package com.taskkeeper.core.domain.fixtures;


import java.util.Date;

import com.taskkeeper.core.domain.WorkItem;
import com.taskkeeper.events.workitem.WorkItemDetails;

public class WorkItemsFixtures {

	private static Long id = 1L;

	public static WorkItem standardWorkItem() {
		WorkItem workItem = new WorkItem(new Date());
		
		
		workItem.setId(id++);
		workItem.setTitle("Test WorkItem");
		workItem.setDescription("Test text");
		workItem.setDoDate(new Date());
		workItem.setDoneDate(new Date());
		workItem.setLastUpdate(new Date());
		workItem.setStatus(1);

		return workItem;
	}


	/*
	 * Twin of the above, to improve readability
	 */
	public static WorkItemDetails standardOrderDetails() {
		return standardWorkItem().toWorkItemDetails();
	}

}
