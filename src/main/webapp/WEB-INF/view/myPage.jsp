<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 빵덩어리 -->
<div class="ui container list">
	<div class="ui tiny breadcrumb">
		<a class="section">Home</a> <i class="right chevron icon divider"></i>
		<div class="active section">회원정보 수정</div>
	</div>
</div>

<!-- 몸통 -->
<div class="ui container">
	<form method="post" class="ui form" action="updateInfo">
		<div class="field">
			<label>아이디</label>
			<input class="ui input" type="text" name="mem_id" value="${userDTO.mem_id}" disabled>
		</div>
		<div class="field">
			<label>비밀번호</label>
			<input class="ui input" type="text" name="mem_pw" value="${userDTO.mem_pw}">
		</div>
		<div class="field">
			<label>성별</label>
			<input class="ui input" type="text" value="${userDTO.mem_sex}" disabled>
		</div>
		<div class="field">
			<label>이름</label>
			<input class="ui input" type="text" name="mem_name" value="${userDTO.mem_name}">
		</div>
		<div class="field">
			<label>이메일</label>
			<input class="ui input" type="text" name="mem_email" value="${userDTO.mem_email}">
		</div>
		<div class="field">
			<label>주소</label>
			<input class="ui input" type="text" name="mem_address" value="${userDTO.mem_address}">
		</div>
		<div class="field">
			<input class="ui button" type="submit" id="updateBtn" value="수정">
		</div>
	</form>
</div>