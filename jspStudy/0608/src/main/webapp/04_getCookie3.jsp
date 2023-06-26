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
	//쿠키에 저장된 year를 읽어와서 +1하여 출력합니다.
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c: cookies){
			if(c.getName().equals("year")){
				int year = Integer.parseInt(c.getValue()) + 1;
				out.print("year+1: "+year);
				break;
			}
		}
	}
%>
</body>
</html>