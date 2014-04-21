package com.taskkeeper.rest.controller;

import com.taskkeeper.core.services.WorkItemService;
import com.taskkeeper.events.workitem.CreateWorkItemEvent;
import com.taskkeeper.rest.controller.WorkItemCommandsController;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;
import static com.taskkeeper.rest.controller.fixture.RestDataFixture.*;
import static com.taskkeeper.rest.controller.fixture.RestEventFixtures.*;

public class CreateNewOrderIntegrationTest {
  MockMvc mockMvc;

  @InjectMocks
  WorkItemCommandsController controller;

  @Mock
  WorkItemService orderService;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    when(orderService.createWorkItem(any(CreateWorkItemEvent.class))).thenReturn(
            orderCreated(UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13")));
  }
  
  @Test
  public void thatCreateOrderUsesHttpCreated() throws Exception {

    this.mockMvc.perform(
            post("/aggregators/orders")
                    .content(standardOrderJSON())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
  }
  
  @Test
  public void thatCreateOrderRendersAsJson() throws Exception {

    this.mockMvc.perform(
            post("/aggregators/orders")
                    .content(standardOrderJSON())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
              .andExpect(jsonPath("$.key").value("f3512d26-72f6-4290-9265-63ad69eccc13"));
  }
  
  @Test
  public void thatCreateOrderPassesLocationHeader() throws Exception {

    this.mockMvc.perform(
            post("/aggregators/orders")
                    .content(standardOrderJSON())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", Matchers.endsWith("/aggregators/orders/f3512d26-72f6-4290-9265-63ad69eccc13")));
  }

}
