<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="ui container">
		<h1>회원가입</h1>

		<form class="ui form segment">
			<div class="field">
				<label for="id">아이디</label>
				<input id="id" name="id" placeholder="아이디" type="text">
			</div>

			<div class="two fields">
				<div class="field">
					<label for="password">비밀번호</label>
					<input id="password" name="password" type="password">
				</div>

				<div class="field">
					<label for="passwordConfirm">비밀번호 확인</label>
					<input id="passwordConfirm" name="passwordConfirm" type="password">
				</div>
			</div>

			<div class="field">
				<label for="name">이름</label>
				<input id="name" placeholder="이름" name="name" type="text" />
			</div>

			<div class="field">
				<label for="gender">성별</label>
				<input id="gender" placeholder="성별" name="password" type="radio">
			</div>

			<div class="field">
				<label for="email">이메일</label>
				<input id="email" placeholder="이메일" name="email" type="text">
			</div>

			<div class="field">
				<label for="address">주소</label>
				<input id="address" placeholder="주소" name="address" type="text">
			</div>

			<div class="inline field">
				<div class="ui checkbox">
					<input type="checkbox" name="terms">
					<label>I agree to the terms and conditions</label>
				</div>
			</div>
			<div class="ui primary submit button">Submit</div>
			<div class="ui error message"></div>
		</form>
	</div>
