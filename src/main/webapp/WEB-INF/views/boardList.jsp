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
	<h1>boardList</h1>
	<table border="1">
		<tr>
			<th>No</th>
			<th>title</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.boardNo}</td>
				<td>
					<a href="${pageContext.request.contextPath}/boardOne?boardNo=${board.boardNo}">${board.boardTitle}</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<a href="${pageContext.request.contextPath}/addBoard">addBoard</a>
	</div>
</body>
</html>