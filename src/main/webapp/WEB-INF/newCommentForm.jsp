<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 쓰기</title>
</head>
<body>
<form action="comment.do" method="post">
<input type="hidden" id = "article_no" name="no" value="${comReq.articleNumber}">
번호:<br>${comReq.articleNumber}
<p>
	내용:<br>
	<input type="text" name="content" value="${param.content}">
</p>
<input type="submit" value="댓글 등록">
</form>

<table border=1>
	<tr>
		<td>작성자</td>
		<td>내용</td>
	</tr>
</table>

<c:forEach var="comment" items="${commentPage.content}">
	<tr>
		<td>${comment.writer.name}</td>
		<td>${comment.content}</td>
	</tr>
</c:forEach>
</body>
</html>