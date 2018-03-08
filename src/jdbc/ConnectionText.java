package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionText {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// 1. 드라이버 로딩 (외부라이브러리 jdbc-mysql 추가후)
			Class.forName("com.cafe24.jdbc.MyDriver");
			// Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기 (java.sql.Connection을 사용)
			String url = "jdbc:mysql://localhost/dev"; // port 3306
			conn = DriverManager.getConnection(url,null); 
//			conn = DriverManager.getConnection(url, "dev", "dev"); // url,id,pw

			System.out.println("연결 성공");
			

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("에러:" + e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
