package com.taskkeeper.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.taskkeeper.events.workitem.WorkItemCommentDetails;


@Entity(name = "work_item_comment")
public class WorkItemComment {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 100)
  private String comment;
  
  @Column(name = "create_date_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;
 
  @JoinColumn(name="work_item_id", referencedColumnName="id")
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
