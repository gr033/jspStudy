<%@page import="com.sist.dao.MemberDAO"%>
<%@page import="com.sist.vo.MemberVO"%>
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
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String email = request.getParameter("email");
	
	MemberVO m = new MemberVO();
	m.setId(id);
	m.setPwd(pwd);
	m.setName(name);
	m.setBirth(birth);
	m.setEmail(email);
	MemberDAO dao = new MemberDAO();
	dao.insert(m);
%>
회원을 등록했습니다.
</body>
</html>