package com.taskkeeper.events.workitem;

import java.util.Date;

public class WorkItemCommentDetails {

	private Long id;
	private String comment;
	private Date createDate;
	private WorkItemDetails workItemDetails;

	public WorkItemCommentDetails() {
		id = null;
	}

	public WorkItemCommentDetails(Long id) {
		this.id = id;
	}

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

	public WorkItemDetails getWorkItemDetails() {
		return workItemDetails;
	}

	public void setWorkItemDetails(WorkItemDetails workItemDetails) {
		this.workItemDetails = workItemDetails;
	}
}
