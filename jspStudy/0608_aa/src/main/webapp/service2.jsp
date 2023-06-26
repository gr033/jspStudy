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
	String id="";
	if(session.getAttribute("id") == null){
		response.sendRedirect("login.jsp");
	}
	id = (String)session.getAttribute("id");
%>
<h2>서비스 2입니다.</h2>
<hr>

</body>
</html>