<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
<c:if test="${! empty authUser}">
	<div>
	${authUser.name}님
	<a href="logout.do">[로그아웃]</a>
	<a href="changePwd.do">[비밀번호 변경]</a>
	<a href="memDelete.do">[회원탈퇴]</a>
	</div>
</c:if>
<br><br>
<table border="1">
	<tr>
		<td colspan="5"><a href="write.do">[게시글쓰기]</a></td>
	</tr>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
	</tr>
<c:if test="${articlePage.hasNoArticles()}">
	<tr>
		<td>게시글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="article" items="${articlePage.content}">
	<tr>
		<td>${article.number}</td>
		<td>
		<a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
			<c:out value="${article.title}"/>
		</a>
		</td>
		<td>${article.writer.name}</td>
		<td>${article.readCount}</td>
	</tr>
</c:forEach>
<c:if test="${articlePage.hasArticles()}">
	<tr>
		<td colspan="5">
			<c:if test="${articlePage.startPage > 5}">
			<a href="list.do?pageNo=${articlePage.startPage-5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" begin="${articlePage.startPage}" end="${articlePage.endPage}">
				<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${articlePage.endPage < articlePage.totalPages}">
			<a href="list.do?pageNo=${articlePage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>

<%-- 
<div>
<form action="list.do" method="get">
<select name="col">
	<option value="none">전체 목록</option>
	<option value="title">제목</option>
	<option value="content">내용</option>
	<option value="title_content">제목+내용</option>
</select>
	<input type="text" name="word" value="">
	<input type="submit" value="검색">
</form>
</div>
--%>
</body>
</html>