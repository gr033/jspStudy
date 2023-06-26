<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor = "pink">
<%
	String id="";
	if(session.getAttribute("id") == null){
		response.sendRedirect("login.jsp");
	}
	id = (String)session.getAttribute("id");
%>
<%=id %>님 환영합니다.<br>
<a href="service1.jsp">서비스1</a>
<a href="service2.jsp">서비스2</a>
<a href="service3.jsp">서비스3</a>
</body>
</html>