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
<div id="mm">
	<div class="mymenu">
	<div class="ui three item menu">
			<a class="item" href="profile"> 나의 평점 </a> 
			<a class="item" href="mylist"> 마이리스트 </a> 
			<a class="item" href="myboard"> 내가 쓴 글 </a>
		</div>
	</div>
	
	
	<div>
	<h2 class="ui header">
  			<div class="content">
   			<a>${mem_name}</a> 님이 보고싶은 영화
   			<hr>
   <div class="ui vertical menu">
  <div class="ui dropdown item">
    Categories
    <i class="dropdown icon"></i>
    <div class="menu" id="categorymenu">
      <a class="item" id="cate">전체</a>
      <a class="item" id="cate">스릴러</a>
      <a class="item" id="cate">액션</a>
      <a class="item" id="cate">드라마</a>
      <a class="item" id="cate">공포</a>
      <a class="item" id="cate">코메디</a>
      <a class="item" id="cate">미스터리</a>
      <a class="item" id="cate">범죄</a>
      <a class="item" id="cate">스포츠</a>
      <a class="item" id="cate">어드벤처</a>
      <a class="item" id="cate">전쟁</a>
    </div>
  </div>
 </div>
  		</div>
		</h2>

</div>

<div class="ui link special cards four columns" id="movieListWindow">
			<c:forEach var="i" items="${list}">
				<div class="card column blurring dimmable image main_movie" id="mylistcard">
					<!-- 영화 번호 넣을자리 -->
					<input type="hidden" value="${i.movie_num}" />
					<!-- 영화이미지 넣을자리 -->
					<c:choose>
						<c:when test="${i.movie_image eq '이미지 없음'}">
							<img class="slideImg" src="resources/images/no_image.png" id="mylistnull">
						</c:when>
						<c:otherwise>
							<c:forTokens var="item" items="${i.movie_image}" delims="|" end="0">
								<img class="slideImg" src="${item}" id="mylistimg">
							</c:forTokens>
						</c:otherwise>
					</c:choose>
					<div class="ui dimmer">
						<div class="ui content">
							<div class="ui center">
								<p>${i.movie_kor_title}</p>
								<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
								<br /> <br />
							</div>
						</div>
					</div>
				</div>
							</c:forEach>
				


 </div>
 </div>
</body>
</html>