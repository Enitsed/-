<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div class="mymenu">
	<div class="ui three item menu">
			<a class="item" href="profile"> 나의 평점 </a> 
			<a class="item" href="mylist"> 마이리스트 </a> 
			<a class="item" href="myboard"> 내가 쓴 글 </a>
		</div>
	</div>
		<h2 class="ui header">
 		 <i class="edit icon"></i>
  			<div class="content">
   			내가 쓴 게시물
  		</div>
		</h2>
		
		
		<div class="ui clearing segment" id="board_segment">
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
								value="boardDetail2">
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
		</div>
		
		<h2 class="ui header">
 		 <i class="comment outline icon"></i>
  			<div class="content">
   			나의 코멘트
  		</div>
		</h2>
		<div class="ui clearing segment" id="board_segment">
			<div class="ui feed">
			<c:forEach items="${comment}" var="dto">
				 <div class="event">
  					<div class="label">
    				<c:choose>
						<c:when test="${dto.movie_image eq '이미지 없음'}">
							<img id="myimg" src="resources/images/no_image.png">
						</c:when>
						<c:otherwise>
							<c:forTokens var="item" items="${dto.movie_image}" delims="|"
								end="0">
								<img id="myimg" src="${item}">
							</c:forTokens>
						</c:otherwise>
					</c:choose>
  				</div>
  			<div class="content">
    		<div class="summary">
      		<a class="user">
        		${dto.movie_kor_title}
      		</a>
      		<div class="date">
        	<fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short"
									value="${dto.regdate}" />
      		</div>
      		<div class="extra text">${dto.replytext}</div>
    	</div>
    		<div class="meta" id="metaimage">
      		<a class="like">
        	<i class="thumbs up outline icon"></i> ${dto.likecount } Likes
      		</a>
      		<a href="mycommentdelete?comment_num=${dto.comment_num}">
      		<i class="trash icon"></i>삭제
      		</a>
    	</div>
  	</div>
	</div>
	<hr>
	</c:forEach>			
	</div>
</div>
</body>
</html>