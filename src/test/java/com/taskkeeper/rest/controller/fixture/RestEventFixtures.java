package com.taskkeeper.rest.controller.fixture;

import java.util.Date;
import java.util.UUID;

import com.taskkeeper.events.orders.*;

import static com.taskkeeper.rest.controller.fixture.RestDataFixture.*;

public class RestEventFixtures {

  public static OrderStatusEvent orderStatusNotFound(UUID OrderKey) {
    return OrderStatusEvent.notFound(OrderKey);
  }
  
  public static OrderStatusEvent orderStatus(UUID OrderKey, UUID statusKey, String status) {
    return new OrderStatusEvent(OrderKey, new OrderStatusDetails(OrderKey, statusKey, new Date(), status));
  }
  
	public static OrderDetailsEvent orderDetailsNotFound(UUID OrderKey) {
		return OrderDetailsEvent.notFound(OrderKey);
	}

	public static OrderDetailsEvent orderDetailsEvent(UUID OrderKey) {
		return new OrderDetailsEvent(OrderKey, customKeyOrderDetails(OrderKey));
	}
	
  public static OrderCreatedEvent orderCreated(UUID OrderKey) {
    return new OrderCreatedEvent(OrderKey, customKeyOrderDetails(OrderKey));
  }
	
  public static OrderDeletedEvent orderDeleted(UUID OrderKey) {
    return new OrderDeletedEvent(OrderKey, standardOrderDetails());
  }
  
  public static OrderDeletedEvent orderDeletedFailed(UUID OrderKey) {
    return OrderDeletedEvent.deletionForbidden(OrderKey, standardOrderDetails());
  } 
  
  public static OrderDeletedEvent orderDeletedNotFound(UUID OrderKey) {
    return OrderDeletedEvent.notFound(OrderKey);
  }

}
