
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// 로그인 여부 확인
String loginId = (String) session.getAttribute("loginId");

// 로그인하지 않았다면 로그인 화면으로 이동
if (loginId == null) {
	response.sendRedirect(request.getContextPath() + "/login1.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
</head>
<body>
	<header><h1>게시글 등록</h1></header>
	<nav>
	<a href="logout1.jsp">로그아웃</a>
	<a href="board.jsp">home</a>
	</nav>
	<p>작성자:<%=loginId%></p>
	<form action="board" method="post" enctype="multipart/form-data">
	<p>이미지: <input type="file" name="image"  required></p>

		<p>이미지 설명:
			<textarea name="imageDesc" rows="5" cols="40"
				placeholder="이미지 설명을 입력하세요"></textarea>
		</p>

		<input type="submit" value="등록"> <a href="board.jsp">취소</a>

	</form>

</body>
</html>
