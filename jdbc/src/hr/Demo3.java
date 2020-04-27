package hr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Demo3 {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static String nextString() throws Exception{
		return reader.readLine();
	}
	private static int nextInt() throws Exception{
		return Integer.parseInt(reader.readLine());
	}
	
	
	
	public static void main(String[] args) throws Exception{

		
		System.out.println("[도서검색]");
		System.out.print("제목을 입력하세요 : ");
		String keyword = nextString();
		
		
		
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
			String sql = "SELECT * FROM SAMPLE_BOOKS WHERE BOOK_TITLE LIKE '%'||?||'%' ORDER BY BOOK_NO DESC";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("BOOK_NO");
				String title = rs.getString("BOOK_TITLE");
				String writer = rs.getString("BOOK_WRITE");
				String genre = rs.getString("BOOK_GENRE");
				String publisher = rs.getString("BOOK_PUBLISHER");
				int price = rs.getInt("BOOK_PRICE");
				int discountPrice = rs.getInt("BOOK_DISCOUNT_PRICE");
				java.util.Date date = rs.getDate("BOOK_REGISTERED_DATE");
				
				System.out.printf("%d,%s,%s,%s,%s,%d,%d,"+date+"\n",no,title,writer,genre,publisher,price,discountPrice);
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
