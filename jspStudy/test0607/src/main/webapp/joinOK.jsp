<%@page import="com.sist.dao.MemberDAO"%>
<%@page import="com.sist.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- jsp 액션태그 usebean, setProperty 를 이용한 것으로 코드를 수정 -->
<jsp:useBean id="m" class="com.sist.vo.MemberVO"/>
//매개변수를 갖지 않는 생성자가 요구되므로 반드시 vo에 매개변수를 갖지 않는 생성자를 만들 것
<jsp:setProperty property="*" name="m"/>
<jsp:useBean id="dao" class="com.sist.dao.MemberDAO"/>
<%
	dao.insert(m);
%>
회원을 등록했습니다.
</body>
</html>