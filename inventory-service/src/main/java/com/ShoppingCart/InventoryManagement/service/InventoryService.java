package com.ShoppingCart.InventoryManagement.service;


import com.ShoppingCart.InventoryManagement.dto.InventoryResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InventoryService {

    public boolean isInStock(String skuCode);

    @Transactional(readOnly=true)
    List<InventoryResponse> isInStock(List<String> skuCode);
}
