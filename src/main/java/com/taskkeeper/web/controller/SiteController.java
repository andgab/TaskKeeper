
package com.taskkeeper.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskkeeper.core.services.OrderService;


@Controller
@RequestMapping("/")
public class SiteController {

    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCurrentMenu(Model model) {
        LOG.debug("Yummy MenuItemDetails to home view");
        model.addAttribute("OrderItems","test");
        return "/home";
    }	



}
