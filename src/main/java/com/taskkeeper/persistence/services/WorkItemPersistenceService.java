package com.taskkeeper.persistence.services;

import com.taskkeeper.events.workitem.*;

public interface WorkItemPersistenceService {
  
  public WorkItemCreatedEvent createWorkItem(CreateWorkItemEvent createWorkItemsEvent);

  public AllWorkItemsEvent requestAllWorkItems(RequestAllWorkItemsEvent requestAllWorkItemsEvent);
  
  public WorkItemEvent requestWorkItemDetails(RequestWorkItemEvent requestWorkItemDetailsEvent);

  public WorkItemDeletedEvent deleteWorkItem(DeleteWorkItemEvent deleteWorkItemsEvent);
  
  public WorkItemUpdatedEvent updateWorkItem(UpdateWorkItemEvent updateWorkItemEvent);
}
