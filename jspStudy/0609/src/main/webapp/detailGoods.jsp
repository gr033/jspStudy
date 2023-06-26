<%@page import="com.sist.vo.GoodsVO"%>
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
	int no = Integer.parseInt(request.getParameter("no"));
	GoodsVO g = dao.selectGoods(no);
%>
상품번호: <%=no %><br>
상품이름: <%=g.getName() %><br>
상품수량: <%=g.getQty()%><br>
상품가격: <%=g.getPrice() %><br>
상품사진: <img src="data/<%=g.getFname() %>"><br>
</body>
</html>