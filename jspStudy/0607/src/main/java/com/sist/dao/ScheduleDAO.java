package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.ScheduleVO;

import db.ConnectionProvider;

public class ScheduleDAO {
	public  ArrayList<String> listDate(String date) {
		ArrayList<String> list = new ArrayList<>();
		String sql = "select distinct to_char(event_date,'dd') from schedule "
				+ "where to_char(event_date, 'yyyy/mm') = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			ConnectionProvider.close(rs, pstmt, conn);
			} catch (Exception e) {
			System.out.println("listDate 예외발생: "+e.getMessage());
		}
		return list;
	}
	public int deleteSchedule(int no) {
		int re = -1;
		String sql = "delete schedule where no="+no;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(stmt, conn);
		} catch (Exception e) {
			System.out.println("deleteSchedule 예외발생: "+e.getMessage());
		}
		return re;
	}
	public ArrayList<ScheduleVO> findSchedule(String date){
		ArrayList<ScheduleVO> list = new ArrayList<>();
		String sql = "select * from schedule where event_date=to_date(?,'yyyy/mm/dd')";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt(1);
				String content = rs.getString(3);
				ScheduleVO s = new ScheduleVO(no, date, content);
				list.add(s);
			}
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("findSchedule 예외발생: "+e.getMessage());
		}
		return list;
	}
	
	
	public int updateSchedule(String date, String content) {
		int re = -1;
		String sql = "insert into schedule values(seq_schedule.nextval, "
				+ "to_date(?,'yyyy/mm/dd'),?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, content);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(pstmt, conn);
		} catch (Exception e) {
			System.out.println("updateSchedule 예외발생: "+e.getMessage());
		}
				
		return re;
	}
}
