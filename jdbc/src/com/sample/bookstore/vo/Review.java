package com.sample.bookstore.vo;

import java.util.Date;

public class Review {

	private int no;
	private String content;
	private int reviewPoint;
	private Date reivewRegisteredDate;
	private Book book;
	private User user;
	
	public Review() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReviewPoint() {
		return reviewPoint;
	}

	public void setReviewPoint(int reviewPoint) {
		this.reviewPoint = reviewPoint;
	}

	public Date getReivewRegisteredDate() {
		return reivewRegisteredDate;
	}

	public void setReivewRegisteredDate(Date reivewRegisteredDate) {
		this.reivewRegisteredDate = reivewRegisteredDate;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
