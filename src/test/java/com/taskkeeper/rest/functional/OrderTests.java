package com.taskkeeper.rest.functional;

import com.taskkeeper.rest.controller.fixture.RestDataFixture;
import com.taskkeeper.rest.domain.WorkItem;

import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class OrderTests {

  @Test
  public void thatOrdersCanBeAddedAndQueried() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    RestTemplate template = new RestTemplate();

    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardOrderJSON(),headers);

    ResponseEntity<WorkItem> entity = template.postForEntity(
        "http://localhost:8080/aggregators/orders",
        requestEntity, WorkItem.class);

    String path = entity.getHeaders().getLocation().getPath();

    assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    assertTrue(path.startsWith("/aggregators/orders/"));
    WorkItem order = entity.getBody();

    System.out.println ("The WorkItemInfo ID is " + order.getId());
    System.out.println ("The Location is " + entity.getHeaders().getLocation());

    assertEquals(2, order.getItems().size());
  }
}