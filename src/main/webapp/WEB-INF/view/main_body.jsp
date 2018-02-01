<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 동영상 -->
<div class="ui page dimmable">
	<div class="ui fluid container video_clip" id="banner" data-vide-bg="resources/images/travel" data-vide-options="posterType: jpg, loop: true, muted: false"></div>
</div>

<div class="ui dimmer">
	<div class="center">
		<div class="content">
			<div class="ui video" data-source="youtube" data-id="BX-OFZUU0_E" data-image="resources/images/test.jpg" style="max-width: 90%; left: 5%; padding-bottom: 50%;"></div>
			<div class="ui horizontal divider"><button class="ui red button" id="banner_close">Click to close</button>
			</div>
		</div>
	</div>
</div>
<!-- 바디 -->
<div class="ui container contents">
   <div class="ui segment">
	  <div class="ui top attached green label">박스 오피스 영화 리스트</div>
      <div class="ui link special cards four columns slide">
         <c:forEach var="i" items="${movie}">
            <div
               class="card column blurring dimmable image main_movie slide_box fade2">

               <input type="hidden" value="${i.movie_num}" />

               <!-- 영화 번호 넣을자리 -->
               <c:choose>
                  <c:when test="${i.movie_image eq '이미지 없음'}">
                     <img class="slideImg" src="resources/images/no_image.png">
                  </c:when>
                  <c:otherwise>
                     <c:forTokens var="item" items="${i.movie_image}" delims="|"
                        end="0">
                        <img class="slideImg" src="${item}">
                     </c:forTokens>
                  </c:otherwise>
               </c:choose>
               <!-- 영화이미지 넣을자리 -->
               <div class="ui dimmer">
                  <div class="ui content">
                     <div class="ui center">
                        <p>${i.movie_kor_title}</p>
                        <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
                        <br /> <br />
                        <div class="ui divider"></div>
                        <br /> <br /> <br />
                        <div class="ui star rating" data-rating="5" data-max-rating="5"></div>

                        <div class="ui star rating" data-rating="5" data-max-rating="5" id="${i.movie_num}"></div>

                     </div>
                  </div>
               </div>
            </div>

            <div class="ui modal movie" id="modal${i.movie_num}">
               <i class="close icon"></i>
               <div class="header">영화</div>
               <div class="image content">
                  <div class="ui medium image">
                     <img  src="">
                  </div>
                  <div class="description">
                     <div class="ui header">영화제목 : ${i.movie_kor_title}</div>
                     <h4>줄거리 : ${i.movie_summary}</h4>
                     <p>
                        개봉일 :
                        <fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short"
                           value="${i.movie_opening_date}" />
                     </p>
                  </div>
               </div>
               
               <div class="ui large feed">
               </div>
                
               <div class="seemore">
                 <input type="hidden" class="hiddennum" id="10" name="10"/>
               </div>

               <c:if test="${not empty userDTO.mem_id}">
                  <div class="ui left labeled input text_comment">
  					<input type="text" class="comment_m" placeholder="내용을 입력하세요...">
  					<div class="ui basic label" id="${i.movie_num}">
    					<i class="comment outline icon"></i>
  				  	</div>
				  </div>
               <div class="clearing item"></div>
               </c:if>
               
               <div class="actions">
                  <div class="ui black deny button">닫기</div>
                  <div class="ui positive right labeled icon button">
                    	 상세페이지로 이동 <i class="checkmark icon"></i>
                  </div>
               </div>
               
            </div>
         </c:forEach>
         <a class="prev" onclick="plusSlides(-1)">?</a> <a class="next"
            onclick="plusSlides(1)">?</a>
      </div>
   </div>

   <div class="ui segment">
	<div class="ui top attached green label">게시판 글 리스트</div>
      <div class="ui items">
         <div class="item">
            <div class="image" style="width: 100px">
               <img src="resources/images/test.jpg">
            </div>
            <div class="content">
               <a class="header">Header</a>
               <div class="meta">
                  <span>Description</span>
               </div>
               <div class="description">
                  <p></p>
               </div>
               <div class="extra">Additional Details</div>
            </div>
         </div>
         <div class="item">
            <div class="image" style="width: 100px">
               <img src="resources/images/test.jpg">
            </div>
            <div class="content">
               <a class="header">Header</a>
               <div class="meta">
                  <span>Description</span>
               </div>
               <div class="description">
                  <p></p>
               </div>
               <div class="extra">Additional Details</div>
            </div>
         </div>
      </div>
      <div class="ui top right attached label green inverted button">
         <i class="far fa-hand-point-down"></i> &nbsp; 더 보기
      </div>
   </div>

   <div class="ui segment">
	<div class="ui top attached green label">관련 뉴스기사</div>
      <c:forEach items="${list}" var="list">
         <div class="ui items">
            <div class="item">
               <div class="content">
                  <a href="${list.originallink}">${list.title}</a>
                  <div class="extra">
                     <span>${list.description}</span>
                  </div>
               </div>
            </div>
         </div>
      </c:forEach>
   </div>
</div>
