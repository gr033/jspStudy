<%@page import="com.sist.vo.DeptVO"%>
<%@page import="com.sist.dao.DeptDAO"%>
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
%>
	<jsp:useBean id="d" class="com.sist.vo.DeptVO"/>
	<jsp:setProperty property="*" name="d"/>
	<jsp:useBean id="dao" class="com.sist.dao.DeptDAO"/>
<%
	dao.insertDept(d);
%>
등록하였습니다.
<hr>
</body>
</html>