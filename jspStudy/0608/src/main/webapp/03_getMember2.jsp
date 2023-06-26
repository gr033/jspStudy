<%@page import="com.sist.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<Member> list = (ArrayList<Member>)session.getAttribute("list");
%>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>암호</th>
		<th>이름</th>
	</tr>
	<%
		for(Member m:list){
		%>
		<tr>
			<td><%=m.getId() %></td>
			<td><%=m.getPwd() %></td>
			<td><%=m.getName() %></td>
			</tr>
		<%
		}
	%>
</table>
</body>
</html>