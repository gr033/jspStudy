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
<jsp:useBean id="d" class="com.sist.vo.DeptVO"/>
<jsp:setProperty property="dno" name="d" value="100"/>
<jsp:setProperty property="dname" name="d" value="영업팀"/>
<jsp:setProperty property="dloc" name="d" value="서교동"/>
부서번호: <jsp:getProperty property="dno" name="d"/><br>
부서이름: <jsp:getProperty property="dname" name="d"/><br>
부서위치: <jsp:getProperty property="dloc" name="d"/><br>
</body>
</html>