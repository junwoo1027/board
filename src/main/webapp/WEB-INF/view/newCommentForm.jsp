<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="comment.do" method="post">
<input type="hidden" name="no" value="${comReq.articleNumber}">
번호:<br>${comReq.articleNumber}
<p>
	내용:<br>
	<input type="text" name="content" value="${param.content}">
</p>
<input type="submit" value="댓글 등록">
</form>
</body>
</html>