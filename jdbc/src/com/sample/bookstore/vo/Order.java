package com.sample.bookstore.vo;

import java.util.Date;

public class Order {

	private int no;
	private User user;
	private Book book;
	private int orderPrice;
	private int orderAmount;
	private Date orderRegisteredDate;
	
	public Order() {}

	public void setBook(Book book) {
		this.book = book;
	}
	public Book getBook() {
		return book;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getOrderRegisteredDate() {
		return orderRegisteredDate;
	}

	public void setOrderRegisteredDate(Date orderRegisteredDate) {
		this.orderRegisteredDate = orderRegisteredDate;
	}
	
	
	
	
}
