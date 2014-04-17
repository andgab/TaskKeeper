package com.taskkeeper.events.orders;

import java.util.UUID;

public class RequestOrderStatusEvent {
  private UUID key;

  public RequestOrderStatusEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }
}
