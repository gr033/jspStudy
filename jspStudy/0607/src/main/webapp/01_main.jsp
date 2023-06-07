<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>지시자 include</h2>
	<p>다른 문서를 포함시킬 수 있음</p>
	<%@ include file="01_addr.jsp" %>
	<%
		int year = 2023;
	%>
	연도: <%=year %>
	<%@ include file="01_age.jsp" %>
</body>
</html>