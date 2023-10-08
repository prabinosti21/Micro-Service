package com.prabin.inventoryservice.service;

import com.prabin.inventoryservice.dto.InventoryResponse;
import com.prabin.inventoryservice.entity.Inventory;
import com.prabin.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    public String addInventory(InventoryResponse inventoryResponse) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryResponse,inventory);
        inventoryRepository.save(inventory);
        return "Item saved successfully";
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Boolean inquiryItem(String itemName) {
        Optional<Inventory> inventoryOptional= (inventoryRepository.findByItemNameContaining(itemName));
//        Inventory inventory = new Inventory();
//        inventory = inventoryOptional.get();
        if(inventoryOptional.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

}
