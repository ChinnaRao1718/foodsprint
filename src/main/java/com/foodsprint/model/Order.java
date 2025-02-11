package com.foodsprint.model;

import java.util.Date;

public class Order {
	
	private int orderId;
    private int userId;
    private int restaurentId;
    private Date orderDate;
    private int totalAmount;
    private String status;
    private String paymentMode;
    
    public Order() {
    	
    }

	public Order(int orderId, int userId, int restaurentId, Date orderDate, int totalAmount, String status,
			String paymentMode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restaurentId = restaurentId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurentId() {
		return restaurentId;
	}

	public void setRestaurentId(int restaurentId) {
		this.restaurentId = restaurentId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
    

}
