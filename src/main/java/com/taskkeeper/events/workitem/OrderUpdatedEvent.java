package com.taskkeeper.events.workitem;

import com.taskkeeper.events.UpdatedEvent;

import java.util.UUID;

public class OrderUpdatedEvent extends UpdatedEvent {

  private UUID key;
  private WorkItemDetails orderDetails;

  public OrderUpdatedEvent(UUID key, WorkItemDetails orderDetails) {
    this.key = key;
    this.orderDetails = orderDetails;
  }

  public OrderUpdatedEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }

  public WorkItemDetails getOrderDetails() {
    return orderDetails;
  }
  
  public static OrderUpdatedEvent notFound(UUID key) {
    OrderUpdatedEvent ev = new OrderUpdatedEvent(key);
    ev.entityFound=false;
    return ev;
  }
}
