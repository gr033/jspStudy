<%@page import="com.sist.vo.BoardVO"%>
<%@page import="com.sist.dao.BoardDAO"%>
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
	BoardDAO dao = new BoardDAO();
	int no = Integer.parseInt(request.getParameter("no"));
	BoardVO b = dao.selectBoard(no);
%>
게시물번호: <%=no %><br>
게시물제목: <%=b.getTitle() %><br>
게시물작성자: <%=b.getWriter() %><br>
게시물비밀번호: <%=b.getPwd() %><br>
게시물내용: <br>
<textarea rows="10" cols="80" readonly="readonly"><%=b.getContent() %></textarea><br>
게시물작성날짜: <%=b.getRegdate() %><br>
게시물좋아요수: <%=b.getHit() %><br>
<img src="data/<%=b.getFname() %>"><br>

</body>
</html>