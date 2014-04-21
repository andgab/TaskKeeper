package com.taskkeeper.events.workitem;

import com.taskkeeper.events.ReadEvent;

public class WorkItemEvent extends ReadEvent {
  private Long id;
  private WorkItemDetails workItemDetails;

  private WorkItemEvent(Long id) {
    this.id = id;
  }

  public WorkItemEvent(Long id, WorkItemDetails workItemDetails) {
    this.id = id;
    this.workItemDetails = workItemDetails;
  }

  public Long getId() {
    return id;
  }

  public WorkItemDetails getWorkItemDetails() {
    return workItemDetails;
  }

  public static WorkItemEvent notFound(Long id) {
    WorkItemEvent ev = new WorkItemEvent(id);
    ev.entityFound = false;
    return ev;
  }

}
