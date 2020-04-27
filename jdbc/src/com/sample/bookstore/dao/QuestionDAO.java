package com.sample.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sample.bookstore.util.ConnectionUtil;
import com.sample.bookstore.util.QueryUtil;
import com.sample.bookstore.vo.Answer;
import com.sample.bookstore.vo.Question;
import com.sample.bookstore.vo.User;

public class QuestionDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public void addQuestion(Question question) throws Exception {
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("question.addQuestion"));
		pstmt.setString(1, question.getTitle());
		pstmt.setString(2, question.getUser().getUserId());
		pstmt.setString(3, question.getContent());
		pstmt.setString(4, question.getType());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
	}
	public List<Question> getAllQuestions() throws Exception{
		List<Question> questions = new ArrayList<Question>();
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(QueryUtil.getSQL("question.getAllQuestions"));
		ResultSet rs = pstmt.executeQuery(); 
		while(rs.next()) {
			Question question = new Question();
			question.setNo(rs.getInt("QUESTION_NO"));
			question.setTitle(rs.getString("question_title"));
			question.setContent(rs.getString("question_content"));
			
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			question.setUser(user);
			
			Answer answer = new Answer();
			answer.setNo(rs.getInt("answer_no"));
			answer.setContent(rs.getString("answer_content"));
			question.setAnswer(answer);
			
			question.setViewCount(rs.getInt("question_view_count"));
			question.setRegisteredDate(rs.getDate("question_registered_date"));
			question.setStatus(rs.getString("question_status"));
			question.setType(rs.getString("question_type"));
						
			questions.add(question);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return questions;
	}
	public Question getQuestionByNo(int questionNo) throws Exception{
		Question question = null;
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("question.getQuestionByNo"));
		pstmt.setInt(1, questionNo);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			question = new Question();
			question.setNo(rs.getInt("QUESTION_NO"));
			question.setTitle(rs.getString("question_title"));
			question.setContent(rs.getString("question_content"));
			
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			question.setUser(user);
			
			Answer answer = new Answer();
			answer.setNo(rs.getInt("answer_no"));
			answer.setContent(rs.getString("answer_content"));
			question.setAnswer(answer);
			
			question.setRegisteredDate(rs.getDate("question_registered_date"));
			question.setStatus(rs.getString("question_status"));
			question.setType(rs.getString("question_type"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return question;
	}
	public void removeQuestion(int questionNo, String userId) throws Exception {
		
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("question.removeQuestion"));
		pstmt.setInt(1, questionNo);
		pstmt.setString(2, userId);
		pstmt.executeQuery();
		pstmt.close();
		conn.close();
		
	}
	
	
}
