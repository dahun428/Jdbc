package com.sample.bookstore.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.sample.bookstore.dao.AnswerDAO;
import com.sample.bookstore.dao.BookDAO;
import com.sample.bookstore.dao.OrderDAO;
import com.sample.bookstore.dao.QuestionDAO;
import com.sample.bookstore.dao.UserDAO;
import com.sample.bookstore.vo.Answer;
import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Order;
import com.sample.bookstore.vo.Question;
import com.sample.bookstore.vo.User;


public class BookstoreService {

	UserDAO userDao = new UserDAO();
	BookDAO bookDao = new BookDAO();
	OrderDAO orderDao = new OrderDAO();
	QuestionDAO questionDao = new QuestionDAO();
	AnswerDAO answerDao = new AnswerDAO();
	
	public boolean newAddQuestion(Question question) throws Exception {
		User user = userDao.getUserById(question.getUser().getUserId());
		if(user == null) {
			return false;
		}
		questionDao.addQuestion(question);
		return true;
	}
	public boolean newAddAnswer(int questionNo, String content) throws Exception{
		Answer answer = answerDao.getAnswer(questionNo);
		if(answer != null) {
			return false;
			
		} else {
			
			answer = new Answer();
			answer.setQuestionNo(questionNo);
			answer.setContent(content);
			answerDao.addAnswer(answer);
		}
		
		
		return true;
	}
	public List<Question> getAllQuestion() throws Exception{
		List<Question> questions = questionDao.getAllQuestions();
		return questions;
	}
	public Question getQuestion(int questionNo) throws Exception{
		Question question = questionDao.getQuestionByNo(questionNo);
		return question;
	}
	public boolean removeQuestion(int questionNo, String userId) throws Exception{
		User user = userDao.getUserById(userId);
		if(user == null) {
			return false;
		}
		questionDao.removeQuestion(questionNo, userId);
		return true;
	}
	public Answer getAnswer(int questionNo) throws Exception{
		Answer answer = answerDao.getAnswer(questionNo);
		return answer;
	}
	
	public boolean newAddOrder(String userId, int bookNo, int amount) throws Exception{
		User user = userDao.getUserById(userId);
		if (user == null) {
			return false;
		}
		Book book = bookDao.getBookByNo(bookNo);
		if (book == null) {
			return false;
		}
		if(book.getStock() < amount) {
			return false;
		}
		Order order = new Order();
		order.setUser(user);
		order.setBook(book);
		order.setOrderAmount(amount);
		order.setOrderPrice(book.getDiscountPrice());
		orderDao.addOrder(order);
		
		book.setStock(book.getStock()-amount);
		bookDao.updateBook(book);
		
		int depositPoint = (int)(book.getDiscountPrice() * amount *0.3);
		user.setPoint(user.getPoint()+depositPoint);
		userDao.updateUser(user);
		
		return true;
	}
	
	
	public boolean isDuplicatedUser(User user) throws Exception{
		
		User savedUser = userDao.getUserById(user.getUserId());
		if(savedUser != null) {
			return false;
		}
		return true;
	}
	public List<Book> getBookByKeyword(String keyword) throws Exception{
		List<Book> books = bookDao.getBookByTitle(keyword);
		
		return books;
	}
	
	
	public Book getBook(int bookNo) throws Exception{
		Book book = bookDao.getBookByNo(bookNo);
		return book;
	}
	
	
	
	public void addNewUser(User user) throws Exception {
		//비밀번호 암호화하기 //보통 sh256사용
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		userDao.addUser(user);
	}
	
	public void addNewOrder(Order order) throws Exception { 
		orderDao.addOrder(order);
	}
	public User getUser(String userId) throws Exception{
		User user = userDao.getUserById(userId);
		
		return user;
	}
	public void updateUser(User user) throws Exception{
		userDao.updateUser(user);
	}
	
	public List<Order> getMyOrders(String userId) throws Exception { 
		List<Order> orders = orderDao.getOrdersByUserId(userId);
		
		return orders;
	}
	public Order getOrder(int orderNo) throws Exception{ 
		 Order order = orderDao.getOrderByNo(orderNo);
		return order;
	}
	
}
