package com.taskkeeper.web.controller;

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

import com.taskkeeper.core.services.UserService;
import com.taskkeeper.events.user.CreateUserEvent;
import com.taskkeeper.events.user.UserCreatedEvent;
import com.taskkeeper.web.domain.UserInfo;

@Controller
@RequestMapping("/addUser")
public class AddUserController {

	private static final Logger LOG = LoggerFactory
	    .getLogger(AddUserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String checkout() {
		
		return "/addUser";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doCheckout(@Valid @ModelAttribute("userInfo") UserInfo user,
	    BindingResult result, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) {
			// errors in the form
			// show the checkout form again
			return "/addUser";
		}

		LOG.debug("No errors, continue with processing for user {}:", user.getUsername());

		UserCreatedEvent userCreatedEvent = userService.createUser(new CreateUserEvent(user.toUserDetails()));
		
		LOG.debug("Cretaed new user with user id {}", userCreatedEvent.getNewId());

		return "redirect:/";
	}
	
	

	@ModelAttribute("userInfo")
	private UserInfo getUserInfo() {
		return new UserInfo();
	}

}
