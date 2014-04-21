package com.taskkeeper.events.workitem;

import java.util.UUID;

import com.taskkeeper.events.ReadEvent;

public class OrderStatusEvent extends ReadEvent {

	private UUID OrderKey;
	private OrderStatusDetails orderStatus;

	private OrderStatusEvent(UUID OrderKey) {
		this.OrderKey = OrderKey;
	}

	public OrderStatusEvent(UUID OrderKey, OrderStatusDetails orderStatus) {
		this.OrderKey = OrderKey;
		this.orderStatus = orderStatus;
	}

	public UUID getOrderKey() {
		return OrderKey;
	}

	public OrderStatusDetails getOrderStatus() {
		return orderStatus;
	}

	public static OrderStatusEvent notFound(UUID OrderKey) {
		OrderStatusEvent ev = new OrderStatusEvent(OrderKey);
		ev.entityFound = false;
		return ev;
	}

}
