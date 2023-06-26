package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.BoardVO;

import db.ConnectionProvider;

public class BoardDAO {
	public static int totalRecord;	//전체 레코드 수
	public static int pageSIZE = 10;//한 화면에 보여줄 레코드 수
	public static int totalPage;	//전체 페이지 수
	
	private static BoardDAO dao;
	private BoardDAO() {
	}
	
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao=new BoardDAO();
		}
		return dao;
	}
	
	public int getTotalRecord(String keyword, String search_column) {
		int total = 0;
		String sql = "select count(*) from board ";
		
		//검색문이 있다면
		if(keyword != null) {
			sql += "where "+search_column+" like '%"+keyword+"%'";
		}
		
		System.out.println("sql: "+sql);
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("레코드 수: "+total);
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("getTotalRecord error: "+e.getMessage());
		}
		return total;
	}
	
	public void updateHit(int no) {
		String sql = "update board set hit=hit+1 where no="+no;
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			ConnectionProvider.close(conn, stmt);
		} catch (Exception e) {
			System.out.println("updateHit error: "+e.getMessage());
		}
	}
	
	public BoardVO findByNo(int no, boolean hit) {
		BoardVO b = new BoardVO();
		try {
			if(hit==true) {
				updateHit(no);
			}
			String sql = "select * from board where no="+no;
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setWriter(rs.getString(3));
				b.setPwd(rs.getString(4));
				b.setContent(rs.getString(5));
				b.setRegdate(rs.getDate(6));
				b.setHit(rs.getInt(7));
				b.setFname(rs.getString(8));
				b.setB_ref(rs.getInt(9));
				b.setB_level(rs.getInt(10));
				b.setB_step(rs.getInt(11));
			}
			ConnectionProvider.close(conn, stmt, rs);
		} catch (Exception e) {
			System.out.println("findByNo error: "+e.getMessage());
		}
		return b;
	}
	
	public ArrayList<BoardVO> findAll(int pageNUM, String keyword, String search_column){
		//전체 페이지수를 반환
		totalRecord = getTotalRecord(keyword, search_column);
		
		System.out.println("totalRecord: "+totalRecord);
		
		//실수로 형변환 후 올림하고 그걸 정수로 변환해서 페이지 수 계산
		totalPage = (int)Math.ceil(totalRecord / (double)pageSIZE);
		
//		totalPage = totalRecord/pageSIZE;
//		
//		if(totalRecord % totalPage !=0) {
//			totalPage ++;
//		}
		
		//현재 페이지에 보여줄 시작 레코드
		int start = (pageNUM-1)*pageSIZE+1;
		//현재 페이지에 보여줄 마지막 레코드
		int end = start+pageSIZE-1;
		
		if(end>totalRecord) {
			end=totalRecord;
		}
		
		System.out.println("start: "+start);
		System.out.println("end: "+end);
		
		System.out.println(totalRecord+"<- totalRecord, totalPage->"+totalPage);
		
		ArrayList<BoardVO> list = new ArrayList<>();
		String sql = "select no, title, writer, pwd, content, regdate, hit, fname, b_ref, b_level, b_step "
				+ "from (select rownum n, no , title, writer, pwd, content, regdate, hit, fname, b_ref, b_level, b_step "
				+ "from (select * from board ";
		
				//동적 쿼리 생성
				if(keyword != null) {
					sql += "where "+search_column+" like '%"+keyword+"%'";
				}
				
				sql+=" order by b_ref desc, b_step)) a "
				+ "where a.n between ? and ?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO b = new BoardVO();
				b.setNo(rs.getInt(1));
				b.setTitle(rs.getString(2));
				b.setWriter(rs.getString(3));
				b.setPwd(rs.getString(4));
				b.setContent(rs.getString(5));
				b.setRegdate(rs.getDate(6));
				b.setHit(rs.getInt(7));
				b.setFname(rs.getString(8));
				b.setB_ref(rs.getInt(9));
				b.setB_level(rs.getInt(10));
				b.setB_step(rs.getInt(11));
				list.add(b);
			}
			ConnectionProvider.close(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("findAll error: "+e.getMessage());
		}
		return list;
	}
	
	//새로운 글번호 발생을 위한 메소드
	public int getNextNo() {
		int no=0;
		String sql = "select nvl(max(no),0)+1 from board";
		try {
			Connection conn = ConnectionProvider.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				no=rs.getInt(1);
			}
			ConnectionProvider.close(conn, stmt);
		} catch (Exception e) {
			System.out.println("getNextNo error: "+e.getMessage());
		}
		return no;
	}
	
	//게시물 등록을 위한 메소드 
	public int insertBoard(BoardVO b) {
		int re = -1;
		String sql = "insert into board values(?, ?, ?, ?, ?, sysdate, 0, ?, ?, ?, ?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNo());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getWriter());
			pstmt.setString(4, b.getPwd());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getFname());
			pstmt.setInt(7, b.getB_ref());
			pstmt.setInt(8, b.getB_level());
			pstmt.setInt(9, b.getB_step());
			
			re = pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
			
		} catch (Exception e) {
			System.out.println("insertBoard error:"+e.getMessage());
		}
		return re;
	}
	
	//서로 관련있는 글일 때에 (b_ref가 동일할 때) 글이 출력되는 순서를 위하여 
	//이미 달려있는 답글들의 b_step을 증가시키는 메소드를 생성
	public void updateStep(int b_ref, int b_step) {
		String sql = "update board set b_step = b_step+1 where b_ref=? and b_step>?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_ref);
			pstmt.setInt(2, b_step);
			pstmt.executeUpdate();
			ConnectionProvider.close(conn, pstmt);
		} catch (Exception e) {
			System.out.println("updateStep error: "+e.getMessage());
		}
	}
}
