package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.ChatVO;

import db.ConnectionProvider;

public class ChatDAO {
	public ArrayList<ChatVO> findAll(){
		ArrayList<ChatVO> list = new ArrayList<>();
		String sql = "select * from chat order by no desc";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ChatVO c = new ChatVO();
				c.setNo(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setMessage(rs.getString(3));
				list.add(c);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("findAll 예외발생"+ e.getMessage());
		}
		return list;
	}
	
	public int insert(ChatVO c) {
		int re = -1;
		String sql = "insert into chat values(seq_chatno.nextval, ?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getMessage());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("insert 예외발생: "+e.getMessage());
		}
		return re;
	}
	
}
