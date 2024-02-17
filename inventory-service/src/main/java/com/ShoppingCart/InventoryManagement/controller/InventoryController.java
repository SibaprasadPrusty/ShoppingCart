package com.ShoppingCart.InventoryManagement.controller;


import com.ShoppingCart.InventoryManagement.dto.InventoryResponse;
import com.ShoppingCart.InventoryManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    //http://localhost:8082/api/inventory/Iphone
    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("skuCode") String skuCode ){
        System.out.println("Inside Controller");
            return inventoryService.isInStock(skuCode);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode ){
        System.out.println("Inside Controller isInStock RequestParam");
        return inventoryService.isInStock(skuCode);
    }
}
