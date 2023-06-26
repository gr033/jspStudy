<%@page import="com.sist.vo.DeptVO"%>
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
	DeptVO d = new DeptVO();
	d.setDname("영업팀");
	d.setDno(100);
	d.setDloc("서교동");
%>
부서번호: <%=d.getDno() %><br>
부서명: <%=d.getDname() %><br>
부서위치: <%=d.getDloc() %><br>
</body>
</html>