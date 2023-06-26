package com.sist.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db.ConnectionProvider;

public class MemberDAO {
	private static MemberDAO dao;
	private MemberDAO() {
	}
	public static MemberDAO getInstance() {
		if(dao==null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	//id와 암호를 매개변수로 전달받아 올바른 회원인지 판별하는 메소드
	//-1: 아이디조차 없을 때
	//0: 암호가 틀렸을 때
	//1: 올바른 회원일 경우
	public int isMember(String id, String pwd) {
		int re = -1;
		String sql = "select pwd from member where id='"+id+"'";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String dbPwd = rs.getString(1);
				if(dbPwd.equals(pwd)) {
					re=1;
				}else {
					re=0;
				}
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("isMember error: "+e.getMessage());
		}
		return re;
	}
	
	
}
