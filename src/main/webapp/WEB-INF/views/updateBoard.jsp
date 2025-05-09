<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>updateBoard</h1>
	<form id="updateForm" action="${pageContext.request.contextPath}/updateBoard" method="post">
		<table border="1">
			<tr>
				<td>boardNo</td>
				<td>
					<input type="text" name="boardNo" value="${board.boardNo}" readonly>
				</td>
			</tr>
			<tr>
				<td>boardTitle</td>
				<td>
					<input type="text" name="boardTitle" id="boardTitle" value="${board.boardTitle}">
				</td>
			</tr>
		</table>
		<button id="updateBtn" type="button">수정</button>
	</form>
	<script>
		document.querySelector('#updateBtn').addEventListener('click', ()=>{
			// 폼 유효성 검사
			if (document.querySelector('#boardTitle').value == '') {
				alert('title을 입력하세요');
				return;
			}
			
			document.querySelector('#updateForm').submit();
		})
	</script>
</body>
</html>