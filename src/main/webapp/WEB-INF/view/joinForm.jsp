<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

</head>
<body>
<form action="join.do" method="post">
<p>
	아이디:<br/><input type="text" id="id" name="id" value="${param.id}" placeholder="시작은 영문, 특수문자 제외, 5~12자" size=32>
	<c:if test="${errors.id}">ID를 입력하세요</c:if>
	<c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
	<c:if test="${errors.checkId}">형식에 맞게 입력해주세요.</c:if>
</p>
<p>
	이름:<br/><input type="text" name="name" value="${param.name}" size="32">
	<c:if test="${errors.name}">이름을 입력하세요</c:if>
</p>
<p>
	암호:<br/><input type="password" name="password" size="32">
	<c:if test="${errors.password}">암호를 입력하세요.</c:if>
</p>
<p>
	확인:<br/><input type="password" name="confirmPassword" size="32">
	<c:if test="${errors.confirmPassword}">확인을 입력하세요.</c:if>
	<c:if test="${errors.notMatch}">암호와 확인이 일치하지 않습니다.</c:if>
</p>
<input type="submit" value="가입">
</form>
<form ${ctxPath = pageContext.request.contextPath ; ''}action="${ctxPath}/index.jsp" method="post">
<input type="submit" value="취소">
</form>
</body>
</html>