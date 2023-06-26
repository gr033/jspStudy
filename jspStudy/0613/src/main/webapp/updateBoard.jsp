<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 수정</h2>
	<form action="updateBoard.do" method="post" enctype = "multipart/form-data">
	<input type = "hidden" name = "no" value="${b.no }">
		글제목: <input type="text" name = "title" value="${b.title }"><br>
		글내용: <br>
		<textarea rows="10" cols="80" name="content">${b.content }</textarea><br>
		글암호: <input type="password" name = "pwd"><br>
		<img src = "board/${b.fname }" width="50" height="50">
		
		<input type="hidden" name="oldFname" value="${b.fname }">
		첨부파일: <input type="file" name="fname"><br>
		<input type="submit" value="수정"><br>
		<input type="reset" value="삭제">
		
	</form>
</body>
</html>