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
<!-- 아이디와 암호를 입력받아 올바른 회원이면 main.jsp로 보내고 그렇지 않으면 다시 login페이지로 보내도록 한다. -->
	<h2>로그인</h2>
	<form action="loginOK.jsp" method="post">
		아이디: <input type="text" name='id'><br>
		비밀번호: <input type="text" name="pwd"><br>
		<input type="submit" value="로그인">
	</form>
	
</body>
</html>