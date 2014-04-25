package com.taskkeeper.events.workitem;

import com.taskkeeper.events.UpdateEvent;

public class AddCommentToWorkItemEvent extends UpdateEvent {
		
	private Long workItemId;
	
	private WorkItemCommentDetails commentDetails;
	
	
  public AddCommentToWorkItemEvent(Long workItemId, WorkItemCommentDetails commentDetails) {
    this.workItemId = workItemId;
    this.commentDetails = commentDetails;
  }
  
  
  public Long getWorkItemId() {
    return workItemId;
  }

  public WorkItemCommentDetails getWorkItemCommentDetails() {
    return commentDetails;
  }


}
