package com.taskkeeper.rest.controller.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.taskkeeper.events.workitem.*;



public class RestDataFixture {
	public static final String YUMMY_ITEM = "yummy1";
	
  public static AllWorkItemsEvent allWorkItems() {
    List<WorkItemDetails> workItems = new ArrayList<WorkItemDetails>();

    workItems.add(standardWorkItemDetails());
    workItems.add(standardWorkItemDetails());
    workItems.add(standardWorkItemDetails());

    return new AllWorkItemsEvent(workItems);
  }
	
	
	public static WorkItemDetails customKeyOrderDetails(UUID key) {
		WorkItemDetails orderdetatils = new WorkItemDetails(key);
		
		orderdetatils.setOrderItems(Collections.singletonMap(YUMMY_ITEM, 12));
		
		return orderdetatils;
	}
	
  public static WorkItemDetails standardWorkItemDetails() {
    return customKeyOrderDetails(UUID.randomUUID());
  }
  
  public static String standardOrderJSON() {
    return "{ \"items\": { \"yummy1\": 12, \"yummy15\": 42 } }";
  }

}
