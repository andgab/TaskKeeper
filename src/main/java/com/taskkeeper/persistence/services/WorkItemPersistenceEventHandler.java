package com.taskkeeper.persistence.services;

import com.taskkeeper.events.workitem.*;
import com.taskkeeper.persistence.domain.WorkItem;
import com.taskkeeper.persistence.domain.WorkItemComment;
import com.taskkeeper.persistence.repository.WorkItemCommentRepository;
import com.taskkeeper.persistence.repository.WorkItemRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WorkItemPersistenceEventHandler implements WorkItemPersistenceService {

  private final WorkItemRepository workItemRepository;
  private final WorkItemCommentRepository workItemCommentRepository;
  
  
  private static final Logger LOG = LoggerFactory.getLogger(WorkItemPersistenceEventHandler.class);
  
  public WorkItemPersistenceEventHandler(final WorkItemRepository orderRepository, final WorkItemCommentRepository workItemCommentRepository) {
    this.workItemRepository = orderRepository;
    this.workItemCommentRepository = workItemCommentRepository;
  }

  @Override
  public WorkItemCreatedEvent createWorkItem(CreateWorkItemEvent createOrderEvent) {
    WorkItem workItem = WorkItem.fromWorkItemDetails(createOrderEvent.getDetails());

    workItem.setId(null);
    workItem = workItemRepository.save(workItem);

    return new WorkItemCreatedEvent(workItem.getId(), workItem.toWorkItemDetails());
  }

  @Override
  public AllWorkItemsEvent requestAllWorkItems(RequestAllWorkItemsEvent requestAllCurrentOrdersEvent) {
    List<WorkItemDetails> generatedDetails = new ArrayList<WorkItemDetails>();
    for (WorkItem workItem : workItemRepository.findAll()) {
    	
    	
    	LOG.warn("Test " + workItem.getComments().size());
    	
      generatedDetails.add(workItem.toWorkItemDetails());
    }
    return new AllWorkItemsEvent(generatedDetails);
  }

  @Override
  public WorkItemEvent requestWorkItemDetails(RequestWorkItemEvent requestOrderDetailsEvent) {

    WorkItem workItem = workItemRepository.findOne(requestOrderDetailsEvent.getId());

    if (workItem == null) {
      return WorkItemEvent.notFound(requestOrderDetailsEvent.getId());
    }
    return new WorkItemEvent(requestOrderDetailsEvent.getId(), workItem.toWorkItemDetails());
  }
  
  
  @Override
  public WorkItemDeletedEvent deleteWorkItem(DeleteWorkItemEvent deleteWorkItemEvent) {
    WorkItem workItem = workItemRepository.findOne(deleteWorkItemEvent.getId());

    if (workItem == null) {
      return WorkItemDeletedEvent.notFound(deleteWorkItemEvent.getId());
    }
    workItemRepository.delete(deleteWorkItemEvent.getId());
    
    return new WorkItemDeletedEvent(deleteWorkItemEvent.getId(), workItem.toWorkItemDetails());
  }

  @Override
  public WorkItemUpdatedEvent updateWorkItem(UpdateWorkItemEvent updateWorkItemEvent) {
    WorkItem workItem = workItemRepository.findOne(updateWorkItemEvent.getId());

    if (workItem == null) {
      return WorkItemUpdatedEvent.notFound(updateWorkItemEvent.getId());
    }

    workItem = workItemRepository.save(WorkItem.fromWorkItemDetails(updateWorkItemEvent.getWorkItemDetails()));
    
    return new WorkItemUpdatedEvent(workItem.getId(), workItem.toWorkItemDetails());
  }
  
  @Override
  public WorkItemUpdatedEvent addCommentToWorkItem(AddCommentToWorkItemEvent addCommentToWorkItemEvent) {
    WorkItem workItem = workItemRepository.findOne(addCommentToWorkItemEvent.getWorkItemId());

    if (workItem == null) {
      return WorkItemUpdatedEvent.notFound(addCommentToWorkItemEvent.getWorkItemId());
    }

    workItemCommentRepository.save(WorkItemComment.fromWorkItemDetails(addCommentToWorkItemEvent.getWorkItemCommentDetails()));
    
    // TODO: get updated work item from database

    return new WorkItemUpdatedEvent(workItem.getId(), workItem.toWorkItemDetails());
  }
  
  
}
