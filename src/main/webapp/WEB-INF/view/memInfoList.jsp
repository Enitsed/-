<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<input type="hidden" name="mem_num" value="${list.mem_num}">
			<input class="ui input" type="text" value="${list.mem_id}" disabled>
			<input class="ui input" type="text" value="${list.mem_name}" disabled>
			<input class="ui input" type="text" value="${list.mem_grade}" disabled>
		</div>
	</c:forEach>
	<div class="field">
	</br>
		<a class="ui button" href="memUpdate">수정</a>
		<a class="ui button" href="http://localhost:8090/finalproject/main">취소</a>
	</div>
</div>