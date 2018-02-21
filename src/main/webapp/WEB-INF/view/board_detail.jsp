<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment">
	<!-- 상세보기  -->

	<div class="ui container">

		<div class="ui container center aligned">
			<!-- 버튼 리스트 -->
			<div class="ui items">
				<div class="ui buttons">
					<c:if test="${userDTO.mem_num eq dto.mem_num }">
						<div class="ui vertical animated fade inverted blue button"
							onclick="location.href='boardUpdate?num=${dto.board_num}&currentPage=${currentPage }'">
							<div class="visible content">
								<i class="edit icon"></i>
							</div>
							<div class="hidden content">수정</div>
						</div>
						<div class="ui vertical animated fade inverted blue button"
							onclick="location.href='boardDelete?num=${dto.board_num}&currentPage=${currentPage }'">
							<div class="visible content">
								<i class="trash icon"></i>
							</div>
							<div class="hidden content">삭제</div>
						</div>
					</c:if>
					<c:if test="${userDTO.mem_id eq 'admin'}">
						<div class="ui vertical animated fade inverted blue button"
							onclick="location.href='boardDelete?num=${dto.board_num}&currentPage=${currentPage }'">
							<div class="visible content">
								<i class="trash icon"></i>
							</div>
							<div class="hidden content">삭제</div>
						</div>
					</c:if>
					<!--  
					<c:url value="free" var="pageList">
						<c:param name="currentPage" value="${currentPage }"></c:param>
					</c:url>
					-->
					<div class="ui sizes animated fade inverted blue button"
						>
						<div class="visible content">
							<i class="list icon"></i>
						</div>
						<div class="hidden content"><a href="javascript:history.back()">목록</a></div>
					</div>
				</div>
			</div>

			<div class="circular ui image items">
				<img src="resources/images/travel.jpg" width="300px">
			</div>

			<table class="ui fixed celled table">
				<thead>
					<tr>
						<th>작성자</th>
						<td>${dto.board_writer }</td>
						<th>작성일</th>
						<td>${dto.board_date }</td>
					</tr>
					<tr>
						<th>제 목</th>
						<td colspan="3">${dto.board_name }</td>
					</tr>
					<tr>
						<th colspan="4">내 용</th>
					</tr>

				</thead>

				<tbody>
					<tr>
						<td colspan="4">${dto.board_content }</td>
					</tr>
				</tbody>
			</table>

		</div>

	</div>
</div>
