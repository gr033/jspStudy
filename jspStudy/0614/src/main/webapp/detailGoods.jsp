<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkDelete(no){
		re=confirm("정말로 삭제하시겠습니까?")
		if(re==true){
			location.href="deleteGoods.do?no="+no;
		}
	}
</script>
</head>
<body>
	<h2>상품 확인</h2>
	<hr>
	상품 번호: ${g.no }<br>
	상품 이름: ${g.name }<br>
	상품 가격: ${g.price }<br>
	상품 수량: ${g.qty }<br>
	상품 사진: <br>
	<img src="goods/${g.fname }" width="200" height="200"><br>
	<a href='updateGoods.do?no=${g.no }'>상품 수정</a><br>
	<a href='listGoods.do?no=${g.no }'>상품 목록</a><br>
	<a href="#" onclick="checkDelete(${g.no})">상품 삭제</a>
</body>
</html>