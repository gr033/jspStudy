package com.sist.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sist.vo.MemberVO;

import db.ConnectionProvider;

public class MemberDAO {
	public ArrayList<MemberVO> findAll(){
		ArrayList<MemberVO> list = new ArrayList<>();
		String sql = "select id, pwd, name, birth, email from member";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MemberVO v = new MemberVO();
				v.setId(rs.getString(1));
				v.setPwd(rs.getString(2));
				v.setName(rs.getString(3));
				v.setBirth(rs.getString(4));
				v.setEmail(rs.getString(5));
				list.add(v);
			}
		} catch (Exception e) {
			System.out.println("findAll 예외발생: "+e.getMessage());
		}
		return list;
	}
	public int insert(MemberVO m) {
		int re = -1;
		String sql = "insert into member values(?, ?, ?, ?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getBirth());
			pstmt.setString(5, m.getEmail());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("insert 예외발생 "+e.getMessage());
		}
		return re;
	}
	
	public boolean isMember(String id, String pwd) {
		boolean re = false;
		String sql = "select * from member where id=? and pwd=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				re = true;
			}
			ConnectionProvider.close(rs, pstmt, conn);
		} catch (Exception e) {
			System.out.println("isMember 예외발생"+e.getMessage());
		}
		return re;
	}
}
