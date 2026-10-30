<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login Form</title>
</head>
<body>
<h1>로그인</h1>
<form action="login1" method="post"> <!-- LoginServlet 서블릿이 만들어진 곳 -->
			아이디: <input type="text" name="id" placeholder="아이디를 입력하세요" required><br>
			비밀번호: <input type="password" name="pw" placeholder="비밀번호를 입력하세요"required><br> 
			<input type="submit" value="로그인">
			<a href="join.jsp">회원가입</a>
	</form>
</body>
</html>