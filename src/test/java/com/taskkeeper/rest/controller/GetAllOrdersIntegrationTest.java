package com.taskkeeper.rest.controller;


import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.RequestAllWorkItemsEvent;
import com.taskkeeper.rest.controller.WorkItemQueriesController;
import com.taskkeeper.rest.controller.fixture.RestDataFixture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.taskkeeper.rest.controller.fixture.RestDataFixture.allWorkItems;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public class GetAllOrdersIntegrationTest {
	
  MockMvc mockMvc;

  @InjectMocks
  WorkItemQueriesController controller;

  @Mock
  WorkItemService orderService;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller).build();

    when(orderService.requestAllWorkItems(any(RequestAllWorkItemsEvent.class))).thenReturn(allWorkItems());
  } 
  
  
  @Test
  public void thatGetOrdersRendersAsJson() throws Exception {

    this.mockMvc.perform(
            get("/aggregators/orders")
              .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[0].items['" + RestDataFixture.YUMMY_ITEM + "']").value(12));
  }

}
