package com.sist.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.goodsVO;

import db.ConnectionProvider;

public class goodsDAO {
	public ArrayList<goodsVO> listGoods (){
		String sql = "select * from goods";
		ArrayList<goodsVO> list = new ArrayList<>();
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
				goodsVO b = new goodsVO(no, name, price, qty, fname);
				list.add(b);
			}
			ConnectionProvider.close(rs, stmt, conn);
		} catch (Exception e) {
			System.out.println("listGoods 예외발생: "+e.getMessage());
		}
		return list;
	}
}
