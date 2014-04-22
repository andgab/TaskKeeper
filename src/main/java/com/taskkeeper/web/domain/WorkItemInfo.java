package com.taskkeeper.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.taskkeeper.events.workitem.WorkItemDetails;

public class WorkItemInfo implements Serializable {

  private Long id;
  
  @NotNull
  @NotEmpty
  private String title;
  
  @NotNull
  @NotEmpty
  private String description;
  private Integer status;
  
  @NotNull
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date doDate;
  
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date doneDate;
  
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date createDate;
  
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date lastUpdate;

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

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
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

  public static WorkItemInfo fromWorkItemDetails(WorkItemDetails workItemDetails) {
    WorkItemInfo workItem = new WorkItemInfo();
    
    workItem.setId(workItemDetails.getId());
    workItem.setTitle(workItemDetails.getTitle());
    workItem.setDescription(workItemDetails.getDescription());
    workItem.setStatus(workItemDetails.getStatus());
    workItem.setDoDate(workItemDetails.getDoDate());
    workItem.setDoneDate(workItemDetails.getDoneDate());
    workItem.setCreateDate(workItemDetails.getCreateDate());
    workItem.setLastUpdate(workItemDetails.getLastUpdate());
    
    return workItem;
  }

}
