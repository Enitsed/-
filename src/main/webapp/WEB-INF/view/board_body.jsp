<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 빵덩어리 -->
	<div class="ui container list">
		<div class="ui tiny breadcrumb">
			<a class="section">Home</a>
			<i class="right chevron icon divider"></i>
			<div class="active section">자유게시판</div>
		</div>
	</div>
<!-- 보드 테이블 -->

	<div class="ui container">
	
		<div class="ui clearing segment">
		
			<table class="ui celled padded table">
				<thead>
					<tr>
						<th class="center aligned" width="20px">작성일</th>
						<th class="center aligned" width="20px">추천 수</th>
						<th class="center aligned" width="20px">작성자</th>
						<th class="center aligned" width="50px">제목</th>
						<th class="center aligned" width="20px">조회 수</th>
	
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${aList }" var="boardDTO">
						<tr>
							<td>
								<span class="ui center aligned">${boardDTO.board_date }</span>
							</td>
							<td class="center aligned">
								<c:url var="boardView" value="boardDetail">
									<c:param name="num" value="${boardDTO.board_num }"></c:param>
									<c:param name="currentPage" value="${pv.currentPage}"></c:param>
								</c:url>
								<a href="${boardView }">
									${boardDTO.board_name }
								</a>
							</td>
							<td>
								<div class="ui star rating" data-rating="3" data-max-rating="5"></div>
							</td>
							<td class="center aligned">
								<a href="#">${boardDTO.board_writer }</a>
							</td>
							<td class="center aligned">
								${boardDTO.board_hits }
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
				<!-- 버튼 리스트 -->
				<div class="ui animated button">
					<div class="visible content">이전</div>
					<div class="hidden content">
						<i class="left arrow icon"></i>
					</div>
				</div>
				<div class="ui animated button">
					<div class="visible content">다음</div>
					<div class="hidden content">
						<i class="right arrow icon"></i>
					</div>
				</div>
				<div class="ui right floated vertical animated fade button" id="writeBtn">
					<div class="hidden content">
						<a href="boardWrite">
							<i class="pencil icon"></i>
						</a>
					</div>
					<div class="visible content">
						글쓰기
					</div>
				</div>
				
		</div>
		
	</div>