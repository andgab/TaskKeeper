package com.taskkeeper.rest.controller.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.taskkeeper.events.orders.*;



public class RestDataFixture {
	public static final String YUMMY_ITEM = "yummy1";
	
  public static AllOrdersEvent allOrders() {
    List<OrderDetails> orders = new ArrayList<OrderDetails>();

    orders.add(standardOrderDetails());
    orders.add(standardOrderDetails());
    orders.add(standardOrderDetails());

    return new AllOrdersEvent(orders);
  }
	
	
	public static OrderDetails customKeyOrderDetails(UUID key) {
		OrderDetails orderdetatils = new OrderDetails(key);
		
		orderdetatils.setOrderItems(Collections.singletonMap(YUMMY_ITEM, 12));
		
		return orderdetatils;
	}
	
  public static OrderDetails standardOrderDetails() {
    return customKeyOrderDetails(UUID.randomUUID());
  }
  
  public static String standardOrderJSON() {
    return "{ \"items\": { \"yummy1\": 12, \"yummy15\": 42 } }";
  }

}
