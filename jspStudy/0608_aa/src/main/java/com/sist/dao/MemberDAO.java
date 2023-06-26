package com.sist.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	//커넥션 풀을 이용하여 아이디와 암호를 매개변수로 전달받아 올바른 회원이면 true, 
	//그렇지 않으면 false를 반환하는 isMember 메소드를 정의
	public boolean isMember(String id, String pwd) {
		boolean flag = false;
		String sql = "select * from member where id=? and pwd=?";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/sist");
			Connection conn =  ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			rs.close();
			pstmt.close();
			conn.close();
			} catch (Exception e) {
			System.out.println("isMember 예외발생: "+e.getMessage());
		}
		return flag;
	}
}
