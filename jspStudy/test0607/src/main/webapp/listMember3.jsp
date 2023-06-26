<%@page import="com.sist.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.MemberDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 자바빈즈를 이용하여 모든 회원목록을 출력하는 웹 문서를 작성 -->
<%
	MemberDAO dao = new MemberDAO();
	ArrayList<MemberVO> list = dao.findAll();
%>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>생일</th>
		<th>이메일</th>
	</tr>
	<%
		for(MemberVO m:list){
			%>
			<tr>
				<td><%=m.getId() %></td>
				<td><%=m.getPwd()%></td>
				<td><%=m.getName()%></td>
				<td><%=m.getBirth()%></td>
				<td><%=m.getEmail()%></td>
			</tr>
			<%
		}
	%>
</table>
</body>
</html>