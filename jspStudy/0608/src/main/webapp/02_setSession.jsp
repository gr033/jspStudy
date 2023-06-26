<%@page import="com.sist.vo.Member"%>
<%@page import="java.util.ArrayList"%>
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
	String name = "이순신";
	int age = 20;
	ArrayList<String> list = new ArrayList<String>();
	list.add("사과");
	list.add("포도");
	list.add("수박");
	list.add("낑깡");
	Member m = new Member("tiger", "1234", "이순신");
	session.setAttribute("name", name);
	session.setAttribute("age", age);
	session.setAttribute("list", list);
	session.setAttribute("m", m);
%>
세션에 값을 설정하였습니다.
<hr>
<a href="02_getSession.jsp">세션값 확인하기</a>
</body>
</html>