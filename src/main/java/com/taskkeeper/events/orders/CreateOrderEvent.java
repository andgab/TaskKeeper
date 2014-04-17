package com.taskkeeper.events.orders;

import com.taskkeeper.events.CreateEvent;

public class CreateOrderEvent extends CreateEvent {
	private OrderDetails details;
	
  public CreateOrderEvent(OrderDetails details) {
    this.details = details;
  }
	
  public OrderDetails getDetails() {
    return details;
  }

}
