<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>������ include</h2>
	<p>�ٸ� ������ ���Խ�ų �� ����</p>
	<%@ include file="01_addr.jsp" %>
	<%
		int year = 2023;
	%>
	����: <%=year %>
	<%@ include file="01_age.jsp" %>
</body>
</html>