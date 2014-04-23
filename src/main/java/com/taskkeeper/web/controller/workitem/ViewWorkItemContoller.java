package com.taskkeeper.web.controller.workitem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.RequestAllWorkItemsEvent;
import com.taskkeeper.web.domain.WorkItemInfo;


@Controller
@RequestMapping("/viewWorkItem")
public class ViewWorkItemContoller {

  private static final Logger LOG = LoggerFactory.getLogger(WorkItemsController.class);

  @Autowired
  private WorkItemService workItemService;

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public String workItems(@PathVariable Long id, Model model) {
    
    //model.addAttribute("workItems", getWorkItems(workItemService.requestAllWorkItems(new RequestAllWorkItemsEvent())));
  	
  	model.addAttribute("workItemInfo", new WorkItemInfo());
    
    return "/workItem/viewWorkItem";
  }
	
	

}
