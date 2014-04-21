package com.taskkeeper.events.workitem;

import com.taskkeeper.events.CreatedEvent;

public class WorkItemCreatedEvent extends CreatedEvent {
  private final Long newId;
  private final WorkItemDetails details;
  
  public WorkItemCreatedEvent(final Long newId, final WorkItemDetails details) {
    this.newId = newId;
    this.details = details;
  }
  
  public WorkItemDetails getDetails() {
    return details;
  }

  public Long getNewId() {
    return newId;
  }

}
