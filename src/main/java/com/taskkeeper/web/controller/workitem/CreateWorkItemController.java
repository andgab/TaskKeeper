package com.taskkeeper.web.controller.workitem;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.CreateWorkItemEvent;
import com.taskkeeper.events.workitem.WorkItemCreatedEvent;
import com.taskkeeper.web.domain.WorkItemInfo;

@Controller
@RequestMapping("/createWorkItem")
public class CreateWorkItemController {

  private static final Logger LOG = LoggerFactory.getLogger(CreateWorkItemController.class);

  @Autowired
  private WorkItemService workItemService;

  @RequestMapping(method = RequestMethod.GET)
  public String workItem() {

    return "/workItem/createWorkItem";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String createWorkItem(@Valid @ModelAttribute("workItemInfo") WorkItemInfo workItem,
      BindingResult result, RedirectAttributes redirectAttrs) {

    if (result.hasErrors()) {
    	LOG.debug("Error, when trying to create work item {}:", workItem.getTitle());
      // errors in the form
      // show the checkout form again
      return "/workItem/createWorkItem";
    }

    LOG.debug("No errors, continue with processing workItem {}:", workItem.getTitle());
    
    // Set crate date and last update to today
    workItem.setCreateDate(new Date());
    workItem.setLastUpdate(new Date());
       

    WorkItemCreatedEvent workItemCreatedEvent = workItemService
        .createWorkItem(new CreateWorkItemEvent(workItem.toWorkItemDetails()));

    LOG.debug("Created new workItem id {}", workItemCreatedEvent.getNewId());

    return "redirect:/workItems";
  }

  @ModelAttribute("workItemInfo")
  private WorkItemInfo getWorkItemInfo() {
    return new WorkItemInfo();
  }

}
