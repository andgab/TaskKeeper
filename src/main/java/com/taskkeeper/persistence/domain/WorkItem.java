package com.taskkeeper.persistence.domain;

import com.taskkeeper.events.workitem.WorkItemDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity(name = "work_items")
public class WorkItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 45)
  private String title;

  @Size(max = 45)
  private String description;
  private Integer status;

  @Column(name = "do_date")
  @Temporal(TemporalType.DATE)
  private Date doDate;

  @Column(name = "done_date")
  @Temporal(TemporalType.DATE)
  private Date doneDate;

  @Column(name = "create_date_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @Column(name = "last_update")
  @Temporal(TemporalType.TIMESTAMP)
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

  public static WorkItem fromWorkItemDetails(WorkItemDetails workItemDetails) {
  	WorkItem workItem = new WorkItem();
    
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