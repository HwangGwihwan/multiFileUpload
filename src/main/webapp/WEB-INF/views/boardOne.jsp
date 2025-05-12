<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>boardOne</h1>
	<a href="${pageContext.request.contextPath}/boardList">리스트...</a>
	<div>
		<h3>${board.boardTitle}</h3>
		<a href="${pageContext.request.contextPath}/updateBoard?boardNo=${board.boardNo}">수정</a>
		<a href="${pageContext.request.contextPath}/deleteBoard?boardNo=${board.boardNo}">삭제</a>
	</div>
	<c:forEach var="file" items="${list}">
		<div>
			<img src="/upload/${file.filename}" width="300" height="200">
			<a href="${pageContext.request.contextPath}/deletefileOne?filename=${file.filename}&boardNo=${board.boardNo}">삭제</a>
		</div>
	</c:forEach>
</body>
</html>