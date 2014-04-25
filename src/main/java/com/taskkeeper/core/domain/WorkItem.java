package com.taskkeeper.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taskkeeper.events.workitem.WorkItemCommentDetails;
import com.taskkeeper.events.workitem.WorkItemDetails;
import com.taskkeeper.persistence.domain.WorkItemComment;

public class WorkItem {

	private Long id;
	private String title;
	private String description;
	private User assignedToUser;
	private WorkItemStatus status;
	private Date doDate;
	private Date doneDate;
	private Date createDate;
	private Date lastUpdate;
	private List<WorkItemComment> comments = null;

	public WorkItem(final Date createDate) {
		this.id = null;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(User assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public WorkItemStatus getStatus() {
		return status;
	}

	public void setStatus(WorkItemStatus status) {
		this.status = status;
	}

	public Date getDoDate() {
		return doDate;
	}

	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}

	public Date getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<WorkItemComment> getComments() {
		return comments;
	}

	public void setComments(List<WorkItemComment> comments) {
		this.comments = comments;
	}

	public WorkItemDetails toWorkItemDetails() {
		WorkItemDetails details = new WorkItemDetails();

		details.setId(this.id);
		details.setTitle(this.title);
		details.setDescription(this.description);
		details.setAssignedToUser(this.assignedToUser.toUserDetails());
		details.setStatus(this.status);
		details.setDoDate(this.doDate);
		details.setDoneDate(this.doneDate);
		details.setCreateDate(this.createDate);
		details.setLastUpdate(this.lastUpdate);

		if (this.getComments() != null) {
			List<WorkItemCommentDetails> workItemCommentDetails = new ArrayList<WorkItemCommentDetails>();
			for (WorkItemComment workItemComment : this.getComments()) {
				workItemCommentDetails.add(workItemComment.toWorkItemCommentDetails());
			}
			details.setCommentDetails(workItemCommentDetails);
		}

		return details;
	}

	public static WorkItem fromWorkItemDetails(WorkItemDetails workItemDetails) {
		WorkItem workItem = new WorkItem(workItemDetails.getCreateDate());

		workItem.setId(workItemDetails.getId());
		workItem.setTitle(workItemDetails.getTitle());
		workItem.setDescription(workItemDetails.getDescription());
		workItem.setAssignedToUser(User.fromUserDetails(workItemDetails.getAssignedToUser()));
		workItem.setStatus(workItemDetails.getStatus());
		workItem.setDoDate(workItemDetails.getDoDate());
		workItem.setDoneDate(workItemDetails.getDoneDate());
		workItem.setLastUpdate(workItemDetails.getLastUpdate());

		if (workItemDetails.getCommentDetails() != null) {
			List<WorkItemComment> workItemComments = new ArrayList<WorkItemComment>();
			for (WorkItemCommentDetails workItemCommentDetails : workItemDetails.getCommentDetails()) {
				workItemComments.add(WorkItemComment.fromWorkItemDetails(workItemCommentDetails));
			}
			workItem.setComments(workItemComments);
		}

		return workItem;
	}

}
