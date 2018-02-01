<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui container">
	<form action="memUpdateInfo" method="post">
		<div class="field">
			<label>회원번호</label>
			<input class="ui input" type="text" value="${memInfo.mem_num}" disabled>
			<input type="hidden" name="mem_num" value="${memInfo.mem_num}">
		</div>
		<div class="field">
			<label>아이디</label>
			<input class="ui input" type="text" value="${memInfo.mem_id}" disabled>
		</div>
		<div class="field">
			<label>이름</label>
			<input class="ui input" type="text" name="mem_name" value="${memInfo.mem_name}" disabled>
		</div>
		<div class="field">
			<label>등급</label>
			<input class="ui input" type="text" name="mem_grade" value="${memInfo.mem_grade}" >
		</div>
		<div class="field">
			<input class="ui button" type="submit" value="수정">
			<a class="ui button" href="http://localhost:8090/finalproject/memInfo">취소</a>
		</div>
	</form>
</div>

<div class="ui tiny modal loginStatus">
	<i class="close icon"></i>
	<div class="ui header">회원정보수정</div>
	<div class="actions">
		<div class="ui tiny green button">닫기</div>
	</div>
</div>