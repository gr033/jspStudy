package com.sist.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context context;
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/sist");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("getConnection error: " + e.getMessage());
		}
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			System.out.println("close error: " + e.getMessage());
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			System.out.println("close error: " + e.getMessage());
		}
	}
}
