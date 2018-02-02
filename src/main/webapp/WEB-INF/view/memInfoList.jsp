<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui container">
<div class="ui clearing segment">
	<table class="ui selectable celled padded table">
		<thread>
			<tr>
				<th class="center aligned two wide">회원번호</th>
				<th class="center aligned two wide">아이디</th>
				<th class="center aligned two wide">이름</th>
				<th class="center aligned two wide">등급</th>
			</tr>
		</thread>
		<tbody>
		<c:forEach var="list" items="${memList}">
			<tr>
				<td class="center aligned"><a class="ui input" href="memUpdateInfo?mem_num=${list.mem_num}">${list.mem_num}</a>
				<input type="hidden" name="mem_num" value="${list.mem_num}"></td>
				<td class="center aligned"><a class="ui input" type="text">${list.mem_id}</a></td>
				<td class="center aligned"><a class="ui input" type="text">${list.mem_name}</a></td>
				<td class="center aligned"><a class="ui input" type="text">${list.mem_grade}</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
	<div class="field">
		</br> <a class="ui button" href="http://localhost:8090/finalproject/main">취소</a>
	</div>

</div>