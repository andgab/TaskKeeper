package com.taskkeeper.persistence.services;

import com.taskkeeper.events.orders.*;
import com.taskkeeper.persistence.domain.Order;
import com.taskkeeper.persistence.domain.OrderStatus;
import com.taskkeeper.persistence.repository.OrdersRepository;
//import com.taskkeeper.persistence.repository.OrderStatusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderPersistenceEventHandler implements OrderPersistenceService {

	private final OrdersRepository orderRepository;

	// private final OrderStatusRepository orderStatusRepository;

	public OrderPersistenceEventHandler(final OrdersRepository orderRepository /*,
	    final OrderStatusRepository orderStatusRepository*/) {
		
		this.orderRepository = orderRepository;
		//this.orderStatusRepository = orderStatusRepository;
	}
	
	
  @Override
  public OrderStatusEvent setOrderStatus(SetOrderStatusEvent event) {

    OrderStatus status = OrderStatus.fromStatusDetails(event.getOrderStatus());

    //status = orderStatusRepository.save(status); anga

    return new OrderStatusEvent(status.getId(), status.toStatusDetails());
  }

	
	@Override
	public OrderCreatedEvent createOrder(CreateOrderEvent createOrderEvent) {
		Order order = Order.fromOrderDetails(createOrderEvent.getDetails());
		
		order.setId(UUID.randomUUID().toString()); // anga		

		order = orderRepository.save(order);

		return new OrderCreatedEvent(UUID.fromString(order.getId()), order.toOrderDetails());
	}
	
	
  @Override
  public AllOrdersEvent requestAllOrders(RequestAllOrdersEvent requestAllCurrentOrdersEvent) {
    List<OrderDetails> generatedDetails = new ArrayList<OrderDetails>();
    for (Order order : orderRepository.findAll()) {
      generatedDetails.add(order.toOrderDetails());
    }
    return new AllOrdersEvent(generatedDetails);
  }
  
  @Override
  public OrderDetailsEvent requestOrderDetails(RequestOrderDetailsEvent requestOrderDetailsEvent) {

    Order order = orderRepository.findOne(requestOrderDetailsEvent.getKey().toString());

    if (order == null) {
      return OrderDetailsEvent.notFound(requestOrderDetailsEvent.getKey());
    }

    return new OrderDetailsEvent(
            requestOrderDetailsEvent.getKey(),
            order.toOrderDetails());
  }
  
  
  @Override
  public OrderUpdatedEvent setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
    Order order = orderRepository.findOne(setOrderPaymentEvent.getKey().toString());

    if(order == null) {
      return OrderUpdatedEvent.notFound(setOrderPaymentEvent.getKey());
    }

    //TODO, handling payment details...

    return new OrderUpdatedEvent(UUID.fromString(order.getId()), order.toOrderDetails());
  }
  
  
  @Override
  public OrderDeletedEvent deleteOrder(DeleteOrderEvent deleteOrderEvent) {

    Order order = orderRepository.findOne(deleteOrderEvent.getKey().toString());

    if (order == null) {
      return OrderDeletedEvent.notFound(deleteOrderEvent.getKey());
    }

    orderRepository.delete(deleteOrderEvent.getKey().toString());

    return new OrderDeletedEvent(deleteOrderEvent.getKey(), order.toOrderDetails());
  }
  

  @Override
  public OrderStatusEvent requestOrderStatus(RequestOrderStatusEvent requestOrderDetailsEvent) {
    //OrderStatus status = orderStatusRepository.findLatestById(requestOrderDetailsEvent.getKey());

/*    if (status == null) {
      return OrderStatusEvent.notFound(requestOrderDetailsEvent.getKey());
    }*/

    return new OrderStatusEvent(requestOrderDetailsEvent.getKey(), null/*status.toStatusDetails()*/);
  }
  

}
