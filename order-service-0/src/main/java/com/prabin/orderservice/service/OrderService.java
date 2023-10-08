package com.prabin.orderservice.service;

import com.prabin.orderservice.dto.OrderResponse;
import com.prabin.orderservice.entity.Order;
import com.prabin.orderservice.feignclient.OrderFeignClient;
import com.prabin.orderservice.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private ModelMapper modelMapper;
    public String placeOrder(Order order) {
        //ask inventory service if the item is in inventory or not.
        Boolean result = orderFeignClient.inquiryItem(order.getItemName());
        if(result.booleanValue() == true){
            orderRepository.save(order);
            return "Order placed successfully.";
        }
        else{
            return "Sorry your order is not in the stock.";
        }
    }

    public List<OrderResponse> getAllOrder() {
        List<Order>orderList = new ArrayList<>();
        orderList = (orderRepository.findAll());
        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderResponseList = Arrays.asList(modelMapper.map(orderList,OrderResponse[].class));
        return orderResponseList;
    }

    public OrderResponse getOrderById(Long id) {
        Optional<Order>optionalOrder= orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            OrderResponse orderResponse= OrderResponse.builder().build();
            orderResponse = modelMapper.map(optionalOrder.get(), OrderResponse.class);
            return orderResponse;
        }
        else {
            return null;
        }
    }
}
