package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.BoardVO;

public class BoardDAO {
	public BoardVO selectBoard(int no) {
		BoardVO b = new BoardVO();
		String sql = "select * from board where no="+no;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/sist");
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String pwd = rs.getString(4);
				String content = rs.getString(5);
				String regdate = rs.getString(6);
				int hit = rs.getInt(7);
				String fname = rs.getString(8);
				b = new BoardVO(no, title, writer, pwd, content, regdate, hit, fname);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("selectBoard error: "+e.getMessage());
		}
		return b;
	}
	public ArrayList<BoardVO> selectAll(){
		ArrayList<BoardVO> list = new ArrayList<>();
		String sql = "select * from board";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/sist");
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String pwd = rs.getString(4);
				String content = rs.getString(5);
				String regdate = rs.getString(6);
				int hit = rs.getInt(7);
				String fname = rs.getString(8);
				BoardVO b = new BoardVO(no, title, writer, pwd, content, regdate, hit, fname);
				list.add(b);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("selectAll error: "+e.getMessage());
		}
		return list;
	}
	
	public int insertBoard(String title, String writer, String pwd, String content, String fname) {
		int re = -1;
		String sql = "insert into board values(seq_board.nextval,?,?,?,?,sysdate,0,?)";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/sist");
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, pwd);
			pstmt.setString(4, content);
			pstmt.setString(5, fname);
			re = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("insertboard error: "+e.getMessage());
		}
		return re;
	}
}
