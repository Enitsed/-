<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 빵덩어리 -->
<div class="ui container list">
	<div class="ui tiny breadcrumb">
		<a class="section">Home</a> <i class="right chevron icon divider"></i>
		<div class="active section">정보수정</div>
	</div>
</div>

<div class="ui container">
	<div class="field">
		<label>회원번호</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>아이디</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>이름</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>등급</label>
	</div>

	<c:forEach var="list" items="${memList}">
		<div class="field">
			<input class="ui input" type="text" value="${list.mem_num}" disabled>
			<input type="hidden" name="mem_id" value="${list.mem_num}">
			<input class="ui input" type="text" value="${list.mem_id}" disabled>
			<input class="ui input" type="text" value="${list.mem_name}" disabled>
			<input class="ui input" type="text" name="mem_grade" value="${list.mem_grade}">
		</div>
	</c:forEach>
	<div class="field">
	</br>
		<form action="memUpdateInfo" method="post">
			<input class="ui button" type="submit" value="수정">
			<a class="ui button" href="http://localhost:8090/finalproject/memInfo">취소</a>
		</form>
	</div>
</div>

<div class="ui tiny modal loginStatus">
	<i class="close icon"></i>
	<div class="ui header">회원정보수정</div>
	<div class="actions">
		<div class="ui tiny green button">닫기</div>
	</div>
</div>