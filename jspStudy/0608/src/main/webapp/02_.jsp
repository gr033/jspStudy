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
//name, age, list, m
	String name = (String)session.getAttribute("name");
	int age = (Integer)session.getAttribute("age");
	ArrayList<String> list = (ArrayList<String>)session.getAttribute("list");
	Member m = (Member)session.getAttribute("m");
%>
name: <%=name %><br>
age: <%=age %><br>
<%
for(String data:list){
	out.print(data+"<br>");
}
%>
id: <%=m.getId() %><br>
pwd: <%=m.getPwd() %><br>
name: <%=m.getName() %><br>
</body>
</html>