package com.taskkeeper.core.services;

import com.taskkeeper.events.workitem.*;

//TODOCUMENT THis is an event driven service.
//Used to interact with the core domain.
//All methods are guaranteed to return something, null will never be returned.
public interface WorkItemService {
  
  public WorkItemCreatedEvent createWorkItem(CreateWorkItemEvent event);

	public AllWorkItemsEvent requestAllWorkItems(RequestAllWorkItemsEvent event);

  public WorkItemEvent requestWorkItem(RequestWorkItemEvent event);
  
  public WorkItemUpdatedEvent updateWorkItem(UpdateWorkItemEvent event);
  
  public WorkItemDeletedEvent deleteWorkItem(DeleteWorkItemEvent event);
  
  public WorkItemUpdatedEvent addCommentToWorkItem(AddCommentToWorkItemEvent event);
}
