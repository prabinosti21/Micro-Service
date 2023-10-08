package com.prabin.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//http://localhost:8082/inventory/inquiry/iphone
@FeignClient(name = "Inventory-Inquiry", url = "http://localhost:8082/inventory/")
public interface OrderFeignClient {
    @GetMapping("/inquiry/{itemName}")
    Boolean inquiryItem(@PathVariable("itemName") String itemName);
}
