<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<th>dno</th>
		<th>dname</th>
		<th>dloc</th>
	</tr>		
<%
	try{
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/sist");
		Connection conn = ds.getConnection();
		String sql = "select * from dept order by dno";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			%>
			<tr>
				<td><%=rs.getInt(1) %></td>
				<td><%=rs.getString(2) %></td>
				<td><%=rs.getString(3) %></td>
			</tr>
			<%
		}
		rs.close();
		stmt.close();
		conn.close();
	}catch(Exception e){
		System.out.println("예외발생: "+e.getMessage());
	}
%>
</table>
</body>
</html>