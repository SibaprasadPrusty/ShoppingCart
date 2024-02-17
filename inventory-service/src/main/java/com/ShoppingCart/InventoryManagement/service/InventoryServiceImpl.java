package com.ShoppingCart.InventoryManagement.service;

import com.ShoppingCart.InventoryManagement.dto.InventoryResponse;
import com.ShoppingCart.InventoryManagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
     InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly=true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Override
    @Transactional(readOnly=true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {

        System.out.println("skuCode size "+skuCode.size());
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                                    .map(inventory -> InventoryResponse.builder()
                                                        .skuCode(inventory.getSkuCode())
                                                        .isInStock(inventory.getQuantity() > 0)
                                                        .build()
                                    ).toList();
    }
}
