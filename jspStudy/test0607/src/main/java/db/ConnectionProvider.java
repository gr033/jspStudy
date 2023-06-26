package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionProvider {
	//jdbc 드라이버를 메모리로 로드하고 db서버에 연결한 connection 객체를 반환하는 class메소드
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##madang", "madang");
		}catch(Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return conn;
	}
	public static void close (ResultSet rs,Statement stmt,Connection conn){
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
	}
	public static void close (Statement stmt,Connection conn){
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
}
}
