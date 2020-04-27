package com.sample.bookstore.vo;

import java.util.Date;

public class User {
	
	private String userId;
	private String password;
	private String userName;
	private String email;
	private int point;
	private Date userRegisteredDate;
	
	public User() {}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getUserRegisteredDate() {
		return userRegisteredDate;
	}

	public void setUserRegisteredDate(Date userRegisteredDate) {
		this.userRegisteredDate = userRegisteredDate;
	}
	
	
	
}
