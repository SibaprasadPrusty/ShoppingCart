package com.ShoppingService.OrderService.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="T_Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String orderNo;
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrderLineItems> orderLineItemsList;
	
}
	