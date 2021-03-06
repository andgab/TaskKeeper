package com.taskkeeper.web.controller.workitem;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.AllWorkItemsEvent;
import com.taskkeeper.events.workitem.RequestAllWorkItemsEvent;
import com.taskkeeper.events.workitem.WorkItemDetails;
import com.taskkeeper.web.domain.WorkItemInfo;

@Controller
@RequestMapping("/workItems")
public class WorkItemsController {

  private static final Logger LOG = LoggerFactory.getLogger(WorkItemsController.class);

  @Autowired
  private WorkItemService workItemService;

  @RequestMapping(method = RequestMethod.GET)
  public String workItems(Model model) {
    
    model.addAttribute("workItems", getWorkItems(workItemService.requestAllWorkItems(new RequestAllWorkItemsEvent())));
    
    return "/workItem/workItems";
  }

  private List<WorkItemInfo> getWorkItems(AllWorkItemsEvent allWorkItemsEvent) {
    List<WorkItemInfo> workItems = new ArrayList<WorkItemInfo>();

    for (WorkItemDetails workItemDetails : allWorkItemsEvent.getWorkItemDetails()) {
      workItems.add(WorkItemInfo.fromWorkItemDetails(workItemDetails));
    }
    return workItems;
  }

}
