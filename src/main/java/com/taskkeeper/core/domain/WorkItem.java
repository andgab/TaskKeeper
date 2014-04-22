package com.taskkeeper.core.domain;

import java.util.Date;

import com.taskkeeper.events.workitem.WorkItemDetails;


public class WorkItem {

  private Long id;
  private String title;
  private String description;
  private Integer status;
  private Date doDate;
  private Date doneDate;
  private Date createDate;
  private Date lastUpdate;

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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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

 
  public WorkItemDetails toWorkItemDetails() {
    WorkItemDetails details = new WorkItemDetails();
    
    details.setId(this.id);
    details.setTitle(this.title);
    details.setDescription(this.description);
    details.setStatus(this.status);
    details.setDoDate(this.doDate);
    details.setDoneDate(this.doneDate);
    details.setCreateDate(this.createDate);
    details.setLastUpdate(this.lastUpdate);
    
    return details;
  }

  public static WorkItem fromWorkItemDetails(WorkItemDetails workItemDetails) {
  	WorkItem workItem = new WorkItem(workItemDetails.getCreateDate());
    
    workItem.setId(workItemDetails.getId());
    workItem.setTitle(workItemDetails.getTitle());
    workItem.setDescription(workItemDetails.getDescription());
    workItem.setStatus(workItemDetails.getStatus());
    workItem.setDoDate(workItemDetails.getDoDate());
    workItem.setDoneDate(workItemDetails.getDoneDate());
    workItem.setLastUpdate(workItemDetails.getLastUpdate());
    
    return workItem;
  }

}
