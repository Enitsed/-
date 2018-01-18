<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>회원가입</title>

	<link href="resources/css/semantic.min.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="ui container">
		<h1>회원가입</h1>

		<div class="ui form segment">
			<div class="field">
				<label for="id">아이디</label>
				<input id="id" placeholder="아이디" type="text">
			</div>

			<div class="two fields">
				<div class="field">
					<label for="password">비밀번호</label>
					<input id="password" type="password">
				</div>
	
				<div class="field">
					<label for="passwordConfirm">비밀번호 확인</label>
					<input id="passwordConfirm" type="password">
				</div>
			</div>
			
			<div class="field">
				<label for="name">이름</label>
				<input id="name" placeholder="이름" type="text" />
			</div>

			<div class="field">
				<label for="gender">성별</label>
				<input id="gender" placeholder="성별" type="text">
			</div>

			<div class="field">
				<label for="email">이메일</label>
				<input id="email" placeholder="이메일" type="text">
			</div>
			
			<div class="field">
				<label for="address">주소</label>
				<input id="address" placeholder="주소" type="text">
			</div>
			
			<button class="ui blue button" type="submit" id="btn">Submit</button>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
	<script src="resources/js/semantic.min.js"></script>
	<script src="resources/js/custom.js"></script>

</body>
</html>
