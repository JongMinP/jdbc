package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩 (외부라이브러리 jdbc-mysql 추가후)
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기 (java.sql.Connection을 사용)
			String url = "jdbc:mysql://localhost/dev"; // port 3306
			conn = DriverManager.getConnection(url, "dev", "dev"); // url,id,pw

			// 3. Statement 객체 생성
			stmt = conn.createStatement();

			// 4. SQL 실행

			// date는 올때 String으로 바껴서 오는게 좋다.
			String sql = "select name," + "	     owner," + "        species, " + "        date_format(birth,'%Y-%m-%d')"
					+ "  from pet"; // 세미콜론 x

			rs = stmt.executeQuery(sql); // select -> String 리턴
			// stmt.executeUpdate(sql); // update,delete -> int 리턴

			// 5. 결과 처리
			while (rs.next()) {
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String species = rs.getString(3);
				String birth = rs.getString(4);

				System.out.println(name + ":" + owner + ":" + species + ":" + birth);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("에러:" + e);
		} finally {

			// 자원정리(Clean - Up)
			try {

				if (rs != null)
					rs.close();

				if (stmt != null)
					stmt.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
