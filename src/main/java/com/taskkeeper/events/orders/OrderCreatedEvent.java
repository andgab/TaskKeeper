package com.taskkeeper.events.orders;

import java.util.UUID;

import com.taskkeeper.events.CreatedEvent;

public class OrderCreatedEvent extends CreatedEvent {
  private final UUID newOrderKey;
  private final OrderDetails details;
  
  public OrderCreatedEvent(final UUID newOrderKey, final OrderDetails details) {
    this.newOrderKey = newOrderKey;
    this.details = details;
  }
  
  public OrderDetails getDetails() {
    return details;
  }

  public UUID getNewOrderKey() {
    return newOrderKey;
  }

}
