<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 빵덩어리 -->
<div class="ui container list">
   <div class="ui tiny breadcrumb">
      <a class="section">Home</a> <i class="right chevron icon divider"></i>
      <div class="active section">검색결과</div>
   </div>
</div>

<!--  -->

<div class="ui container contents">
   <div class="ui clearing segment" >
   <table class="ui celled padded table">
   
    <thead align="center">
     <tr>
      <th>'${map.keyword }' 에 대한 검색결과 (${map.searchcount }개)</th>
     </tr>
    </thead>
    
    <tbody>
     <tr>
      <td>

      <div class="ui link special cards four columns">
         <c:forEach var="i" items="${map.searchlist}">
            <div class="card column blurring dimmable image main_movie">
               <!-- 영화 번호 넣을자리 -->
               <input type="hidden" value="${i.movie_num}" />
               <!-- 영화이미지 넣을자리 -->
               <img src="resources/images/travel.jpg">
               <div class="ui dimmer">
                  <div class="ui content">
                     <div class="ui center">
                        <p>${i.movie_kor_title}</p>
                        <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
                        <br /> <br />
                        <div class="ui divider"></div>
                        <br /> <br /> <br />
                        <div class="ui star rating" data-rating="5" data-max-rating="5"></div>
                     </div>
                  </div>
               </div>
            </div>
            
            <div class="ui modal movie" id="modal${i.movie_num}">
               <i class="close icon"></i>
               <div class="header">영화</div>
               <div class="image content">
                  <div class="ui medium image">
                     <img src="resources/images/travel.jpg">
                  </div>
                  <div class="description">
                     <div class="ui header">영화제목 : ${i.movie_kor_title}</div>
                     <h4>줄거리 : ${i.movie_summary}</h4>
                     <p>
                        개봉일 :
                        <fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short"
                           value="${i.movie_production_date}" />
                     </p>
                  </div>
               </div>
               <div class="actions">
                  <div class="ui black deny button">닫기</div>
                  <div class="ui positive right labeled icon button">
                     상세페이지로 이동 <i class="checkmark icon"></i>
                  </div>
               </div>
               <div id="bb"></div>

            </div>
         </c:forEach>
         <div class="ui top right attached label green" id=  "addBtn"class="addBtn">
            <a href="javascript:moreList();" > <i
               class="far fa-hand-point-down"></i> &nbsp; 더 보기
                <input type="hidden" name="currentPage" id="currentPage" value="1" />
            </a>
         </div>
      </div>

      </td>
      </tr>
    </tbody>
    <tfoot>
    </tfoot>
   </table>
   
   </div>
</div>