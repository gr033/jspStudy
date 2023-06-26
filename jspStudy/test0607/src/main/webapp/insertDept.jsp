<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "insertDeptOK.jsp" method="post">
		부서번호: <input type="number" name="dno"><br>
		부서명: <input type="text" name="dname"><br>
		부서위치: <input type="text" name="dloc"><br>
		<input type="submit">
	</form>
</body>
</html>