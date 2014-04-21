package com.taskkeeper.events.workitem;

import com.taskkeeper.events.CreateEvent;

public class CreateWorkItemEvent extends CreateEvent {
	private WorkItemDetails details;
	
  public CreateWorkItemEvent(WorkItemDetails details) {
    this.details = details;
  }
	
  public WorkItemDetails getDetails() {
    return details;
  }

}
