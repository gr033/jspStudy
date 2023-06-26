package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.db.ConnectionProvider;
import com.sist.vo.GoodsVO;

public class GoodsDAO {
	
	public static int pageSIZE=5;
	public static int totalRecord;
	public static int totalPage;	
	
	
	private static GoodsDAO dao;
	
	//외부에서 직접 생성할 수 없도록
	//생성자를 private으로 둡니다.
	private GoodsDAO() {	
	}
	
	//외부에 DAO객체를 얻기 위한 메소드를 static만들어요.
	public static  GoodsDAO getInstance() {
		
		//dao가 아직 생성되지 않았다면 생성해주고
		//만약 생성되어있다면 그것을 반환해 줍니다.
		if(dao == null) {
			dao = new GoodsDAO();
		}
		return dao;
	}
	
	public int insertGoods(GoodsVO g) {
		int re = -1;
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "insert into goods values(seq_goods.nextval,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setInt(2, g.getPrice());
			pstmt.setInt(3, g.getQty());
			pstmt.setString(4, g.getFname());
			
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	//전체레코드 수를 반환하는 메소드
	public int getTotalRecored(String keyword) {
		int cnt = 0;
		String sql = "select count(*) from goods ";
		if(keyword != "null") {
			sql += "where name like '%"+keyword+"%'";
		}
		System.out.println(sql);
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			ConnectionProvider.close(conn, stmt, rs);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return cnt;
	}
	
	
	public ArrayList<GoodsVO> findAll(int pageNUM, String keyword){
		totalRecord = getTotalRecored(keyword);
		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE); 
		
		int start = (pageNUM-1)*pageSIZE + 1;
		int end = start + pageSIZE - 1;
		
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		String sql = "select no,name,price,qty,fname  from "
				+ "( select rownum n, no,name,price,qty,fname from ( "
				+ "select * from goods ";
		
		if(keyword != null) {
			sql += "where name like '%"+keyword+"%'";
		}
		sql += "order by no) ) a where a.n between ? and ?";
		
		System.out.println("sql: "+sql);
		System.out.println("keyword: "+keyword);
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsVO g = new GoodsVO();
				g.setNo(rs.getInt(1));
				g.setName(rs.getString(2));
				g.setPrice(rs.getInt(3));
				g.setQty(rs.getInt(4));
				g.setFname(rs.getString(5));
				list.add(g);				
			}
			ConnectionProvider.close(conn, pstmt, rs);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}

	public GoodsVO findByNo(int no) {
		GoodsVO g = new GoodsVO();
		String sql = "select * from goods where no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				g.setNo(rs.getInt(1));
				g.setName(rs.getString(2));
				g.setPrice(rs.getInt(3));
				g.setQty(rs.getInt(4));
				g.setFname(rs.getString(5));
			}
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return g;
	}

	public int updateGoods(GoodsVO g) {
		int re = -1;
		String sql = "update goods set name=?,price=?,qty=?,fname=? where no=?";
		try {
			Connection conn = 
					ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setInt(2, g.getPrice());
			pstmt.setInt(3, g.getQty());
			pstmt.setString(4, g.getFname());
			pstmt.setInt(5, g.getNo());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
			
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return re;
	}

	public int deleteGoods(int no) {
		int re =  -1;
		try {
			
			String sql = "delete goods where no=?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
}
