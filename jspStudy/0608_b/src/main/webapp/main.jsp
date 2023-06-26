<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="loginCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor = "pink">
<%=id %>님 환영합니다.<br>
서비스 문서입니다.
<hr>
<!-- 로그인한 회원인지 판별하는 jsp를 만들고 이것을 main, service1~4에 지시자 include로 포함하도록 한다. -->
<a href="service1.jsp">서비스1</a>
<a href="service2.jsp">서비스2</a>
<a href="service3.jsp">서비스3</a>
</body>
</html>