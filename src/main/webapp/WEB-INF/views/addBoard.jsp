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
	<h1>addBoard</h1>
	<form id="addForm" action="${pageContext.request.contextPath}/addBoard" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>boardTitle</td>
				<td>
					<input type="text" name="boardTitle" id="boardTitle">
				</td>
			</tr>
			<tr>
				<td>boardfile</td>
				<td>
					<div>
						<button id="appendFile" type="button">파일추가</button>
					</div>
					<div id="fileDiv">
						<input type="file" name="boardfile" class="boardfile">
					</div>
				</td>
			</tr>
		</table>
		<button id="addBtn" type="button">입력</button>
	</form>
	<script>
		document.querySelector('#appendFile').addEventListener('click', ()=>{
			let flag = false;
			// input type=file 추가
			let boardfiles = document.querySelectorAll('.boardfile');
			boardfiles.forEach((e)=>{
				if (e.value == '') {
					alert('공백의 boardfile이 있습니다');
					flag = true;
					return;
				}
			})
			
			if (flag) {
				return;
			}

			let inputFile = document.createElement('input');
			inputFile.setAttribute('type', 'file');
			inputFile.setAttribute('name', 'boardfile');
			inputFile.setAttribute('class', 'boardfile');
			document.querySelector('#fileDiv').appendChild(inputFile);
			
		})
	
		document.querySelector('#addBtn').addEventListener('click', ()=>{
			// alert('addBtn Click');
			// 폼 유효성 검사
			if (document.querySelector('#boardTitle').value == '') {
				alert('title을 입력하세요');
				return;
			}
			
			// 파일이 추가되지 않은 node(input type=file) 삭제
			let boardfiles = document.querySelectorAll('.boardfile');
			boardfiles.forEach((e)=>{
				if(e.value == '') {
					e.remove(); // node 삭제
				}
			})
			
			document.querySelector('#addForm').submit();
		})
	
	</script>
</body>
</html>