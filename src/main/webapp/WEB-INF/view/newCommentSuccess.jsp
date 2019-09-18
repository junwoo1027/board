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
<a href="${ctxPath}/read.do?no=${comments.comNum}">[게시글내용보기]</a>
:${comments.comNum}
</body>
</html>