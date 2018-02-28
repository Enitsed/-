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
	<div id="pro">

	<div class="mymenu">
	<div class="ui three item menu">
			<a class="item" href="profile"> 나의 평점 </a> 
			<a class="item" href="mylist"> 마이리스트 </a> 
			<a class="item" href="myboard"> 내가 쓴 글 </a>
		</div>
	</div>
		
		<div class="ui four cards" id="fcards">
		<c:forEach items="${rating}" var="dto">
	  <div class="card" id="cards">
	  	      <a id="title">${dto.movie_kor_title}</a>
	    <div class="image">
	      <input type="hidden" value="${dto.mem_num}" id="member_num"/>
	      <c:choose>
	      	<c:when test="${dto.movie_image eq '이미지 없음' }">
	      		<img src = "resources/images/no_image.png" id="no_img">
	      	</c:when>
	      	<c:otherwise>
	      		<img src="${dto.movie_image}">	
	      	</c:otherwise>
	      </c:choose>
	      
	    </div>
	    <div class="extra">
	      Rating:
	      <div class="ui star rating point" data-rating="${dto.star_point}" data-max-rating="5" id="${dto.movie_num}"></div>
	    </div>
	  </div>
	  	 </c:forEach>
	  
	  </div>
	</div>
</body>
</html>