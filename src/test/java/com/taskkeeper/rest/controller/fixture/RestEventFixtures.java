package com.taskkeeper.rest.controller.fixture;

import java.util.Date;
import java.util.UUID;

import com.taskkeeper.events.workitem.*;

import static com.taskkeeper.rest.controller.fixture.RestDataFixture.*;

public class RestEventFixtures {

  public static OrderStatusEvent orderStatusNotFound(UUID OrderKey) {
    return OrderStatusEvent.notFound(OrderKey);
  }
  
  public static OrderStatusEvent orderStatus(UUID OrderKey, UUID statusKey, String status) {
    return new OrderStatusEvent(OrderKey, new OrderStatusDetails(OrderKey, statusKey, new Date(), status));
  }
  
	public static WorkItemEvent orderDetailsNotFound(UUID OrderKey) {
		return WorkItemEvent.notFound(OrderKey);
	}

	public static WorkItemEvent orderDetailsEvent(UUID OrderKey) {
		return new WorkItemEvent(OrderKey, customKeyOrderDetails(OrderKey));
	}
	
  public static WorkItemCreatedEvent orderCreated(UUID OrderKey) {
    return new WorkItemCreatedEvent(OrderKey, customKeyOrderDetails(OrderKey));
  }
	
  public static WorkItemDeletedEvent orderDeleted(UUID OrderKey) {
    return new WorkItemDeletedEvent(OrderKey, standardWorkItemDetails());
  }
  
  public static WorkItemDeletedEvent orderDeletedFailed(UUID OrderKey) {
    return WorkItemDeletedEvent.deletionForbidden(OrderKey, standardWorkItemDetails());
  } 
  
  public static WorkItemDeletedEvent orderDeletedNotFound(UUID OrderKey) {
    return WorkItemDeletedEvent.notFound(OrderKey);
  }

}
