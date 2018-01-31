<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 아이디 찾기 -->
<div class="ui container">
	<form class="ui form segment" action="findId" id="findId" method="post">
		<div class="two fields">
			<div class="input field">
				<label>이름</label> <input placeholder="이름 입력" name="mem_name"
					type="text" id="findName">
			</div>
			<div class="input field">
				<label>이메일</label> <input placeholder="이메일 입력" name="mem_email"
					type="text" id="findEmail">
			</div>
		</div>
		<div class="ui submit button">찾기</div>
	
		<div class="ui error message">
		</div>
	</form>
</div>

<div class="ui tiny modal findIdStatus">
	<i class="close icon"></i>
	<div class="ui header">
		아이디 중복확인
	</div>
	<div class="actions">
		<div class="ui tiny green button">닫기</div>
	</div>
</div>

