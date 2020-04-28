package com.sample.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sample.bookstore.util.ConnectionUtil;
import com.sample.bookstore.util.QueryUtil;
import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Review;
import com.sample.bookstore.vo.User;

public class ReviewDAO {


	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public void addReview(Review review) throws Exception{
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("review.addReview"));
		pstmt.setString(1, review.getContent());
		pstmt.setInt(2, review.getReviewPoint());
		pstmt.setInt(3, review.getBook().getNo());
		pstmt.setString(4, review.getUser().getUserId());
		pstmt.executeQuery();
		
		pstmt.close();
		conn.close();
		
		
	}
	public List<Review> getReviewsByUserId(String userId) throws Exception{
		List<Review> reviews = new ArrayList<Review>();
		
		
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("review.getReviewsByUserId"));
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			Review review = new Review();
			
			Book book = new Book();
			book.setNo(rs.getInt("book_no"));
			book.setTitle(rs.getString("book_title"));
			review.setBook(book);
			
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			review.setUser(user);
			
			review.setContent(rs.getString("review_content"));
			review.setReviewPoint(rs.getInt("review_point"));
			review.setReivewRegisteredDate(rs.getDate("review_registered_date"));
			reviews.add(review);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return reviews;
	}
	
	
	public List<Review> getReviewsByBookNo(int bookNo) throws Exception{
		List<Review> reviews = new ArrayList<Review>();
		
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("review.getReviewsByBookNo"));
		pstmt.setInt(1, bookNo);
		
		rs = pstmt.executeQuery();
		while(rs.next()) {
			Review review = new Review();
			Book book = new Book();
			book.setNo(rs.getInt("book_no"));
			book.setTitle(rs.getString("book_title"));
			review.setBook(book);
			
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			review.setUser(user);
			
			review.setContent(rs.getString("review_content"));
			review.setReviewPoint(rs.getInt("review_point"));
			review.setReivewRegisteredDate(rs.getDate("review_registered_date"));
			
			reviews.add(review);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return reviews;
	}
	public void deleteReview(String userId, int reviewNo) throws Exception{
		
	}
	public void updateReview(String userId, int reviewNo) throws Exception{
		
	}
	
}
