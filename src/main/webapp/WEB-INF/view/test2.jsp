<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<form class="fPwForm" id=findPwForm action="findPw" method="post">
		<div class="input field">
			<label>아이디</label> <input placeholder="아이디 입력" name="mem_id" type="text" id="findId">
		</div>
		<div class="input field">
			<label>이름</label> <input placeholder="이름 입력" name="mem_name" type="text" id="findName">
		</div>
		<input type="submit" value="찾기"/>
	</form>
</body>
</html>