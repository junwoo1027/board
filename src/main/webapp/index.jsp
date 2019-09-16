<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${! empty authUser}">
	${authUser.name}님 안녕하세요.
	<a href="logout.do">[로그아웃]</a>
	<a href="changePwd.do">[비밀번호 변경]</a>
	<a href="memDelete.do">[회원탈퇴]</a>
	${ctxPath = pageContext.request.contextPath ; ''}
	<a href="write.do">[글쓰기]</a>
	${ctxPath = pageContext.request.contextPath ; ''}
<a href="${ctxPath}/list.do">[게시글목록보기]</a>
</c:if>
<c:if test="${empty authUser}">
	<a href="join.do">[회원가입하기]</a>
	<a href="login.do">[로그인하기]</a>
</c:if>
</body>
</html>