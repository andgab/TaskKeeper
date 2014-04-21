package com.taskkeeper.events.workitem;

import com.taskkeeper.events.UpdateEvent;

import java.util.UUID;

public class SetOrderStatusEvent extends UpdateEvent {

  private UUID orderKey;
  private OrderStatusDetails orderStatus;

  public SetOrderStatusEvent(UUID orderKey, OrderStatusDetails orderStatus) {
    this.orderKey = orderKey;
    this.orderStatus = orderStatus;
  }

  public UUID getKey() {
    return orderKey;
  }

  public OrderStatusDetails getOrderStatus() {
    return orderStatus;
  }
}
