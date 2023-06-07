package com.sist.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.sist.vo.deptVO;
import com.sist.vo.empVO;

import db.ConnectionProvider;

public class deptDAO {
	public int updateDept(deptVO d) {
		String sql = "update dept set dname=?, dloc=? where dno=?";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDname());
			pstmt.setString(2, d.getDloc());
			pstmt.setInt(3, d.getDno());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("updateDept 예외발생: "+e.getMessage());
		}
		return re;
	}
	public int deleteDept(int dno) {
		int re = -1;
		String sql = "delete dept where dno="+dno;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(stmt, conn);
		} catch (Exception e) {
			System.out.println("deleteDept 예외발생: "+e.getMessage());
		}
		return re;
	}
	public int insertDept(deptVO d) {
		String sql = "insert into dept values(?,?,?)";
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d.getDno());
			pstmt.setString(2, d.getDname());
			pstmt.setString(3, d.getDloc());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("updateDept 예외발생: "+e.getMessage());
		}
		return re;
	}
	public ArrayList<deptVO> findAll(){
		ArrayList<deptVO> list = new ArrayList<>();
		String sql = "select * from dept order by dno";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##madang", "madang");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int dno = rs.getInt(1);
				String dname = rs.getString(2);
				String dloc = rs.getString(3);
				list.add(new deptVO(dno, dname, dloc));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("findAll 예외발생: "+e.getMessage());
		}
		return list;
	}
}