<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
댓글성공
${ctxPath = pageContext.request.contextPath; ''}
<a href="${ctxPath}/list.do">[게시글목록보기]</a>

</body>

<script>
	alert('댓글작성');
	window.location.href="${pageContext.request.contextPath}/read.do?no=${no}"
</script>
</html>