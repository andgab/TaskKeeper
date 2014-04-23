package com.taskkeeper.events.workitem;

import com.taskkeeper.events.UpdateEvent;

public class UpdateWorkItemEvent extends UpdateEvent {

  private Long id;
  private WorkItemDetails workItemDetails;

  public UpdateWorkItemEvent(Long id, WorkItemDetails workItemDetails) {
    this.id = id;
    this.workItemDetails = workItemDetails;
  }

  public Long getId() {
    return id;
  }

  public WorkItemDetails getWorkItemDetails() {
    return workItemDetails;
  }
}
