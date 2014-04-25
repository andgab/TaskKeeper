package com.taskkeeper.core.domain;

import java.util.Date;

import com.taskkeeper.events.workitem.WorkItemCommentDetails;

public class WorkItemComment {

	private Long id;
	private String comment;
	private Date createDate;
	private Long workItemId;

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

	public Long getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(Long workItemId) {
		this.workItemId = workItemId;
	}

	public WorkItemCommentDetails toWorkItemCommentDetails() {
		WorkItemCommentDetails workItemCommentDetails = new WorkItemCommentDetails();

		workItemCommentDetails.setId(this.id);
		workItemCommentDetails.setComment(this.comment);
		workItemCommentDetails.setCreateDate(this.createDate);
		workItemCommentDetails.setWorkItemId(this.getWorkItemId());

		return workItemCommentDetails;
	}

	public static WorkItemComment fromWorkItemDetails(WorkItemCommentDetails workItemCommentDetails) {
		WorkItemComment workItemComment = new WorkItemComment();

		workItemComment.setId(workItemCommentDetails.getId());
		workItemComment.setComment(workItemCommentDetails.getComment());
		workItemComment.setCreateDate(workItemCommentDetails.getCreateDate());
		workItemComment.setWorkItemId(workItemCommentDetails.getWorkItemId());

		return workItemComment;
	}
}
