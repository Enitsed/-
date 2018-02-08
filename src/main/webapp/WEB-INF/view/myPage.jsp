<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
<script type="text/javascript">
	function asd(){
		
		alert("asda");
	}
</script>
<!-- 몸통 -->
<div class="ui container">
	
	<div class="profile_wrap">
	
		<div class="img_wrap">
			<c:if test="${not empty userDTO.upload}">
			<!--  <img src ="resources/images/profile/${userDTO.upload}">-->
			<img src="./profile/${userDTO.upload}">
			</c:if>			
		</div>
	
		<div class="input_wrap">
				<input type="file" id="input_img"/>
		</div>
	</div>
	
	<form method="post" class="ui form" action="updateInfo">
		<div class="field">
			<label>아이디</label>
			<input class="ui input" type="text" value="${userDTO.mem_id}" disabled>
			<input type="hidden" name="mem_id" value="${userDTO.mem_id}">
		</div>
		<div class="field">
			<label>비밀번호</label>
			<input class="ui input" type="text" name="mem_pw" value="${userDTO.mem_pw}">
		</div>
		<div class="field">
			<label>성별</label>
			<input class="ui input" type="text" value="${userDTO.mem_sex}" disabled>
			<input name="mem_sex" type="hidden" value="${userDTO.mem_sex}">
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
			<a class="ui button" href="http://localhost:8090/finalproject/main">취소</a>
		</div>
	</form>
</div>
<div class="ui tiny modal updateInfoStatus">
	<i class="close icon"></i>
	<div class="ui header">
		회원정보 수정
	</div>
	<div class="actions">
		<div class="ui tiny green button">닫기</div>
	</div>
</div>
