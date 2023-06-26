<%@page import="com.sist.dao.GoodsDAO"%>
<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	
	//파일을 업로드하기 위한 폴더(data)의 실제 경로를 알아온다.
	String path = request.getRealPath("data");
	System.out.println(path);
	
	MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", null);
	//1024*1024*5 -> 5MB
	
	String name = multi.getParameter("name");
	//상품 이름을 가져온다
	String fname = multi.getFilesystemName("fname");
	//사진 이름을 가져온다
	
	System.out.println(name);
	System.out.println(fname);
	
	//데이터베이스에 등록이 되도록 나머지 코드를 완성
	GoodsVO g = new GoodsVO();
	int no = Integer.parseInt(multi.getParameter("no"));
	int price = Integer.parseInt(multi.getParameter("price"));
	int qty = Integer.parseInt(multi.getParameter("qty"));
	g.setNo(no);
	g.setName(name);
	g.setPrice(price);
	g.setQty(qty);
	g.setFname(fname);
	GoodsDAO dao = new GoodsDAO();
	int re = dao.insert(g);
	if(re>0){
		out.print("저장 성공");
	}else{
		out.print("저장 실패");
	}
%>
<a href="insertGoods.jsp">상품등록</a>
<a href="listGoods.jsp">상품목록</a>
</body>
</html>