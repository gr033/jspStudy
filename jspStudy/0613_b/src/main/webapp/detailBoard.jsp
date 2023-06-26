<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 확인</h2>
	<hr>
	글번호: ${b.no }<br>
	글제목: ${b.title }<br>
	작성자: ${b.writer }<br>
	글내용: <br>
	<textarea rows="10" cols="80" readonly="readonly">${b.content }</textarea><br>
	등록일: ${b.regdate }<br>
	조회수: ${b.hit }<br>
	<img src="board/${b.fname }" width="200" height="200"><br>
	<a href="updateBoard.do?no=${b.no }">게시물 수정</a><br>
	<a href="deleteBoard.do?no=${b.no }">게시물 삭제</a><br>
</body>
</html>