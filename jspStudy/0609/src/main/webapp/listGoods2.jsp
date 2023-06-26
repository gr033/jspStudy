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
	<table border="1">
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
		</tr>
		<%
		for(GoodsVO g: list){
			%>
			<tr>
				<td><%=g.getNo() %></td>
				<td><a href='detailGoods.jsp?no=<%=g.getNo()%>'><%=g.getName() %></td>
			</tr>
			<%
		}
		%>
	</table>
</body>
</html>