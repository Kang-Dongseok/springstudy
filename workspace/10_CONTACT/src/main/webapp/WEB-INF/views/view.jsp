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
			$('#update_page_btn').click(function(){
				$('#f').attr('action', 'updateContact.do');
				$('#f').submit();
			})
			$('#delete_btn').click(function(){
				if (confirm('삭제할까요?')) {
					location.href = 'deleteContact.do?no=${contact.no}';
				}
			})
			$('#list_btn').click(function(){
				location.href = 'selectContactList.do';
			})
		})
	</script>
</head>
<body>
	
	<h1>연락처 보기</h1>
	<form id="f" method="post">
		이름<br>
		<input type="text" name="name" value="${contact.name}"><br><br>
		전화<br>
		<input type="text" name="tel" value="${contact.tel}"><br><br>
		주소<br>
		<input type="text" name="addr" value="${contact.addr}"><br><br>
		이메일<br>
		<input type="text" name="email" value="${contact.email}"><br><br>
		비고<br>
		<input type="text" name="note" value="${contact.note}"><br><br>
		<input type="hidden" name="no" value="${contact.no}">
		<input type="button" value="수정하기" id="update_page_btn">
		<input type="button" value="삭제하기" id="delete_btn">
		<input type="button" value="전체연락처" id="list_btn">
	</form>
	
</body>
</html>