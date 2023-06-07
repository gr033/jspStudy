package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.bookVO;

import db.ConnectionProvider;

public class bookDAO {
	public int updateBook(bookVO b) {
		int re = -1;
		String sql = "update book set bookname=?, publisher=?, price=? where bookid=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBookname());
			pstmt.setString(2, b.getPublisher());
			pstmt.setInt(3, b.getPrice());
			pstmt.setInt(4, b.getBookid());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("updateBook 예외발생: "+e.getMessage());
		}
		return re;
	}
	public int deleteBook(int bookid) {
		int re = -1;
		String sql = "delete book where bookid="+bookid;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(stmt, conn);
		} catch (Exception e) {
			System.out.println("deleteBook 예외발생: "+e.getMessage());
		}
		return re;
	}
	public int insertBook(bookVO b) {
		int re = -1;
		String sql = "insert into book values(?,?,?,?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBookid());
			pstmt.setString(2, b.getBookname());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("insertBook 예외발생: "+e.getMessage());
		}
		return re;
	}
	public ArrayList<bookVO> findBook(){
		String sql = "select * from book order by bookid";
		ArrayList<bookVO> list = new ArrayList<>();
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int bookid=rs.getInt(1);
				String bookname = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);
				bookVO d = new bookVO(bookid, bookname, publisher, price);
				list.add(d);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("findBook 예외발생: "+e.getMessage());
		}
		return list;
	}
}
