<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${msg }
	<hr>
	<c:if test="${b != null }">
		글제목: ${b.title }<br>
		작성자: ${b.writer }<br>
	</c:if>
	<hr>
	<a href="listBoard.do">글목록</a>
</body>
</html>