<%@page import="javax.servlet.http.Cookie"%>
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
	String message = "hello";
	int year = 2023;
	Cookie cookie1 = new Cookie("message", message);
	Cookie cookie2 = new Cookie("year", year+"");
	response.addCookie(cookie1);
	response.addCookie(cookie2);
%>
쿠키값을 설정하였습니다.
<hr>
<a href="04_getCookie.jsp">쿠키값 확인하기</a>
</body>
</html>