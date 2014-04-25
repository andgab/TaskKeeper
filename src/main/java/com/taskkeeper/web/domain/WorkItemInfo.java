package com.taskkeeper.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.taskkeeper.core.domain.WorkItemStatus;
import com.taskkeeper.events.user.UserDetails;
import com.taskkeeper.events.workitem.WorkItemCommentDetails;
import com.taskkeeper.events.workitem.WorkItemDetails;
import com.taskkeeper.persistence.domain.WorkItemComment;

public class WorkItemInfo implements Serializable {

	private Long id;

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	private Long assignedToUserId;
	private String assignedToUserName;

	private WorkItemStatus status;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doneDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdate;

	private List<WorkItemCommentInfo> comments = null;

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

	public Long getAssignedToUserId() {
		return assignedToUserId;
	}

	public void setAssignedToUserId(Long assignedToUserId) {
		this.assignedToUserId = assignedToUserId;
	}

	public String getAssignedToUserName() {
		return assignedToUserName;
	}

	public void setAssignedToUserName(String assignedToUserName) {
		this.assignedToUserName = assignedToUserName;
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

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<WorkItemCommentInfo> getComments() {
		return comments;
	}

	public void setComments(List<WorkItemCommentInfo> comments) {
		this.comments = comments;
	}

	public WorkItemDetails toWorkItemDetails() {
		WorkItemDetails details = new WorkItemDetails();

		details.setId(this.id);
		details.setTitle(this.title);
		details.setDescription(this.description);

		UserDetails userDetails = new UserDetails();
		userDetails.setId(this.assignedToUserId);
		details.setAssignedToUser(userDetails);

		details.setStatus(this.status);
		details.setDoDate(this.doDate);
		details.setDoneDate(this.doneDate);
		details.setCreateDate(this.createDate);
		details.setLastUpdate(this.lastUpdate);

		details.setCommentDetails(null);

		return details;
	}

	public static WorkItemInfo fromWorkItemDetails(WorkItemDetails workItemDetails) {
		WorkItemInfo workItem = new WorkItemInfo();

		workItem.setId(workItemDetails.getId());
		workItem.setTitle(workItemDetails.getTitle());
		workItem.setDescription(workItemDetails.getDescription());
		workItem.setAssignedToUserId(workItemDetails.getAssignedToUser().getId());
		workItem.setAssignedToUserName(workItemDetails.getAssignedToUser().getFirstname() + " "
		    + workItemDetails.getAssignedToUser().getLastname());
		workItem.setStatus(workItemDetails.getStatus());
		workItem.setDoDate(workItemDetails.getDoDate());
		workItem.setDoneDate(workItemDetails.getDoneDate());
		workItem.setCreateDate(workItemDetails.getCreateDate());
		workItem.setLastUpdate(workItemDetails.getLastUpdate());

		if (workItemDetails.getCommentDetails() != null) {
			List<WorkItemCommentInfo> workItemCommentInfo = new ArrayList<WorkItemCommentInfo>();
			for (WorkItemCommentDetails workItemCommentDetails : workItemDetails.getCommentDetails()) {
				workItemCommentInfo.add(WorkItemCommentInfo
				    .fromWorkItemCommentDetails(workItemCommentDetails));
			}
			workItem.setComments(workItemCommentInfo);
		}

		return workItem;
	}

}
