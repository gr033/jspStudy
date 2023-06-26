<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>상품등록</h2>
	<form action="insertGoodsOK.do" method="post" enctype="multipart/form-data">
		이름: <input type="text" name="name"><br>
		가격: <input type="number" name="price"><br>
		수량: <input type="number" name="qty"><br>
		사진: <input type="file" name="fname"><br>
		<input type="submit" value="등록">
		<input type="reset" value="재작성">
	</form>
</body>
</html>