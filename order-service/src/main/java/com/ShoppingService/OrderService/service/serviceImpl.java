package com.ShoppingService.OrderService.service;

import java.util.*;

import com.ShoppingService.OrderService.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import com.ShoppingService.OrderService.dto.OrderLineItemsDto;
import com.ShoppingService.OrderService.dto.OrderRequest;
import com.ShoppingService.OrderService.model.Order;
import com.ShoppingService.OrderService.model.OrderLineItems;
import com.ShoppingService.OrderService.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class serviceImpl implements service{


	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	@Override
	public void palceOrder(OrderRequest orderRequest) {
		
		Order order= new Order();
		//OrderLineItems orderLineItems = new OrderLineItems();
		
		
		order.setOrderNo(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsList()
										.stream()
										.map(this::maptoDto)
										.toList();
		
		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodeList=order.getOrderLineItemsList().stream()
				    				  .map(OrderLineItems::getSkuCode)
										.toList();

		System.out.println("skuCodeList size "+skuCodeList.size());

		//Calling Inventory Service for the availability of the Product

		InventoryResponse[] inventoryResponseArray=webClientBuilder.build().get()
				.uri("http://Inventory-Service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodeList).build())
						.retrieve()
								.bodyToMono(InventoryResponse[].class)
										.block();

		//assert inventoryResponseArray != null;
		System.out.println("inventoryResponseArray size "+inventoryResponseArray.length);

		/*for(InventoryResponse response : inventoryResponseArray)
			System.out.println("Response "+response.getSkuCode()+" "+response.isInStock());

			return Optional.ofNullable(userList)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(user -> user.getName())
                .collect(toList());

			*/

        boolean allProductInStock = Arrays.stream(inventoryResponseArray)
											.allMatch(InventoryResponse::isInStock);

		/*Optional allProductInStock = Arrays.stream(Optional.ofNullable(inventoryResponseArray))
										.allMatch(InventoryResponse::isInStock);*/

		System.out.println("allProductInStock "+allProductInStock);
		if(allProductInStock)
		orderRepository.save(order);
		else
			throw new RuntimeException("Not in Inventory");
	}

	private OrderLineItems maptoDto(OrderLineItemsDto orderLineItemsDto) {
		// TODO Auto-generated method stub
		
		OrderLineItems orderLineItems = new OrderLineItems();
		
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		System.out.println(orderLineItemsDto.getSkuCode());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		
		return orderLineItems;
	}

}
