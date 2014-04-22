package com.taskkeeper.rest.domain;

import com.taskkeeper.events.workitem.WorkItemDetails;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;

@XmlRootElement
public class WorkItem implements Serializable {

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

    BeanUtils.copyProperties(details, this);
    return details;
  }

  public static WorkItem fromWorkItemDetails(WorkItemDetails workItemDetails) {
    WorkItem workItem = new WorkItem(workItemDetails.getCreateDate());

    BeanUtils.copyProperties(workItemDetails, workItem);
    return workItem;
  }

}
