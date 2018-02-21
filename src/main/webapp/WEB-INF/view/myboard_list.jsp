<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div class="ui four item menu boardCategoryMenu">
			<a class="item" href="profile"> 나의 평점 </a> 
			<a class="item" href="mylist"> 마이리스트 </a> 
			<a class="item" href="myboard"> 내가 쓴 글 </a>
	</div>
		
		<table class="ui selectable celled padded table">
			<thead>
				<tr>
					<th class="center aligned two wide" >작성일</th>
					<th class="center aligned ten wide" >제목</th>
					<th class="center aligned two wide" >조회 수</th>
				</tr>
			</thead>
			<tbody>
			  
				<c:forEach items="${aList}" var="boardDTO">
					<tr>
						<td class="center aligned"><span>${boardDTO.board_date }</span>
						</td>
						
						<td class="center aligned"><c:url var="boardView"
								value="boardDetail">
								<c:param name="num" value="${boardDTO.board_num }"></c:param>
								<c:param name="currentPage" value="${pv.currentPage}"></c:param>
							</c:url> <a href="${boardView}"> ${boardDTO.board_name}</a>
						</td>
						<td class="center aligned">${boardDTO.board_hits}</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<!-- 버튼 리스트 -->
		<c:if test="${pv.startPage > 1}">
			<div class="ui animated button"
				onclick="location.href='myboard?currentPage=${pv.startPage - pv.blockPage}'">
				<div class="visible content">이전</div>
				<div class="hidden content">
					<i class="left arrow icon"></i>
				</div>
			</div>
		</c:if>

		<c:forEach var="i" begin="${pv.startPage }" end="${pv.endPage }">
			<c:url var="currPage" value="free">
				<c:param name="currentPage" value="${i }" />
			</c:url>
			<a class="ui button" href="${currPage }"> <c:out value="${i }" />
			</a>
		</c:forEach>

		<c:if test="${pv.totalPage>pv.endPage }">
			<div class="ui animated button"
				onclick="location.href='myboard?currentPage=${pv.startPage + pv.blockPage }'">
				<div class="visible content">다음</div>
				<div class="hidden content">
					<i class="right arrow icon"></i>
				</div>
			</div>
		</c:if>
		
</body>
</html>