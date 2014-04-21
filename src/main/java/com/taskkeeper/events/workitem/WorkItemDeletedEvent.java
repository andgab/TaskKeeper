package com.taskkeeper.events.workitem;

import com.taskkeeper.events.DeletedEvent;

public class WorkItemDeletedEvent extends DeletedEvent {
  private Long id;
  private WorkItemDetails details;
  private boolean deletionCompleted;
  
  private WorkItemDeletedEvent(Long id) {
    this.id = id;
  }

  public WorkItemDeletedEvent(Long id, WorkItemDetails details) {
    this.id = id;
    this.details = details;
    this.deletionCompleted = true;
  }
  
  public Long getKey() {
    return id;
  }

  public WorkItemDetails getDetails() {
    return details;
  }
  
  public boolean isDeletionCompleted() {
    return deletionCompleted;
  }
  
  public static WorkItemDeletedEvent deletionForbidden(Long id, WorkItemDetails details) {
    WorkItemDeletedEvent ev = new WorkItemDeletedEvent(id, details);
    ev.entityFound=true;
    ev.deletionCompleted=false;
    return ev;
  }
  
  public static WorkItemDeletedEvent notFound(Long id) {
    WorkItemDeletedEvent ev = new WorkItemDeletedEvent(id);
    ev.entityFound=false;
    ev.deletionCompleted=false;
    return ev;
  }
  
}
