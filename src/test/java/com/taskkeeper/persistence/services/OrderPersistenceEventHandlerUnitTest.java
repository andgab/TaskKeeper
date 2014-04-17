package com.taskkeeper.persistence.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.taskkeeper.events.orders.*;
import com.taskkeeper.persistence.domain.Order;
import com.taskkeeper.persistence.domain.fixture.PersistenceFixture;
import com.taskkeeper.persistence.repository.OrdersRepository;
import com.taskkeeper.persistence.services.OrderPersistenceEventHandler;



public class OrderPersistenceEventHandlerUnitTest {
	
	OrderPersistenceEventHandler uut;
	OrdersRepository mockOrdersRepository;
	
	
  @Before
  public void setupUnitUnderTest() {
  	mockOrdersRepository = mock(OrdersRepository.class);
    uut = new OrderPersistenceEventHandler(mockOrdersRepository);
  }
  
  
  @Test
  public void addANewOrderToTheSystem() {

    when(mockOrdersRepository.save(any(Order.class))).thenReturn(PersistenceFixture.standardOrder());

    CreateOrderEvent ev = new CreateOrderEvent(new OrderDetails());

    uut.createOrder(ev);

    verify(mockOrdersRepository).save(any(Order.class));
    verifyNoMoreInteractions(mockOrdersRepository);
  }
  
 	

}
