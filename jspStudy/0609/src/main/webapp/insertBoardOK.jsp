<%@page import="com.sist.dao.BoardDAO"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	request.setCharacterEncoding("utf-8");
	
	String path = request.getRealPath("data");
	System.out.println(path);
	
	MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*10, "UTF-8", null);
	String title = multi.getParameter("title");
	String writer = multi.getParameter("writer");
	String pwd = multi.getParameter("pwd");
	String content = multi.getParameter("content");
	String fname = multi.getFilesystemName("fname");
	
	System.out.println(fname);
	
	BoardDAO b = new BoardDAO();
	int re = b.insertBoard(title, writer, pwd, content, fname);
	if(re>0){
		out.print("저장성공");
	}else{
		out.print("저장실패");
	}
%>
	<a href="insertBoard.jsp">게시물 작성</a>
	<a href="listBoard.jsp">게시물 목록</a>
</body>
</html>