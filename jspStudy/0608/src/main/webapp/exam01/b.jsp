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
	int bno = Integer.parseInt(request.getParameter("bno"));
%>
bno: <%=bno %>
<a href="c.jsp?bno="<%=bno %>>연결</a>
</body>
</html>