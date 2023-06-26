package com.sist.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.sist.vo.EmpVO;

import db.ConnectionProvider;

public class EmpDAO {
	public EmpVO findbyName(int eno){
		EmpVO list = new EmpVO();
		String sql = "select * from emp where eno="+eno;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String ename = rs.getString(2);
				int dno = rs.getInt(3);
				int salary = rs.getInt(4);
				int comm = rs.getInt(5);
				Date hiredate = rs.getDate(6);
				String phone = rs.getString(7);
				String addr = rs.getString(8);
				int mgr = rs.getInt(9);
				String job = rs.getString(10);
				String email = rs.getString(11);
				String jumin = rs.getString(12);
				list = new EmpVO(eno, ename, dno, salary, comm, hiredate, phone, addr, mgr, job, email, jumin);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("findbyName 예외발생: "+e.getMessage());
		}
		return list;
	}
	public ArrayList<EmpVO> findbyDno (int d_dno){
		ArrayList<EmpVO> list = new ArrayList<>();
		String sql = "select * from emp where dno="+d_dno+" order by eno";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int eno = rs.getInt(1);
				String ename = rs.getString(2);
				int dno = rs.getInt(3);
				int salary = rs.getInt(4);
				int comm = rs.getInt(5);
				Date hiredate = rs.getDate(6);
				String phone = rs.getString(7);
				String addr = rs.getString(8);
				int mgr = rs.getInt(9);
				String job = rs.getString(10);
				String email = rs.getString(11);
				String jumin = rs.getString(12);
				EmpVO v = new EmpVO(eno, ename, dno, salary, comm, hiredate, phone, addr, mgr, job, email, jumin);
				list.add(v);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("listEmp 예외발생: "+e.getMessage());
		}
		return list;
	}
}
