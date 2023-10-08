package com.prabin.inventoryservice.controller;

import com.prabin.inventoryservice.dto.InventoryResponse;
import com.prabin.inventoryservice.entity.Inventory;
import com.prabin.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public String addInventory(@RequestBody InventoryResponse inventoryResponse){
        return inventoryService.addInventory(inventoryResponse);
    }
    @GetMapping
    public List<Inventory>getAllInventory(){
        return inventoryService.getAllInventory();
    }

    @GetMapping("/inquiry/{itemName}")
    public Boolean inquiryItem(@PathVariable("itemName") String itemName){
        return inventoryService.inquiryItem(itemName);
    }


}
