package com.sist.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.db.ConnectionProvider;
import com.sist.vo.BookVO;

public class BookDAO {
	//한 화면에 보여줄 레코드의 수
	public static int pageSIZE = 5;
	
	//전체 레코드의 수
	public static int totalRecord;
	
	//전체 페이지의 수
	public static int totalPage;
	
	public static BookDAO dao;
	private BookDAO() {
	}
	
	public static BookDAO getInstance() {
		if(dao==null) {
			dao = new BookDAO();
		}
		return dao;
	}
	
	//전체 레코드의 수를 반환하는 메소드
	public int getTotalRecord(String keyword, String searchColumn, String op) {
		int cnt = 0;
		String sql = "select count(*) from book ";
		if(keyword != null) {
			if(searchColumn.equals("price")) {
				sql += "where "+searchColumn+" "+op+" "+keyword;
			}else {
				sql += "where "+searchColumn+" like '%"+keyword+"%' ";
			}
		}
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("getTotalRecord error: "+e.getMessage());
		}
		return cnt;
	}
	
	public ArrayList<BookVO> findAll(int pageNUM, String keyword, String searchColumn, String op){
		totalRecord = getTotalRecord(keyword, searchColumn, op);
		totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
		System.out.println("totalRecord : "+totalRecord);
		System.out.println("totalPage: "+totalPage);
		
		int start = pageSIZE*(pageNUM-1)+1;
		int end = start+pageSIZE-1;
		
		System.out.println("start: "+start);
		System.out.println("end: "+end);
		
		if(end>totalRecord) {
			end=totalRecord;
		}
		
		
		ArrayList<BookVO> list = new ArrayList<>();
		String sql = "select bookid, bookname, publisher, price "
				+ "from (select rownum n, bookid, bookname, publisher, price "
				+ "from (select * from book ";
		if(keyword != null) {
			if(searchColumn.equals("price")) {
			sql += "where "+searchColumn+" "+op+" "+keyword;
			}else {
				sql += "where "+searchColumn+" like '%"+keyword+"%' ";
			}
		}
			sql += " order by bookid)) a "
			+ "where a.n between "+start+" and "+end ;
			
			System.out.println("op: "+op);
			System.out.println(sql);
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BookVO b = new BookVO();
				b.setBookid(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setPublisher(rs.getString(3));
				b.setPrice(rs.getInt(4));
				list.add(b);
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("findAll error: "+e.getMessage());
		}
		return list;
	}
}
