package com.taskkeeper.events.orders;


import java.util.UUID;

import com.taskkeeper.events.RequestReadEvent;

public class RequestOrderDetailsEvent extends RequestReadEvent {
	private UUID key;
	
  public RequestOrderDetailsEvent(UUID key) {
    this.key = key;
  }

  public UUID getKey() {
    return key;
  }

}