package com.taskkeeper.rest.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.RequestWorkItemEvent;
import com.taskkeeper.rest.controller.WorkItemQueriesController;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static com.taskkeeper.rest.controller.fixture.RestDataFixture.*;
import static com.taskkeeper.rest.controller.fixture.RestEventFixtures.*;

public class ViewOrderIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	WorkItemQueriesController controller;

	@Mock
	WorkItemService orderService;

	UUID key = UUID.fromString("9e67aa30-ba2f-11e3-a5e2-0800200c9a66");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).setMessageConverters(
		    new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void thatViewOrderUsesHttpNotFound() throws Exception {

		when(orderService.requestWorkItem(any(RequestWorkItemEvent.class)))
		    .thenReturn(orderDetailsNotFound(key));

		this.mockMvc
		    .perform(
		        get("/aggregators/orders/{id}", key.toString()).accept(
		            MediaType.APPLICATION_JSON)).andDo(print())
		    .andExpect(status().isNotFound());
	}

	@Test
	public void thatViewOrderUsesHttpOK() throws Exception {

		when(orderService.requestWorkItem(any(RequestWorkItemEvent.class)))
		    .thenReturn(orderDetailsEvent(key));

		this.mockMvc.perform(
		    get("/aggregators/orders/{id}", key.toString()).accept(
		        MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void thatViewOrderRendersCorrecty() throws Exception {

		when(orderService.requestWorkItem(any(RequestWorkItemEvent.class)))
		    .thenReturn(orderDetailsEvent(key));

		this.mockMvc
		    .perform(
		        get("/aggregators/orders/{id}", key.toString()).accept(
		            MediaType.APPLICATION_JSON))
		    .andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
		    .andExpect(jsonPath("$.key").value(key.toString()));
	}

}
