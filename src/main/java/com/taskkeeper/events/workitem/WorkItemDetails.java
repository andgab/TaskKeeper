package com.taskkeeper.events.workitem;

import java.util.Date;

import com.taskkeeper.core.domain.WorkItemStatus;
import com.taskkeeper.events.user.UserDetails;

public class WorkItemDetails {

  private Long id;
  private String title;
  private String description;
  private UserDetails assignedToUser;
  private WorkItemStatus status;
  private Date doDate;
  private Date doneDate;
  private Date createDate;
  private Date lastUpdate;

	public WorkItemDetails() {
	  this.id = null;
	}

	public WorkItemDetails(Long id) {
		this.id = id;
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
  
	public void setAssignedToUser(UserDetails assignedToUser) {
	  this.assignedToUser = assignedToUser;	  
  }
	
	public UserDetails getAssignedToUser() {
		return assignedToUser;
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


}
