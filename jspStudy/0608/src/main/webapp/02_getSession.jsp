<%@page import="com.sist.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
name: ${name }<br>
age: ${age }<br>
<c:forEach var='data' items="${list }">
${data }<br>
</c:forEach>
<hr>
id: ${m.id }<br>
pwd: ${m.pwd }<br>
name: ${m.name }<br>
</body>
</html>