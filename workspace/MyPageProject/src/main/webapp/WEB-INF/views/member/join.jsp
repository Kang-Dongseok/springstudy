<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			fn_join();
		});
		function fn_join(){
			$('#f').submit(function(event){
				if ($('#id').val() == '' || $('#pw').val() == '' || $('#email').val == ''){
					alert('필수 정보를 모두 입력하세요.')
					event.preventDefault();
					return false;
				}
				if ($('#pw').val() != $('#pw2').val()) {
					alert('비밀번호 일치 여부를 확인하세요.')
					event.preventDefault();
					return false;
				}
				
			});
		}
	</script>
</head>
<body>

	<h1>회원가입</h1>

	<form action="join.do" id="f" method="post">
		아이디<br>
		<input type="text" name="id" id="id"><br><br>
	
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
	
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br><br>
		
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		
		연락처<br>
		<input type="text" name="tel" id="tel"><br><br>
		
		이메일<br>
		<input type="text" name="email" id="email"><br><br>
		
		주소<br>
		<input type="text" name="address" id="address"><br><br>
		
		<button>가입하기</button>
		<input type="button" value="돌아가기" onclick="location.href='index.do'">
	</form>
</body>
</html>