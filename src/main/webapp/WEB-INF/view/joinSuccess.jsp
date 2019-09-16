<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입완료</title>
</head>
<body>
${param.name}님, 회원 가입에 성공했습니다.

<br><br>
<c:if test="${empty authUser}">
	<a href="login.do">로그인하기</a>
</c:if>
</body>
</html>