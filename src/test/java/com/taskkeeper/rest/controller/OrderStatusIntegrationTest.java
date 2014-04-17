package com.taskkeeper.rest.controller;

import com.taskkeeper.core.services.OrderService;
import com.taskkeeper.events.orders.RequestOrderStatusEvent;
import com.taskkeeper.rest.controller.OrderStatusController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.taskkeeper.rest.controller.fixture.RestEventFixtures.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class OrderStatusIntegrationTest {
	MockMvc mockMvc;

	@InjectMocks
	OrderStatusController controller;

	@Mock
	OrderService orderService;

	UUID orderKey = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");
	UUID orderStatusKey = UUID.fromString("243537a0-bf16-11e3-b1b6-0800200c9a66");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).setMessageConverters(
		    new MappingJackson2HttpMessageConverter(),
		    new Jaxb2RootElementHttpMessageConverter()).build();
	}

	@Test
	public void thatViewOrderStatusUsesHttpNotFound() throws Exception {

		when(orderService.requestOrderStatus(any(RequestOrderStatusEvent.class)))
		    .thenReturn(orderStatusNotFound(orderKey));

		this.mockMvc.perform(
		    get("/aggregators/orders/{id}/status", orderKey.toString()).accept(
		        MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	
  @Test
  public void thatViewOrderUsesHttpOK() throws Exception {

    when(orderService.requestOrderStatus(any(RequestOrderStatusEvent.class))).thenReturn(
            orderStatus(orderKey, orderStatusKey, "Cooking"));

    this.mockMvc.perform(
            get("/aggregators/orders/{id}/status", orderKey.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
  
  @Test
  public void thatViewOrderRendersJSONCorrectly() throws Exception {

    when(orderService.requestOrderStatus(any(RequestOrderStatusEvent.class))).thenReturn(
            orderStatus(orderKey, orderStatusKey, "Cooking"));

    this.mockMvc.perform(
            get("/aggregators/orders/{id}/status", orderKey.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.orderId").value(orderKey.toString()))
            .andExpect(jsonPath("$.status").value("Cooking"));
  }
  
  @Test
  public void thatViewOrderRendersXMLCorrectly() throws Exception {

    when(orderService.requestOrderStatus(any(RequestOrderStatusEvent.class))).thenReturn(
            orderStatus(orderKey, orderStatusKey, "Cooking"));

    this.mockMvc.perform(
            get("/aggregators/orders/{id}/status", orderKey.toString())
                    .accept(MediaType.TEXT_XML))
            .andDo(print())
            .andExpect(content().contentType(MediaType.TEXT_XML))
            .andExpect(xpath("/orderStatus/orderId").string(orderKey.toString()))
            .andExpect(xpath("/orderStatus/status").string("Cooking"));
  }

}
