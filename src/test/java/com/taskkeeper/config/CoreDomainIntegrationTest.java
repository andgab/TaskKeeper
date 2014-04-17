package com.taskkeeper.config;

import com.taskkeeper.config.CoreConfig;
import com.taskkeeper.config.JPAConfiguration;
import com.taskkeeper.config.PersistenceConfig;
import com.taskkeeper.config.StandaloneDataConfig;
import com.taskkeeper.core.services.OrderService;
import com.taskkeeper.events.orders.AllOrdersEvent;
import com.taskkeeper.events.orders.CreateOrderEvent;
import com.taskkeeper.events.orders.OrderDetails;
import com.taskkeeper.events.orders.RequestAllOrdersEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StandaloneDataConfig.class, JPAConfiguration.class, PersistenceConfig.class, CoreConfig.class})
public class CoreDomainIntegrationTest {
	
  @Autowired
  OrderService orderService;
  
  @Test
  public void addANewOrderToTheSystem() {

    CreateOrderEvent ev = new CreateOrderEvent(new OrderDetails());

    orderService.createOrder(ev);

    AllOrdersEvent allOrders = orderService.requestAllOrders(new RequestAllOrdersEvent());

    assertEquals(1, allOrders.getOrdersDetails().size());
  }

}
