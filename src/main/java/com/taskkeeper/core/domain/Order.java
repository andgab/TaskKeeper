package com.taskkeeper.core.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.taskkeeper.events.orders.OrderDetails;

public class Order {

	private final Date dateTimeOfSubmission;
	private List<MenuItem> menuItems;
	private final UUID key;
	private Customer customer;
	
	
	private OrderStatus status;
	private List<OrderStatus> statusHistory;
	
	private Date expectedCompletionTime;
	private BigDecimal totalCost;
	
	//currently 5 minutes
	private final static long ACCEPT_CANCEL_TIME = 1000 * 60 * 5;
	
	
	public Order(final Date dateTimeOfSubmission) {
		this.key = UUID.randomUUID();
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		statusHistory = new ArrayList<OrderStatus>();
	}

	public Date getExpectedCompletionTime() {
		return expectedCompletionTime;
	}
	
	public void setExpectedCompletionTime(Date expectedCompletionTime) {
		this.expectedCompletionTime = expectedCompletionTime;
	}
	
  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost) {
    this.totalCost = totalCost;
  }	
	
	public void addStatus(OrderStatus newStatus) {
		statusHistory.add(newStatus);
		status = newStatus;
	}	
	
	public OrderStatus getStatus() {
		return status;
	}
	
	
	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}
	
	public UUID getKey() {
		return key;
	}
	
  public List<MenuItem> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }
	
	public boolean canBeDeleted() {
		// accept cancellation if within 5 minutes of placing
		return System.currentTimeMillis() - dateTimeOfSubmission.getTime() < ACCEPT_CANCEL_TIME;
	}
	
  public Order withMenuItems(List<MenuItem> menuItems) {
    this.menuItems = menuItems;
    return this;
  }
	
  public OrderDetails toOrderDetails() {
    OrderDetails details = new OrderDetails();

    details.setDateTimeOfSubmission(this.getDateTimeOfSubmission());
    details.setKey(this.getKey());

    return details;
  }


  public static Order fromOrderDetails(OrderDetails orderDetails) {
    Order order = new Order(orderDetails.getDateTimeOfSubmission());

    BeanUtils.copyProperties(orderDetails, order);

    return order;
  }
	
}
