package com.taskkeeper.persistence.services;

import com.taskkeeper.events.workitem.*;
import com.taskkeeper.persistence.domain.WorkItem;
import com.taskkeeper.persistence.repository.WorkItemRepository;

import java.util.ArrayList;
import java.util.List;


public class WorkItemPersistenceEventHandler implements WorkItemPersistenceService {

  private final WorkItemRepository workItemRepository;

  public WorkItemPersistenceEventHandler(final WorkItemRepository orderRepository) {
    this.workItemRepository = orderRepository;
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
/*
  @Override
  public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
    WorkItem order = workItemRepository.findOne(setOrderPaymentEvent.getKey().toString());

    if (order == null) {
      return OrderUpdatedEvent.notFound(setOrderPaymentEvent.getKey());
    }

    // TODO, handling payment details...

    return new OrderUpdatedEvent(UUID.fromString(order.getId()), order.toOrderDetails());
  }
*/
}
