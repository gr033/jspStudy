<%@page import="com.sist.vo.DeptVO"%>
<%@page import="com.sist.dao.DeptDAO"%>
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
	int dno = Integer.parseInt(request.getParameter("dno"));
	String dname = request.getParameter("dname");
	String dloc = request.getParameter("dloc");
	DeptVO d = new DeptVO();
	d.setDno(dno);
	d.setDname(dname);
	d.setDloc(dloc);
	
	DeptDAO dao = new DeptDAO();
	dao.insertDept(d);
%>
등록하였습니다.
<hr>
</body>
</html>