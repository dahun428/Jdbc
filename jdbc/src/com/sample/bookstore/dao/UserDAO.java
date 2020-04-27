package com.sample.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sample.bookstore.util.ConnectionUtil;
import com.sample.bookstore.util.QueryUtil;
import com.sample.bookstore.vo.User;

public class UserDAO {
	
	public void addUser(User user) throws SQLException{
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("user.addUser"));
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getUserName());
		pstmt.setString(4, user.getEmail());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}
	public User getUserById(String userId) throws Exception{
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("user.getUserById"));
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		
		User user = null;
		while(rs.next()) {
			user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("user_password"));
			user.setUserName(rs.getString("user_name"));
			user.setEmail(rs.getString("user_email"));
			user.setPoint(rs.getInt("user_point"));
			user.setUserRegisteredDate(rs.getDate("user_registered_date"));
		
		}
		rs.close();
		pstmt.close();
		conn.close();
		return user;
	}
	public void updateUser(User user) throws Exception{
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("user.updateUser"));
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getUserId());
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}
	
	
}
