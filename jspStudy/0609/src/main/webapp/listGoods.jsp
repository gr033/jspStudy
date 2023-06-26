<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.GoodsDAO"%>
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
	GoodsDAO dao = new GoodsDAO();
	ArrayList<GoodsVO> list = dao.selectAll();
	
%>
<a href="insertGoods.jsp">상품등록</a>
	<table border="1">
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
		</tr>
		<%
		for(GoodsVO g: list){
			out.print("<tr>");
			out.print("<td>"+g.getNo()+"</td>");
			out.print("<td><a href='detailGoods.jsp?no="+g.getNo()+"'>"+g.getName()+"</a></td>");
			out.print("</tr>");
		}
		%>
	</table>
</body>
</html>