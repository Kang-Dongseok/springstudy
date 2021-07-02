<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			fn_login();
		});
		function fn_login(){
			$('#f').submit(function(event){
				if ($('#id').val() == '') {
					alert('아이디를 입력하세요.');
					$('#id').focus();
					event.preventDefault();
					return false;
				} else if ($('#pw').val() == '') {
					alert('비밀번호를 입력하세요.');
					$('#pw').focus();
					event.preventDefault();
					return false;
				}
			});
		}  // fn_login()
	</script>
</head>
<body>

	<h1>홈페이지</h1>
	
	<c:if test="${loginUser == null}">
		<form action="login.do" id="f" method="post">
			<label>아이디 <input type="text" name="id" id="id" placeholder="아이디"></label><br>
			<label>비밀번호 <input type="password" name="pw" id="pw" placeholder="●●●●"></label><br>
			<button>로그인</button>
			<input type="button" value="회원가입" id="joinPage_btn" onclick="location.href='joinPage.do'">
		</form>
	</c:if>
	
	<c:if test="${loginUser != null}">
		${loginUser.name}님 환영합니다!<br>
		<input type="button" value="로그아웃" id="logout_btn" onclick="location.href='logout.do'">
		<input type="button" value="회원탈퇴" id="leave_btn" onclick="location.href='leave.do'">
	</c:if>
	
	<br><br>
	
	<input type="button" value="갤러리 게시판" onclick="location.href='galleryPage.do'">
	<input type="button" value="자유 게시판" onclick="location.href='boardPage.do'">
</body>
</html>