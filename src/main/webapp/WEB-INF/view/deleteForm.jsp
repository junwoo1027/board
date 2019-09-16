<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
<form action="delete.do" method="post">
<input type="hidden" id = "article_no" name="no" value="${delReq.articleNum}">
번호:<br>${delReq.articleNum}
게시글을 삭제 하시겠습니까?
<br>
<input type=submit value="삭제">
</form>
${ctxPath = pageContext.request.contextPath; ''}
<form action="${ctxPath}/read.do?no=${delReq.articleNum}" method="post">
<input type="submit" value="취소">
</form>
</body>
</html>