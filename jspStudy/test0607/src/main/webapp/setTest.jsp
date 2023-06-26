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
	int a = 10;
	int b = 10;
	if(session.getAttribute("a") != null){
		a = (Integer)session.getAttribute("a");
	}
	if(application.getAttribute("b") != null){
		b = (Integer)application.getAttribute("b");
	}
	out.print("a:"+a+"<br>");
	out.print("b:"+b+"<br>");
	session.setAttribute("a", a+1);
	application.setAttribute("b", b+1);
%>
</body>
</html>