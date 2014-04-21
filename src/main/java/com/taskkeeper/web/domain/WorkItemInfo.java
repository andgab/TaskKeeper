package com.taskkeeper.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

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
  @NotEmpty
  private Date doDate;
  private Date doneDate;
  private Date createDate;
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

    BeanUtils.copyProperties(details, this);
    return details;
  }

  public static WorkItemInfo fromWorkItemDetails(WorkItemDetails workItemDetails) {
    WorkItemInfo workItem = new WorkItemInfo();

    BeanUtils.copyProperties(workItemDetails, workItem);
    return workItem;
  }

}
