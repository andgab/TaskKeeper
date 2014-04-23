package com.taskkeeper.events.workitem;

import com.taskkeeper.events.UpdatedEvent;


public class WorkItemUpdatedEvent extends UpdatedEvent {

  private Long id;
  private WorkItemDetails workItemDetails;

  public WorkItemUpdatedEvent(Long id, WorkItemDetails orderDetails) {
    this.id = id;
    this.workItemDetails = orderDetails;
  }

  public WorkItemUpdatedEvent(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public WorkItemDetails getWorkItemDetails() {
    return workItemDetails;
  }
  
  public static WorkItemUpdatedEvent notFound(Long id) {
    WorkItemUpdatedEvent ev = new WorkItemUpdatedEvent(id);
    ev.entityFound=false;
    return ev;
  }
}
