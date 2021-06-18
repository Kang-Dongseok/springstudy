<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#list_btn').click(function(){
				location.href = 'selectContactList.do';
			})
		})
	</script>
</head>
<body>
	<h1>연락처 등록</h1>
	<form action="insertContact.do">
		이름<br>
		<input type="text" name="name"><br><br>
		전화<br>
		<input type="text" name="tel"><br><br>
		주소<br>
		<input type="text" name="addr"><br><br>
		이메일<br>
		<input type="text" name="email"><br><br>
		비고<br>
		<input type="text" name="note"><br><br>
		<button>연락처 저장하기</button>
		<input type="button" value="전체연락처" id="list_btn">
	</form>
</body>
</html>
