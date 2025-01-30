package com.dkte.pizzashop.entities;

public class ForAllOrders {
	private int orderId;
	private String CustomerName;
	private String CustomerEmail;
	private String menuItem;
	private double price;
	
	public ForAllOrders(){
	}

	public ForAllOrders(int orderId, String customerName, String customerEmail, String menuItem, double price) {
		
		this.orderId = orderId;
		CustomerName = customerName;
		CustomerEmail = customerEmail;
		this.menuItem = menuItem;
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerEmail() {
		return CustomerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ForAllOrders [orderId=" + orderId + ", CustomerName=" + CustomerName + ", CustomerEmail="
				+ CustomerEmail + ", menuItem=" + menuItem + ", price=" + price + "]";
	}
	
	
}
