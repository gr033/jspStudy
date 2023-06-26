package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.jasper.compiler.JspUtil;

import com.sist.vo.BoardVO;

public class BoardDAO {
	public int updateBoard(BoardVO b) {
		int re = -1;
		String sql = "update board set title=?, content=?, fname=? where no=? and pwd=?";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/sist");
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getFname());
			pstmt.setInt(4, b.getNo());
			pstmt.setString(5, b.getPwd());
			re = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("updateBoard error: "+e.getMessage());
		}
		System.out.println(b);
		return re;
	}
	public int deleteBoard(int no, String pwd) {
		int re = -1;
		String sql = "delete board where no="+no+"and pwd="+pwd;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/sist");
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("deleteboard error: "+e.getMessage());
		}
		return re;
	}
	public BoardVO selectBoard(int no) {
		BoardVO b = new BoardVO();
		String sql = "select * from board where no="+no;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/sist");
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
			DataSource ds = (DataSource)context.lookup("java:/comp/env/sist");
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
			System.out.println("2. 컨트롤러로 이동");
		} catch (Exception e) {
			System.out.println("selectAll error: "+e.getMessage());
		}
		return list;
	}
	
	public int insertBoard(BoardVO b) {
		int re = -1;
		String sql = "insert into board values(seq_board.nextval,?,?,?,?,sysdate,0,?)";
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/sist");
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getWriter());
			pstmt.setString(3, b.getPwd());
			pstmt.setString(4, b.getContent());
			pstmt.setString(5, b.getFname());
			re = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("insertboard error: "+e.getMessage());
		}
		return re;
	}
}
