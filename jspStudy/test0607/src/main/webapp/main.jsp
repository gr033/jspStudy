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
		String id = "";
		if(session.getAttribute("id") == null){
			response.sendRedirect("login.jsp");
		}else{
			id = (String)session.getAttribute("id");
		}
	%>
	<%=id %>님 로그인 하였습니다.
	<!-- <a href="logout.jsp">로그아웃</a> -->
	<a href="Logout">로그아웃</a>
	<h2>지시자 include</h2>
	<p>다른 문서를 포함시킬수 있어요!</p>
	<%@ include file="addr.jsp"  %>
	<%
		int year = 2023;
	%>
	연도 <%= year %>
	<%@ include file="age.jsp" %>
</body>
</html>
