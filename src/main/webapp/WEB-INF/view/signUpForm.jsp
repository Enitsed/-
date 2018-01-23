<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<div class="ui container">
		<h1>회원가입</h1>

		<form class="ui form segment" id="signUpForm" action="signup" method="post">
			<div class="field">
				<label for="id">아이디</label>
				<input id="id" name="mem_id" placeholder="아이디" type="text">
				<div class="ui basic teal small button items" id="checkId">아이디 중복 검사</div>
				<input type="hidden" id = "id_ck" value = "0"/>
			</div>

			<div class="two fields">
				<div class="field">
					<label for="password">비밀번호</label>
					<input id="password" name="mem_pw" type="password">
				</div>

				<div class="field">
					<label for="passwordConfirm">비밀번호 확인</label>
					<input id="passwordConfirm" name="passwordConfirm" type="password">
				</div>
			</div>

			<div class="field">
				<label for="name">이름</label>
				<input id="name" placeholder="이름" name="mem_name" type="text" />
			</div>

			<div class="field">
				<label>성별</label>
				<div class="ui fluid selection dropdown">
					<input id="gender" type="hidden" name="mem_sex">
					<div class="default text">성별</div>
					<i class="dropdown icon"></i>
					<div class="menu">
						<div class="item" data-value="남자">남자</div>
						<div class="item" data-value="여자">여자</div>
					</div>
				</div>
			</div>

			<div class="field">
				<label for="email">이메일</label>
				<input id="email" placeholder="이메일" name="mem_email" type="text">
			</div>

			<div class="field">
				<label for="address">주소</label>
				<input id="address" placeholder="주소" name="mem_address" type="text">
			</div>

			<div class="inline field">
				<div class="ui checkbox">
					<input type="checkbox" name="terms">
					<label>저는 이 사이트의 회원 가입 약관에 동의합니다.</label>
				</div>
			</div>
			<div class="ui primary submit button">회원가입</div>
			<div class="ui reset red button">초기화</div>
			<div class="ui error message"></div>
		</form>
	</div>
	

