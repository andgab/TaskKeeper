package com.taskkeeper.events.workitem;

import com.taskkeeper.events.UpdateEvent;

import java.util.UUID;

public class UpdateOrderEvent extends UpdateEvent {

  private UUID key;
  private WorkItemDetails orderDetails;

  public UpdateOrderEvent(UUID key, WorkItemDetails orderDetails) {
    this.key = key;
    this.orderDetails = orderDetails;
  }

  public UUID getKey() {
    return key;
  }

  public WorkItemDetails getOrderDetails() {
    return orderDetails;
  }
}
