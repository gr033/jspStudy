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
	//요청한 데이터가 한글일 때에는 인코딩을 설정해야 함
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
	String[] hobby = request.getParameterValues("hobby");
	
	//요청방식을 파악할 수 있음
	String method = request.getMethod();
	
	//요청한 ip주소를 파악할 수 있음
	String ip = request.getRemoteAddr();
	
	//요청한 URL을 확인할 수 있음
	String URL = request.getRequestURL().toString();
	
	//요청한 URI를 확인할 수 있음
	String URI = request.getRequestURI().toString();
%>
사용자가 요청한 정보는 다음과 같습니다.<br>
name: <%=name %><br>
age: <%=age %><br>
hobby: 
<%
	for(String i:hobby){
		out.print(i+" ");
	}
%>
<hr>
method: <%=method %><br>
ip: <%=ip %><br>
URL: <%=URL %><br>
URI: <%=URI %>
</body>
</html>