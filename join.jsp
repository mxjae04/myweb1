<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Form</title>
</head>
<body>
	<h1>회원가입</h1>

	<form action="members" method="post"> <!-- MembersServlet 서블릿 만들어진 곳 -->
			아이디: <input type="text" name="id" placeholder="아이디를 입력하세요" required><br>
			비밀번호: <input type="password" name="pw" placeholder="비밀번호를 입력하세요"required><br> 
			이름: <input type="text" name="name" placeholder="이름을 입력하세요" required><br> 
			이메일: <input type="email" name="email" placeholder="이메일을 입력하세요" required><br>
			전화번호: <input type="tel" name="tel" placeholder="전화번호를 입력하세요" required><br>
			<input type="submit" value="회원가입">
	</form>

</body>
</html>