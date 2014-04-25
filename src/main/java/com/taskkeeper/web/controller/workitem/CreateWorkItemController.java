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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskkeeper.core.domain.WorkItemStatus;
import com.taskkeeper.core.services.UserService;
import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.user.AllUsersEvent;
import com.taskkeeper.events.user.RequestAllUsersEvent;
import com.taskkeeper.events.user.UserDetails;
import com.taskkeeper.events.workitem.CreateWorkItemEvent;
import com.taskkeeper.events.workitem.WorkItemCreatedEvent;
import com.taskkeeper.web.domain.UserInfo;
import com.taskkeeper.web.domain.WorkItemInfo;

@Controller
@RequestMapping("/createWorkItem")
public class CreateWorkItemController {

	private static final Logger LOG = LoggerFactory.getLogger(CreateWorkItemController.class);

	@Autowired
	private WorkItemService workItemService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String workItem(Model model) {

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

		// Set create date and last update to today
		workItem.setCreateDate(new Date());
		workItem.setLastUpdate(new Date());
		workItem.setStatus(WorkItemStatus.OPEND);

		WorkItemCreatedEvent workItemCreatedEvent = workItemService
		    .createWorkItem(new CreateWorkItemEvent(workItem.toWorkItemDetails()));

		LOG.debug("Created new workItem id {}", workItemCreatedEvent.getNewId());

		return "redirect:/workItems";
	}

	@ModelAttribute("workItemInfo")
	private WorkItemInfo getWorkItemInfo() {
		return new WorkItemInfo();
	}

	@ModelAttribute("allUsers")
	private List<UserInfo> getAllUsers() {
		return getUsers(userService.requestAllUsersSortedByFirstname(new RequestAllUsersEvent()));
	}

	private List<UserInfo> getUsers(AllUsersEvent alluserEvent) {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();

		for (UserDetails ueserDetails : alluserEvent.getUserDetails()) {
			userInfos.add(UserInfo.fromUserDetails(ueserDetails));
		}
		return userInfos;
	}

}
