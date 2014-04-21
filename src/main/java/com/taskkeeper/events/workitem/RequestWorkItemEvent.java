package com.taskkeeper.events.workitem;

import com.taskkeeper.events.RequestReadEvent;

public class RequestWorkItemEvent extends RequestReadEvent {
	private Long id;
	
  public RequestWorkItemEvent(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

}
