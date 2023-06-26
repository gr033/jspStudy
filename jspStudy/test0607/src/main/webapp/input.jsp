<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="inputOK.jsp" method="post">
		이름: <input type="text" name="name"><br>
		나이: <input type="text" name="age"><br>
		취미: 
		<input type="checkbox" name="hobby" value="수영">수영
		<input type="checkbox" name="hobby" value="게임">게임
		<input type="checkbox" name="hobby" value="마라톤">마라톤
		<input type="submit" value="확인">
	</form>
</body>
</html>