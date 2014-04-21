package com.taskkeeper.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.*;
import com.taskkeeper.rest.domain.WorkItem;

@Controller
@RequestMapping("/aggregators/workitem")
public class WorkItemQueriesController {

  private static Logger LOG = LoggerFactory.getLogger(WorkItemQueriesController.class);

  @Autowired
  private WorkItemService workItemService;

  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<WorkItem> getAllOrders() {
    List<WorkItem> workItem = new ArrayList<WorkItem>();
    for (WorkItemDetails detail : workItemService.requestAllWorkItems(new RequestAllWorkItemsEvent())
        .getWorkItemDetails()) {
      workItem.add(WorkItem.fromWorkItemDetails(detail));
    }
    return workItem;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<WorkItem> viewOrder(@PathVariable String id) {

    WorkItemEvent details = workItemService.requestWorkItem(new RequestWorkItemEvent(Long.parseLong(id)));

    if (!details.isEntityFound()) {
      return new ResponseEntity<WorkItem>(HttpStatus.NOT_FOUND);
    }

    WorkItem workItem = WorkItem.fromWorkItemDetails(details.getWorkItemDetails());

    return new ResponseEntity<WorkItem>(workItem, HttpStatus.OK);
  }

}
