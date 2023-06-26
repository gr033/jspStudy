package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.db.ConnectionProvider;
import com.sist.vo.GoodsVO;

public class GoodsDAO {
	private static GoodsDAO dao;
	
	//외부에서 직접 생성할 수 없도록 생성자를 private으로 둔다.
	private GoodsDAO() {
	}
	
	//외부에서 DAO객체를 얻기 위한 메소드를 static으로 만든다.
	public static GoodsDAO getInstance() {
		
		//dao가 아직 생성되지 않았다면 생성해주고
		//만약 생성되어 있다면 그것을 반환한다.
		if(dao == null) dao=new GoodsDAO();
		return dao;
	}
	
	public int deleteGoods(int no) {
		int re = -1;
		String sql = "delete goods where no="+no;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			re = stmt.executeUpdate(sql);
			ConnectionProvider.close(conn, stmt);
		} catch (Exception e) {
			System.out.println("deleteGoods error: "+e.getMessage());
		}
		return re;
	}
	
	public int updateGoods(GoodsVO g) {
		int re = -1;
		String sql = "update goods set name=?, price=?, qty=?, fname=? where no=?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setInt(2, g.getPrice());
			pstmt.setInt(3, g.getQty());
			pstmt.setString(4, g.getFname());
			pstmt.setInt(5, g.getNo());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("updateGoods error: "+e.getMessage());
		}
		return re;
	}
	
	public GoodsVO findByNo(int no) {
		String sql = "select * from goods where no="+no;
		GoodsVO g = null;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int qty = rs.getInt(4);
				String fname = rs.getString(5);
				g = new GoodsVO(no, name, price, qty, fname);
			}
		} catch (Exception e) {
			System.out.println("findByNo error: "+e.getMessage());
		}
		return g;
	}
	
	public int insertGoods (GoodsVO g) {
		int re = -1;
		String sql = "insert into goods values(seq_goods.nextval, ?, ?, ? ,?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getName());
			pstmt.setInt(2, g.getPrice());
			pstmt.setInt(3, g.getQty());
			pstmt.setString(4, g.getFname());
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("insertGoods error: " + e.getMessage());
		}
		return re;
	}
	
	public ArrayList<GoodsVO> findAll(){
		ArrayList<GoodsVO> list = new ArrayList<>();
		String sql = "select * from goods order by no";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int qty = rs.getInt(4);
				String fname = rs.getString(5);
				GoodsVO g = new GoodsVO(no, name, price, qty, fname);
				list.add(g);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println();
		}
		return list;
	}
}
