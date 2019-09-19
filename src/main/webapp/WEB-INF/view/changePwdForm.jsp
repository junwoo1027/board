<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
<h3>비밀번호 변경</h3>
<form action="changePwd.do" method="post">
<p>
	현재 비밀번호:<br><input type="password" name="curPwd">
	<c:if test="${errors.curPwd}">현재 비밀번호를 입력하세요.</c:if>
	<c:if test="${errors.badCurPwd}">현재 비밀번호가 일치하지 않습니다.</c:if>
</p>
<p>
	새 비밀번호:<br><input type="password" name="newPwd">
	<c:if test="${errors.newPwd}">새 비밀번호를 입력하세요.</c:if>
</p>
<input type="submit" value="비밀번호 변경">
</form>
<form action="list.do">
<input type="submit" value="취소">
</form>
</body>
</html>