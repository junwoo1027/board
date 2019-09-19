<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 읽기</title>
</head>
<body>
<table border="1" width="100%">
<tr>
	<td>번호</td>
	<td>${articleData.article.number}</td>
</tr>
<tr>
	<td>아이디</td>
	<td>${articleData.article.writer.id}</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${articleData.article.writer.name}</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value='${articleData.article.title}' /></td>
</tr>
<tr>
	<td>내용</td>
	<td><c:out value='${articleData.content}'/></td>
</tr>
<tr>
	<td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		<c:if test="${authUser.id == articleData.article.writer.id}">
		<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a>
		<a href="delete.do?no=${articleData.article.number}">[게시글삭제]</a>
		</c:if>
	</td>
</tr>
</table>

<br><br>
<h3>댓글</h3>
<table border="1">

<c:forEach var="comment" items="${comment}">
	<tr>
		<td>아이디:${comment.writer.id}</td>
		<td>내용:${comment.content}
		<c:if test="${authUser.id == comment.writer.id}">
<%--		<a href="#">수정</a>--%>
<%--		<a href="#" onclick="comDelete(${comment.comNum})">삭제</a>--%>
		<a href="delComment.do?comNum=${comment.comNum}&no=${articleData.article.number}" onclick="return delcheck()">삭제</a>
		</c:if>
		</td>
	</tr>
</c:forEach>
</table>
<form name="commentForm" action="comment.do" method="post">
<input type="hidden" name="no" value="${articleData.article.number}">
<p>
	내용:<br>
	<input type="text" name="content" value="${param.content}">
<input type="submit" onclick="Comment()" value="댓글 등록">
</p>
</form>
</body>

<script type="text/javascript">
<%--function comDelete(comNum){
	var msg=confirm("댓글삭제");
	if(msg==true){
		deleteCom(comNum);
	}else{
		return false;
	}
}--%>

function delcheck(){
	return confrim("삭제");
}

function writeCom(){
	return confirm("쓰기");
}

function Comment(){
	var commentForm = document.commentForm;
	var content=commentForm.content.value;
	
	if(!content){
		alert("댓글을 입력하세요.");
	}else{
		commentForm.submit();
	}
}

</script>

</html>