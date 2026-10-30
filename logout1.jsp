<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	Cookie cookie = new Cookie("Id", "");
	cookie.setMaxAge(0);        
	cookie.setPath("/");        
	response.addCookie(cookie);
	
	// 추가: 공지 쿠키도 삭제
	Cookie notice = new Cookie("hideNoticeUntil", "");
	notice.setMaxAge(0);
	notice.setPath("/");
	response.addCookie(notice);
	
	response.sendRedirect("login1.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>