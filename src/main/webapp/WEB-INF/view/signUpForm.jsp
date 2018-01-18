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
		<h1>Register Now</h1>

		<div class="ui form segment">
			<div class="two fields">
				<div class="field">
					<label for="first_name">이름</label>
					<input id="first_name" placeholder="이름" type="text" />
				</div>

				<div class="field">
					<label for="last_name">성</label>
					<input id="last_name" placeholder="성" type="text">
				</div>
			</div>

			<div class="field">
				<label for="Email">이메일</label>
				<input id="Email" placeholder="이메일" type="text">
			</div>

			<div class="field">
				<label for="id">아이디</label>
				<input id="id" placeholder="아이디" type="text">
			</div>

			<div class="field">
				<label for="Password">비밀번호</label>
				<input id="Password" type="password">
			</div>

			<div class="field">
				<label for="PasswordConfirm">비밀번호 확인</label>
				<input id="PasswordConfirm" type="password">
			</div>

			<button class="ui blue button">Submit</button>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
	<script src="resources/js/semantic.min.js"></script>
	<script src="resources/js/custom.js"></script>

</body>
</html>
