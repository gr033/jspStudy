package com.sist.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.GoodsVO;

import db.ConnectionProvider;

public class GoodsDAO {
	public ArrayList<GoodsVO> listGoods (){
		String sql = "select * from goods";
		ArrayList<GoodsVO> list = new ArrayList<>();
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
				GoodsVO b = new GoodsVO(no, name, price, qty, fname);
				list.add(b);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("listGoods 예외발생: "+e.getMessage());
		}
		return list;
	}
}
