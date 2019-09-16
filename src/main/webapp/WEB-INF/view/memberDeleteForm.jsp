<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
<h2>회원탈퇴</h2>
<br>
<form action="memDelete.do" method="post">
<p>
	비밀번호:<br><input type="password" name="password">
	<c:if test="${errors.password}">비밀번호를 입력하세요.</c:if>
	<c:if test="${errors.badCurPwd}">현재 비밀번호가 일치하지 않습니다.</c:if>
</p>

<input type="submit" value="탈퇴">
</form>
<form action="list.do">
<input type="submit" value="취소">
</form>
</body>
</html>