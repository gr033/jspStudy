<%@page import="com.sist.dao.MemberDAO"%>
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
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDAO dao = new MemberDAO();
		if(dao.isMember(id, pwd)){
			session.setAttribute("id", id);
			response.sendRedirect("main.jsp");
		}else{
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>