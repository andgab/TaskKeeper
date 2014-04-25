package com.taskkeeper.web.controller.workitem;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.AddCommentToWorkItemEvent;
import com.taskkeeper.web.domain.WorkItemCommentInfo;

@Controller
@RequestMapping("/addCommentToWorkItem")
public class AddCommentToWorkItem {

	private static final Logger LOG = LoggerFactory.getLogger(AddCommentToWorkItem.class);

	@Autowired
	private WorkItemService workItemService;

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public String createWorkItemComment(
	    @Valid @ModelAttribute("workItemComment") WorkItemCommentInfo workItemCommentInfo,
	    @PathVariable Long id, BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			// errors in the form
			// show the checkout form again
			return "/workItem/viewWorkItem";
		}
		
		workItemCommentInfo.setWorkItemId(id);		

		// LOG.debug("No errors, continue with processing workItem {}:",
		// workItem.getTitle());
		workItemService.addCommentToWorkItem(new AddCommentToWorkItemEvent(id, workItemCommentInfo.toWorkItemCommentDetails()));

		// LOG.debug("Created new workItem id {}", workItemCreatedEvent.getNewId());

		return "redirect:/viewWorkItem/" + id.toString() + "/view";
	}

}
