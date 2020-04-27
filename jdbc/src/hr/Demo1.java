package hr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo1 {
	public static void main(String[] args) {

		//1.JDBC 드라이버를 JVM의 드라이버 레지스트리에 등록하기
		//2.데이터베이스와 연결을 담당하는 Connection 객체 획득하기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password ="zxcv1234";
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(url, user, password);
			
			String query1 = "select * from employees where employee_id = ?";
			pstmt = connection.prepareStatement(query1);
			pstmt.setInt(1, 100);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("first_name");
				Date date = rs.getDate("hire_date");
				String job_id = rs.getString("job_id");
				
				System.out.println(name +","+date+","+job_id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
