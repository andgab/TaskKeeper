package com.taskkeeper.core.domain;

import java.util.Date;

import com.taskkeeper.events.workitem.WorkItemCommentDetails;

public class WorkItemComment {

	private Long id;
	private String comment;
	private Date createDate;
	private WorkItem workItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}

	public WorkItemCommentDetails toWorkItemCommentDetails() {
		WorkItemCommentDetails workItemCommentDetails = new WorkItemCommentDetails();

		workItemCommentDetails.setId(this.id);
		workItemCommentDetails.setComment(this.comment);
		workItemCommentDetails.setCreateDate(this.createDate);
		workItemCommentDetails.setWorkItemDetails(this.getWorkItem().toWorkItemDetails());

		return workItemCommentDetails;
	}

	public static WorkItemComment fromWorkItemDetails(WorkItemCommentDetails workItemCommentDetails) {
		WorkItemComment workItemComment = new WorkItemComment();

		workItemComment.setId(workItemCommentDetails.getId());
		workItemComment.setComment(workItemCommentDetails.getComment());
		workItemComment.setCreateDate(workItemCommentDetails.getCreateDate());
		workItemComment.setWorkItem(WorkItem.fromWorkItemDetails(workItemCommentDetails.getWorkItemDetails()));

		return workItemComment;
	}
}
