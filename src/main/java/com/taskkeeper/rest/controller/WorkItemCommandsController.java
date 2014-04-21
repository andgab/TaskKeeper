package com.taskkeeper.rest.controller;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.CreateWorkItemEvent;
import com.taskkeeper.events.workitem.DeleteWorkItemEvent;
import com.taskkeeper.events.workitem.WorkItemCreatedEvent;
import com.taskkeeper.events.workitem.WorkItemDeletedEvent;
import com.taskkeeper.rest.domain.WorkItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@RequestMapping("/aggregators/workitem")
public class WorkItemCommandsController {
	
	private static Logger LOG = LoggerFactory.getLogger(WorkItemCommandsController.class);
	
  @Autowired
  private WorkItemService workItemService;
  
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<WorkItem> createOrder(@RequestBody WorkItem workItem, UriComponentsBuilder builder) {

      WorkItemCreatedEvent workItemCreated = workItemService.createWorkItem(new CreateWorkItemEvent(workItem.toWorkItemDetails()));

      WorkItem newWorkItem = WorkItem.fromWorkItemDetails(workItemCreated.getDetails());

      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(
              builder.path("/aggregators/workitem/{id}")
                      .buildAndExpand(workItemCreated.getNewId().toString()).toUri());

      return new ResponseEntity<WorkItem>(newWorkItem, headers, HttpStatus.CREATED);
  }
  
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<WorkItem> cancelOrder(@PathVariable String id) {

      WorkItemDeletedEvent workItemDeleted = workItemService.deleteWorkItem(new DeleteWorkItemEvent(Long.parseLong(id)));

      if (!workItemDeleted.isEntityFound()) {
          return new ResponseEntity<WorkItem>(HttpStatus.NOT_FOUND);
      }

      WorkItem workItem = WorkItem.fromWorkItemDetails(workItemDeleted.getDetails());

      if (workItemDeleted.isDeletionCompleted()) {
          return new ResponseEntity<WorkItem>(workItem, HttpStatus.OK);
      }

      return new ResponseEntity<WorkItem>(workItem, HttpStatus.FORBIDDEN);
  }
	

}
