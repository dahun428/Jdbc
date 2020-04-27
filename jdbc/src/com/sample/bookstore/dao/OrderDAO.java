package com.sample.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sample.bookstore.util.ConnectionUtil;
import com.sample.bookstore.util.QueryUtil;
import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Order;
import com.sample.bookstore.vo.User;

public class OrderDAO {

	public void addOrder(Order order) throws Exception{ 
		
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("order.addOrder"));
		pstmt.setString(1, order.getUser().getUserId());
		pstmt.setInt(2, order.getBook().getNo());
		pstmt.setInt(3, order.getOrderPrice());
		pstmt.setInt(4, order.getOrderAmount());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}
	public List<Order> getOrdersByUserId(String userId) throws Exception{

		List<Order> orders = new ArrayList<Order>();
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("order.getOrdersByUserId"));
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Order order = new Order();
			order.setNo(rs.getInt("order_no"));
			
			User user = new User();
			Book book = new Book();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			book.setNo(rs.getInt("book_no"));
			book.setTitle(rs.getString("book_title"));
			book.setPrice(rs.getInt("book_discount_price"));
			
			order.setUser(user);
			order.setBook(book);
			
			order.setOrderPrice(rs.getInt("order_price"));
			order.setOrderAmount(rs.getInt("order_amount"));
			order.setOrderRegisteredDate(rs.getDate("order_registered_date"));
			orders.add(order);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return orders;
	}
	public Order getOrderByNo(int orderNo) throws Exception{ 
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("order.getOrderByNo"));
		pstmt.setInt(1, orderNo);
		ResultSet rs = pstmt.executeQuery();
		Order order = null;
		
		while(rs.next()) {
			order = new Order();
			order.setNo(rs.getInt("order_no"));
			
			User user = new User();
			Book book = new Book();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			book.setNo(rs.getInt("book_no"));
			book.setTitle(rs.getString("book_title"));
			book.setPrice(rs.getInt("book_price"));
			
			order.setUser(user);
			order.setBook(book);
			order.setOrderPrice(rs.getInt("order_price"));
			order.setOrderAmount(rs.getInt("order_amount"));
			order.setOrderRegisteredDate(rs.getDate("order_registered_date"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return order;
	}

	
	
}
