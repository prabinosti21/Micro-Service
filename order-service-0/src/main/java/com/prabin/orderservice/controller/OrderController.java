package com.prabin.orderservice.controller;

import com.prabin.orderservice.dto.OrderResponse;
import com.prabin.orderservice.entity.Order;
import com.prabin.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public String placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    @GetMapping
    public List<OrderResponse> getAllOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/custom/{id}")
    public OrderResponse getOrderById(@PathVariable("id") Long id){
        return orderService.getOrderById(id);
    }
}
