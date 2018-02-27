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

<div class="ui four cards" id="fcards">
		<c:forEach items="${list}" var="dto">
	  <div class="card" id="card">
	  	      <a id="title">${dto.movie_kor_title}</a>
	    <div class="image">
	      <input type="hidden" value="${dto.mem_num}" id="member_num"/>
	      <c:choose>
	      	<c:when test="${dto.movie_image eq '이미지 없음' }">
	      		<img src = "resources/images/no_image.png" id="no_img">
	      	</c:when>
	      	<c:otherwise>
	      		<img src="${dto.movie_image}" id="card_imgae">	
	      	</c:otherwise>
	      </c:choose>
	      
	    </div>
	    
	  </div>
	  	 </c:forEach>
	  
	  </div>



</body>
</html>