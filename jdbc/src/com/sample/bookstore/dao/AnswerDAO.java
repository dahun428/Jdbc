package com.sample.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sample.bookstore.util.ConnectionUtil;
import com.sample.bookstore.util.QueryUtil;
import com.sample.bookstore.vo.Answer;

public class AnswerDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public void addAnswer(Answer answer) throws Exception {
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("answer.addAnswer"));
		pstmt.setString(1, answer.getContent());
		pstmt.setInt(2, answer.getQuestionNo());
		pstmt.executeQuery();
		
		pstmt.close();
		conn.close();
		
	}
	public Answer getAnswer(int questionNo) throws Exception {
		Answer answer = null;
		conn = ConnectionUtil.getConnection();
		pstmt = conn.prepareStatement(QueryUtil.getSQL("answer.getAnswer"));
		pstmt.setInt(1, questionNo);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			answer = new Answer();
			answer.setNo(rs.getInt("answer_no"));
			answer.setContent(rs.getString("answer_content"));
			answer.setRegisteredDate(rs.getDate("answer_registered_date"));
			answer.setQuestionNo(rs.getInt("question_no"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return answer;
	}
	
	
	
}
