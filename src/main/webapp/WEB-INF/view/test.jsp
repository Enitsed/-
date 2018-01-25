<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<form class="fIdForm" id=findIdForm action="findId" method="post">
		<div class="input field">
			<label>이름</label> <input placeholder="이름 입력" name="mem_name" type="text" id="findName">
		</div>
		<div class="input field">
			<label>이메일</label> <input placeholder="이메일 입력" name="mem_email" type="text" id="findEmail">
		</div>
		<input type="submit" value="찾기"/>
	</form>
</body>
</html>