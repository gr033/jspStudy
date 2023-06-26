<%@page import="com.sist.util.SistUtil"%>
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
	if(SistUtil.getCookie(request, "year") != null){
		int year = Integer.parseInt(SistUtil.getCookie(request, "year"));
		year ++;
		out.print(year+"<br>");
	}
	String member = SistUtil.getCookie(request, "member");
	if(member==null){
		out.print("member가 존재하지 않습니다."); 
	}
%> 
</body>
</html>