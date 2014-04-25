package com.taskkeeper.core.services;

import java.util.Date;

import com.taskkeeper.core.domain.WorkItem;
import com.taskkeeper.core.domain.WorkItemStatus;
import com.taskkeeper.events.workitem.*;
import com.taskkeeper.persistence.domain.WorkItemComment;
import com.taskkeeper.persistence.services.WorkItemPersistenceService;

public class WorkItemEventHandler implements WorkItemService {

	private final WorkItemPersistenceService workItemPersistenceService;

	public WorkItemEventHandler(final WorkItemPersistenceService workItemPersistenceService) {
		this.workItemPersistenceService = workItemPersistenceService;
	}

	@Override
	public WorkItemCreatedEvent createWorkItem(CreateWorkItemEvent createWorkItemEvent) {
		WorkItem workItem = WorkItem.fromWorkItemDetails(createWorkItemEvent.getDetails());

		// Set create date and last update to today
		workItem.setLastUpdate(new Date());
		workItem.setStatus(WorkItemStatus.OPEND);

		// TODO, add validation of menu items
		// TODO, add order total calculation
		// TODO, add order time estimate calculation
		WorkItemCreatedEvent event = workItemPersistenceService.createWorkItem(new CreateWorkItemEvent(
		    workItem.toWorkItemDetails()));
		return event;
	}

	@Override
	public AllWorkItemsEvent requestAllWorkItems(RequestAllWorkItemsEvent requestAllCurrentOrdersEvent) {
		return workItemPersistenceService.requestAllWorkItems(requestAllCurrentOrdersEvent);
	}

	@Override
	public WorkItemEvent requestWorkItem(RequestWorkItemEvent requestWorkItemEvent) {
		return workItemPersistenceService.requestWorkItemDetails(requestWorkItemEvent);
	}

	@Override
	public WorkItemUpdatedEvent updateWorkItem(UpdateWorkItemEvent updateWorkItemEvent) {

		WorkItemEvent workItemEvent = workItemPersistenceService
		    .requestWorkItemDetails(new RequestWorkItemEvent(updateWorkItemEvent.getId()));

		if (!workItemEvent.isEntityFound()) {
			return WorkItemUpdatedEvent.notFound(updateWorkItemEvent.getId());
		}

		WorkItem oldWorkItem = WorkItem.fromWorkItemDetails(workItemEvent.getWorkItemDetails());

		WorkItem updatedWorkItem = WorkItem.fromWorkItemDetails(updateWorkItemEvent
		    .getWorkItemDetails());

		oldWorkItem.setTitle(updatedWorkItem.getTitle());
		oldWorkItem.setDescription(updatedWorkItem.getDescription());
		oldWorkItem.setAssignedToUser(updatedWorkItem.getAssignedToUser());
		oldWorkItem.setDoDate(updatedWorkItem.getDoDate());
		oldWorkItem.setStatus(updatedWorkItem.getStatus());

		oldWorkItem.setLastUpdate(new Date());

		return workItemPersistenceService.updateWorkItem(new UpdateWorkItemEvent(oldWorkItem.getId(),
		    oldWorkItem.toWorkItemDetails()));
	}

	@Override
	public WorkItemDeletedEvent deleteWorkItem(DeleteWorkItemEvent deleteWorkItemEvent) {

		WorkItemEvent workItemEvent = workItemPersistenceService
		    .requestWorkItemDetails(new RequestWorkItemEvent(deleteWorkItemEvent.getId()));

		if (!workItemEvent.isEntityFound()) {
			return WorkItemDeletedEvent.notFound(deleteWorkItemEvent.getId());
		}
		WorkItem workItem = WorkItem.fromWorkItemDetails(workItemEvent.getWorkItemDetails());

		workItemPersistenceService.deleteWorkItem(deleteWorkItemEvent);

		return new WorkItemDeletedEvent(workItem.getId(), workItem.toWorkItemDetails());
	}

	@Override
	public WorkItemUpdatedEvent addCommentToWorkItem(
	    AddCommentToWorkItemEvent addCommentToWorkItemEvent) {

		WorkItemComment workItemComment = WorkItemComment.fromWorkItemDetails(addCommentToWorkItemEvent
		    .getWorkItemCommentDetails());

		workItemComment.setCreateDate(new Date());

		return workItemPersistenceService.addCommentToWorkItem(new AddCommentToWorkItemEvent(
		    addCommentToWorkItemEvent.getWorkItemId(), workItemComment.toWorkItemCommentDetails()));
	}

}
