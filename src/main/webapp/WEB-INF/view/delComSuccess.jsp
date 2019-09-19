<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
<script>
	alert('삭제완료');
	window.location.href="${pageContext.request.contextPath}/read.do?no=${articleNum}"
</script>
</body>
</html>