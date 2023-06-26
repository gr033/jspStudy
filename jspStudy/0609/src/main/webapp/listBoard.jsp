<%@page import="com.sist.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
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
	ArrayList<BoardVO> list = dao.selectAll();
%>
	<a href="insertBoard.jsp">게시물 작성</a>
	<table border="1">
		<tr>
			<th>게시글 번호</th>
			<th>게시물 제목</th>
			<th>작성자</th>
		</tr>
		<%
			for(BoardVO b: list){
				out.print("<tr>");
				out.print("<td>"+b.getNo()+"</td>");
				out.print("<td><a href='detailBoard.jsp?no="+b.getNo()+"'>"+b.getTitle()+"</a></td>");
				out.print("<td>"+b.getWriter()+"</td>");
				out.print("</tr>");
			}
		%>
	</table>
</body>
</html>