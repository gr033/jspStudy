<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품 수정</h2>
	<form action="updateGoodsOK.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${g.no }"><br>
		상품 이름: <input type="text" name="name" value="${g.name }"><br>
		상품 가격: <input type="number" name="price" value="${g.price }"><br>
		상품 수량: <input type="number" name="qty" value="${g.qty }"><br>
		상품 사진: <input type="file" name="fname"><br>
		<input type="hidden" name="oldFname" value="${g.fname }">
		<input type="submit" value="수정"><br>
		<input type="reset" value="삭제"><br>
	</form>
</body>
</html>