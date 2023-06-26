<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<!-- <form action="joinOK.jsp" method="post"> -->
	<form action="JoinOK" method="post">
		아이디: <input type="text" name="id"><br>
		패스워드: <input type="text" name="pwd"><br>
		이름: <input type="text" name="name"><br>
		생년월일: <input type="text" name="birth"><br>
		이메일: <input type="text" name="email"><br>
		<input type="submit" value="등록">
		<input type="reset" value="다시쓰기">
	</form>
</body>
</html>