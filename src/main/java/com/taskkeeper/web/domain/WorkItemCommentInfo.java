package com.taskkeeper.web.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.taskkeeper.events.workitem.WorkItemCommentDetails;


public class WorkItemCommentInfo {

	private Long id;
	private Long workItemId;

	@NotNull
	@NotEmpty
	private String comment;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(Long workItemId) {
		this.workItemId = workItemId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public WorkItemCommentDetails toWorkItemCommentDetails() {
		WorkItemCommentDetails workItemCommentDetails = new WorkItemCommentDetails();

		workItemCommentDetails.setId(this.id);
		workItemCommentDetails.setComment(this.comment);
		workItemCommentDetails.setCreateDate(this.createDate);
		workItemCommentDetails.setWorkItemId(this.workItemId);
		
		return workItemCommentDetails;
	}

	public static WorkItemCommentInfo fromWorkItemCommentDetails(
	    WorkItemCommentDetails workItemCommentDetails) {
		WorkItemCommentInfo workItemCommentInfo = new WorkItemCommentInfo();

		workItemCommentInfo.setId(workItemCommentDetails.getId());
		workItemCommentInfo.setComment(workItemCommentDetails.getComment());
		workItemCommentInfo.setCreateDate(workItemCommentDetails.getCreateDate());		
		workItemCommentInfo.setWorkItemId(workItemCommentDetails.getWorkItemId());

		return workItemCommentInfo;
	}

}
