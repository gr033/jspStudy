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
	int bno = 10;
%>
	<form action="b.jsp" method="post">
		<input type="hidden" name="bno" value="<%=bno %>">
		<input type="submit" value="삭제">
	</form>
</body>
</html>