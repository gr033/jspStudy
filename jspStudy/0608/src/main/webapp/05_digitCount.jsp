<%@page import="com.sist.util.SistUtil"%>
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
	//쿠키를 이용하여 방문 횟수를 기록하여 몇번째 방문인지를 출력하는 jsp 작성
	
	int visitCount = 1;
	if(SistUtil.getCookie(request, "visitCount") != null){
		visitCount = Integer.parseInt(SistUtil.getCookie(request, "visitCount"));
	}
	
	out.print(visitCount+ "번째 방문입니다.");
	visitCount++;
	Cookie cookie = new Cookie("visitCount", visitCount+"");
	response.addCookie(cookie);
%>
</body>
</html>