package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {

	public static void main(String[] args) {
		insert("성탄이", "박종민", "개", "M", "2010-05-01", null);
	}

	public static void insert(String name, String owner, String species, String gender, String birth, String death) {

		Connection conn = null;
		PreparedStatement pstmt = null; // jdbc에 준비 시켜 놓고
		// app 에서 바인딩

		try {
			// 1. 드라이버 로딩 (외부라이브러리 jdbc-mysql 추가후)
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기 (java.sql.Connection을 사용)
			String url = "jdbc:mysql://localhost/dev"; // port 3306
			conn = DriverManager.getConnection(url, "dev", "dev"); // url,id,pw

			// 3. SQL문 준비

			String sql = 
					"insert" + 
					"	into pet" + 
					"values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			// 4. 데이터 바인딩(binding)
			pstmt.setString(1, name);
			pstmt.setString(2, owner);
			pstmt.setString(3, species);
			pstmt.setString(4, gender);
			pstmt.setString(5, birth);
			pstmt.setString(6, death);

			int count = pstmt.executeUpdate();

			if (count == 0) {
				System.out.println("실패");
			} else {
				System.out.println("성공");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("에러:" + e);
		} finally {

			// 자원정리(Clean - Up)
			try {

				if (pstmt != null)
					pstmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
