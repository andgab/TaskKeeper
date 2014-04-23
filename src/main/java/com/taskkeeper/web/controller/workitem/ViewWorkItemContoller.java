package com.taskkeeper.web.controller.workitem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskkeeper.core.services.UserService;
import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.user.AllUsersEvent;
import com.taskkeeper.events.user.RequestAllUsersEvent;
import com.taskkeeper.events.user.UserDetails;

import com.taskkeeper.events.workitem.RequestWorkItemEvent;
import com.taskkeeper.events.workitem.UpdateWorkItemEvent;
import com.taskkeeper.events.workitem.WorkItemEvent;
import com.taskkeeper.events.workitem.WorkItemUpdatedEvent;
import com.taskkeeper.web.domain.UserInfo;
import com.taskkeeper.web.domain.WorkItemInfo;

@Controller
@RequestMapping("/viewWorkItem")
public class ViewWorkItemContoller {

  private static final Logger LOG = LoggerFactory.getLogger(WorkItemsController.class);

  @Autowired
  private WorkItemService workItemService;

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public String workItems(@PathVariable Long id, Model model) {

    WorkItemEvent wokItemEvent = workItemService.requestWorkItem(new RequestWorkItemEvent(id));

    model.addAttribute("workItemInfo",
        WorkItemInfo.fromWorkItemDetails(wokItemEvent.getWorkItemDetails()));

    model.addAttribute("allUsers",
        getUsers(userService.requestAllUsersSortedByFirstname(new RequestAllUsersEvent())));

    return "/workItem/viewWorkItem";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String updateWorkItem(@Valid @ModelAttribute("workItemInfo") WorkItemInfo workItem,
      BindingResult result, RedirectAttributes redirectAttrs) {

    if (result.hasErrors()) {
      LOG.debug("Error, when trying to create work item {}:", workItem.getTitle());
      // errors in the form
      // show the checkout form again
      return "/workItem/viewWorkItem";
    }

    LOG.debug("No errors, continue with processing workItem {}:", workItem.getTitle());

    WorkItemUpdatedEvent workItemUpdatedEvent = workItemService
        .updateWorkItem(new UpdateWorkItemEvent(workItem.getId(), workItem.toWorkItemDetails()));
    
    
    if(!workItemUpdatedEvent.isEntityFound()) {
      return "redirect:/workItems";
    }
      

    LOG.debug("Created new workItem id {}", workItemUpdatedEvent.getId());

    return "redirect:/viewWorkItem/" + workItem.getId().toString();
  }

  private List<UserInfo> getUsers(AllUsersEvent alluserEvent) {
    List<UserInfo> userInfos = new ArrayList<UserInfo>();

    for (UserDetails ueserDetails : alluserEvent.getUserDetails()) {
      userInfos.add(UserInfo.fromUserDetails(ueserDetails));
    }
    return userInfos;
  }

}
