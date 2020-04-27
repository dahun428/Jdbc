package hr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Demo4 {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static String nextString() throws Exception{
		return reader.readLine();
	}
	private static int nextInt() throws Exception{
		return Integer.parseInt(reader.readLine());
	}
	
	
	
	public static void main(String[] args) throws Exception{

		
		System.out.println("책 등록하기");
		System.out.print("제목을 입력하세요  : ");
		String title = nextString();
		System.out.print("저자를 입력하세요  : ");
		String writer = nextString();
		System.out.print("장르를 입력하세요  : ");
		String genre = nextString();
		System.out.print("출판사를 입력하세요  : ");
		String publisher = nextString();
		System.out.print("가격을 입력하세요  : ");
		int price = nextInt();
		System.out.print("할인 가격을 입력하세요  : ");
		int discountPrice = nextInt();
		
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
			
			String sql = "INSERT INTO SAMPLE_BOOKS"
					+ "(BOOK_NO, BOOK_TITLE, BOOK_WRITE,BOOK_GENRE, BOOK_PUBLISHER,"
					+ "BOOK_PRICE, BOOK_DISCOUNT_PRICE, BOOK_REGISTERED_DATE)"
					+ "VALUES (SAMPLE_BOOK_SEQ.NEXTVAL, ?,?,?,?,?,?,SYSDATE)";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, genre);
			pstmt.setString(4, publisher);
			pstmt.setInt(5, price);
			pstmt.setInt(6, discountPrice);
			int count = pstmt.executeUpdate();
			System.out.printf("[%d]개의 책이 등록되었습니다.\n",count);
			
			
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
