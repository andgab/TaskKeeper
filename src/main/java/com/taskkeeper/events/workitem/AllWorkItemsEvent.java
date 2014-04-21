package com.taskkeeper.events.workitem;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.taskkeeper.events.ReadEvent;

public class AllWorkItemsEvent extends ReadEvent {
	
	private final List<WorkItemDetails> workItemDetails;
	
  public AllWorkItemsEvent(List<WorkItemDetails> workItemDetails) {
    this.workItemDetails = Collections.unmodifiableList(workItemDetails);
  }

  public Collection<WorkItemDetails> getWorkItemDetails() {
    return this.workItemDetails;
  }
}
