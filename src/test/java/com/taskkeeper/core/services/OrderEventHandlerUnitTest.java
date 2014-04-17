package com.taskkeeper.core.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.taskkeeper.core.domain.Order;
import com.taskkeeper.core.domain.fixtures.OrdersFixtures;
import com.taskkeeper.core.services.OrderEventHandler;
import com.taskkeeper.events.orders.*;
import com.taskkeeper.persistence.services.OrderPersistenceService;







import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderEventHandlerUnitTest {
	
  OrderEventHandler uut;
  OrderPersistenceService mockOrderPersistenceService;
  
  @Before
  public void setupUnitUnderTest() {
    mockOrderPersistenceService = mock(OrderPersistenceService.class);
    uut = new OrderEventHandler(mockOrderPersistenceService);
  }
  
  @Test
  public void addANewOrderToTheSystem() {

    when(mockOrderPersistenceService.createOrder(any(CreateOrderEvent.class))).thenReturn(new OrderCreatedEvent(null, OrdersFixtures.standardOrderDetails()));

    CreateOrderEvent ev = new CreateOrderEvent(new OrderDetails());

    uut.createOrder(ev);

    verify(mockOrderPersistenceService).createOrder(any(CreateOrderEvent.class));
    verify(mockOrderPersistenceService).setOrderStatus(any(SetOrderStatusEvent.class));
    verifyNoMoreInteractions(mockOrderPersistenceService);
  }
  
  @Test
  public void addTwoNewOrdersToTheSystem() {

    when(mockOrderPersistenceService.createOrder(any(CreateOrderEvent.class))).thenReturn(new OrderCreatedEvent(null, OrdersFixtures.standardOrderDetails()));

    CreateOrderEvent ev = new CreateOrderEvent(new OrderDetails());

    uut.createOrder(ev);
    uut.createOrder(ev);

    verify(mockOrderPersistenceService, times(2)).createOrder(any(CreateOrderEvent.class));
    verify(mockOrderPersistenceService, times(2)).setOrderStatus(any(SetOrderStatusEvent.class));
    verifyNoMoreInteractions(mockOrderPersistenceService);
  }
  
  @Test
  public void requestAllOrdersFromTheSystem() {
  	
  	List<OrderDetails> orders = new ArrayList<OrderDetails>();
  	
  	orders.add(OrdersFixtures.standardOrderDetails());
  	
  	when(mockOrderPersistenceService.requestAllOrders(any(RequestAllOrdersEvent.class))).thenReturn(new AllOrdersEvent(orders));
  	
  	RequestAllOrdersEvent ev = new RequestAllOrdersEvent();
  	
  	AllOrdersEvent allOrdersEvent = uut.requestAllOrders(ev);
  	
  	verify(mockOrderPersistenceService).requestAllOrders(any(RequestAllOrdersEvent.class));
  	assertEquals(1, allOrdersEvent.getOrdersDetails().size());
  }

  @Test
  public void removeAnOrderFromTheSystemFailsIfNotPresent() {
    UUID key = UUID.randomUUID();
    
    OrderDetailsEvent orderDetailsEvent = new OrderDetailsEvent(key, null) {
    	@Override
    	public boolean isEntityFound() {
    		return false;
    	}    	
    };

    when(mockOrderPersistenceService.requestOrderDetails(any(RequestOrderDetailsEvent.class))).thenReturn(orderDetailsEvent);
    
    DeleteOrderEvent ev = new DeleteOrderEvent(key);
    
    OrderDeletedEvent orderDeletedEvent = uut.deleteOrder(ev);
    
    verify(mockOrderPersistenceService, never()).deleteOrder(any(DeleteOrderEvent.class));
    
    assertFalse(orderDeletedEvent.isEntityFound());
    assertFalse(orderDeletedEvent.isDeletionCompleted());
  }
  
  
  @Test
  public void removeAnOrderFromTheSystemFailsIfNotPermitted() {
    UUID key = UUID.randomUUID();
    Date date = new Date();
    date.setMinutes(date.getMinutes()-6);

    OrderDetails orderDetails = new OrderDetails(key);
    orderDetails.setDateTimeOfSubmission(date);
    
    OrderDetailsEvent orderDetailsEvent = new OrderDetailsEvent(key, orderDetails);
    
    when(mockOrderPersistenceService.requestOrderDetails(any(RequestOrderDetailsEvent.class))).thenReturn(orderDetailsEvent);
    
    DeleteOrderEvent ev = new DeleteOrderEvent(key);

    OrderDeletedEvent orderDeletedEvent = uut.deleteOrder(ev);

    verify(mockOrderPersistenceService, never()).deleteOrder(any(DeleteOrderEvent.class));

    assertTrue(orderDeletedEvent.isEntityFound());
    assertFalse(orderDeletedEvent.isDeletionCompleted());
    assertEquals(orderDetails.getDateTimeOfSubmission(), orderDeletedEvent.getDetails().getDateTimeOfSubmission());
  }
  

  @Test
  public void removeAnOrderFromTheSystemWorksIfExists() {

    UUID key = UUID.randomUUID();

    OrderDetails orderDetails = new OrderDetails(key);
    orderDetails.setDateTimeOfSubmission(new Date());
    
    OrderDetailsEvent orderDetailsEvent = new OrderDetailsEvent(key, orderDetails);

    when(mockOrderPersistenceService.requestOrderDetails(any(RequestOrderDetailsEvent.class))).thenReturn(orderDetailsEvent);

    DeleteOrderEvent ev = new DeleteOrderEvent(key);

    OrderDeletedEvent orderDeletedEvent = uut.deleteOrder(ev);

    verify(mockOrderPersistenceService).deleteOrder(any(DeleteOrderEvent.class));

    assertTrue(orderDeletedEvent.isEntityFound());
    assertTrue(orderDeletedEvent.isDeletionCompleted());
    assertEquals(orderDetails.getDateTimeOfSubmission(), orderDeletedEvent.getDetails().getDateTimeOfSubmission());
  }

}
