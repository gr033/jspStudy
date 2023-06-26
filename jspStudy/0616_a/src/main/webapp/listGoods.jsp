<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
			uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품목록</h2>
	<form action="listGoods.do" method="post">
		상품이름 : <input type="search" name="keyword">
		<input type="submit" value="검색">
	</form>
	<a href="insertGoods.do">상품등록</a><br>
	<table border="1" width="80%">
		<tr>
			<th>상품번호</th>
			<th>상품명</th>			
			<th>가격</th>			
			<th>수량</th>			
			<th>이미지</th>			
		</tr>
		<c:forEach var="g" items="${list }">
			<tr>
				<td>${g.no }</td>
				<td>
					<a href="detailGoods.do?no=${g.no }">${g.name }</a>
				</td>
				<td>${g.price }</td>
				<td>${g.qty }</td>
				<td>
					<img  src="goods/${g.fname }" width="100" height="100">
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	${pageStr }
</body>
</html>
