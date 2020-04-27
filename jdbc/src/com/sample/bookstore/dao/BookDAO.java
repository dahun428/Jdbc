package com.sample.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sample.bookstore.util.ConnectionUtil;
import com.sample.bookstore.util.QueryUtil;
import com.sample.bookstore.vo.Book;

public class BookDAO {

	/**
	 * 전달 받은 책 정보를 데이터베이스에 저장한다.
	 * @param book 등록할 책 정보를 포함하고 있는 Book 객체
	 * @throws Exception
	 */
	public void addBook(Book book) throws Exception{
	
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.addBook"));
		pstmt.setString(1, book.getTitle());
		pstmt.setString(2, book.getWriter());
		pstmt.setString(3, book.getGenre());
		pstmt.setString(4, book.getPublisher());
		pstmt.setInt(5, book.getPrice());
		pstmt.setInt(6, book.getDiscountPrice());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}
	public List<Book> getAllBooks()	throws Exception {
		
		List<Book> books = new ArrayList<Book>();
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.getAllBooks"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			
			books.add(resultSetToBook(rs));
		}
		rs.close();
		pstmt.close();
		conn.close();
		return books;
	}
	
	public Book getBookByNo(int bookNo) throws Exception{
		
		Book book = null;
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.getBookByNo"));
		pstmt.setInt(1, bookNo);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			book = resultSetToBook(rs);
		}
				
		return book;
	}
	public List<Book> getBookByTitle(String title) throws Exception{
		List<Book> books = new ArrayList<Book>();
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.getBookByTitle"));
		pstmt.setString(1, title);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			books.add(resultSetToBook(rs));
		}
		rs.close();
		pstmt.close();
		conn.close();
		return books;
	}
	public List<Book> getBooksByGenre(String genre) throws Exception{
		List<Book> books = new ArrayList<Book>();
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.getBooksByGenre"));
		pstmt.setString(1, genre);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			books.add(resultSetToBook(rs));
		}
		rs.close();
		pstmt.close();
		conn.close();
		return books;
	}
	public List<Book> getBooksByPriceRange(int minPrice, int maxPrice) throws Exception{
		List<Book> books = new ArrayList<Book>();
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.getBooksByPriceRange"));
		pstmt.setInt(1, minPrice);
		pstmt.setInt(2, maxPrice);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			Book book = resultSetToBook(rs);
			books.add(book);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return books;
	}
	public void removeBookByNo(int bookNo) throws Exception {
		
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.removeBookByNo"));
		pstmt.setInt(1, bookNo);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public void updateBook(Book book) throws Exception {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("book.updateBook"));
		pstmt.setString(1, book.getTitle());
		pstmt.setString(2, book.getWriter());
		pstmt.setString(3, book.getGenre());
		pstmt.setInt(4, book.getDiscountPrice());
		pstmt.setString(5, book.getPublisher());
		pstmt.setInt(6, book.getPrice());
		pstmt.setInt(7, book.getStock());
		pstmt.setInt(8, book.getNo());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();

	}
	private Book resultSetToBook(ResultSet rs) throws SQLException{
		Book book = new Book();
		book.setNo(rs.getInt("book_no"));
		book.setTitle(rs.getString("book_title"));
		book.setWriter(rs.getString("book_write"));
		book.setGenre(rs.getString("book_genre"));
		book.setPublisher(rs.getString("book_publisher"));
		book.setPrice(rs.getInt("book_price"));
		book.setDiscountPrice(rs.getInt("book_discount_price"));
		book.setRegisteredDate(rs.getDate("book_registered_date"));
		book.setStock(rs.getInt("book_stock"));
		return book;
	}
	
	
}
