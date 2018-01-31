<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <style>
    #slideShowImages { 
      border: 1px gray solid;
    }   
  
    #slideShowImages img { 
      border: 0.8em black solid;
      padding: 3px;
    }   
  </style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   var session_id = '${userDTO.mem_id}';
   var session_num = '${userDTO.mem_num}';
   // 영화 상세보기
   $('.main_movie').on('click', function () {
      var movie_num = $('.movie_num').val();
      var index = $(this).find('input[type="hidden"]').val();
      var modal = '#modal' + index;
      
      
      $.ajax({
         url: 'info?movie_num=' + index,
         type: 'GET',
         dataType: 'json',
         success: function (data) {
            
            $('.event').remove();
            $.each(data,function(index,value){
               var comment="";
               var sdate = new Date(value.regdate);
               var sm = sdate.getFullYear() + "/";
               sm = sm + (sdate.getMonth() + 1) + "/";
               sm = sm + sdate.getDate();

               comment +=
                  '<div class="event">' +
                  '<div class="label">' +
                  '<img src="resources/images/user.png">' +
                  '</div>' +
                  '<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
                  '<div class="content">' +
                  '<div class="summary">' +
                  '<a class="user">' + value.mem_id + '</a>' +
                  //'<input type="hidden" class="mem_id" value="' + value.mem_id + '"/>' +
                  '<input type="hidden" class="movie_num" value="'+value.movie_num+'"/>'+
                  '<div class="date">' + sm + '</div></div>' +
                  '<div class="extra text">' + value.replytext + '</div>' +
                  '<div class="meta">' +
                  '<a class="like" value="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
                  if(session_id==value.mem_id){
                     comment+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
                  }
                  +'</div></div></div>'
                  $(comment).appendTo(".ui.large.feed");
            });

            $(modal).modal('show');
            
         }
      });//ajax끝
      
   });//movie modal 클릭 끝
   
   
   $(document).on('click','.ui.blue.labeled.submit.icon.button',commentinsert);
   
   function commentinsert(){
      
      var comments = $(this).parent().find('textarea').val();//replytext
      var number = parseInt($(this).attr('id'));
      alert(comments);
      var comments = $('textarea').val();//코멘트 내용 가져오기
      //var number =  $(document).find('.movie_num').val();
      var number = parseInt($(this).attr('id'));
     
      $.ajax({
         
         url : 'insertcomment?mem_id='+session_id+'&replytext='+comments+'&movie_num='+number+'&mem_num='+session_num,
         type:'GET',
         dataType:'json',
         success : function(data){
            $('.event').remove();

            $.each(data,function(index,value){
               var insert="";
               var sdate = new Date(value.regdate);
               var sm = sdate.getFullYear() + "/";
               sm = sm + (sdate.getMonth() + 1) + "/";
               sm = sm + sdate.getDate();

               insert +=
                  '<div class="event">' +
                  '<div class="label">' +
                  '<img src="resources/images/user.png">' +
                  '</div>' +
                  '<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
                  '<div class="content">' +
                  '<div class="summary">' +
                  '<a class="user">' + value.mem_id + '</a>' +
                  '<input type="hidden" class="mem_id" value="' + value.mem_id + '"/>' +
                  '<div class="date">' + sm + '</div></div>' +
                  '<div class="extra text">' + value.replytext + '</div>' +
                  '<div class="meta">' +
                  '<a class="like" value="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
                  if(session_id==value.mem_id){
                     insert+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
                  }
                  +'</div></div></div>'
                  $(insert).appendTo(".ui.large.feed");
                  
            });
            $('textarea').val('');
            
            
         }//success끝
      });//ajax끝
   }
   
$(document).on('click','.like',like);

   
   function like(){
      var num = parseInt($(this).attr('value')); //코멘트 번호
      var now = $(this);
         var likey = parseInt($(this).text());
      if(session_id){
         
      }else{
         alert('먼저 로그인을 해주세요.');
         return false;
      }
         

      $.ajax({
         type: 'GET',
         dataType: 'json',
         url: 'like',
         data: 'mem_id=' + session_id + '&comment_num=' + num,
         success: function (data) {
            
            if (data.like != null) {
               likey+=1;
              
               now.text('  '+likey+' '+'Likes');
            
            } else if(data.like==null){
               likey -= 1;
              
               now.text('  '+likey+'  '+'Likes');
               
            }
            
         }//success끝
      });//ajax끝
   }//like function()끝
   

   $(document).on('click','.del',del);
   function del(){
      var comment_number = parseInt($(this).attr('value'));
      var movie_number = parseInt($(this).attr('id'));
      
      $.ajax({
    	  url : 'deletecomment',
    	  type:'GET',
    	  data : 'comment_num='+comment_number+'&movie_num='+movie_number,
    	  success : function(data){
    		  $('.event').remove();
    		  $.each(data,function(index,value){
                  var movie_comment="";
                  var sdate = new Date(value.regdate);
                  var sm = sdate.getFullYear() + "/";
                  sm = sm + (sdate.getMonth() + 1) + "/";
                  sm = sm + sdate.getDate();

                  movie_comment +=
                     '<div class="event">' +
                     '<div class="label">' +
                     '<img src="resources/images/user.png">' +
                     '</div>' +
                     '<input type="hidden" class="comment_num" value="' + value.comment_num + '"/>' +
                     '<div class="content">' +
                     '<div class="summary">' +
                     '<a class="user">' + value.mem_id + '</a>' +
                     '<input type="hidden" class="mem_id" value="' + value.mem_id + '"/>' +
                     '<div class="date">' + sm + '</div></div>' +
                     '<div class="extra text">' + value.replytext + '</div>' +
                     '<div class="meta">' +
                     '<a class="like" value="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
                     if(session_id==value.mem_id){
                    	 movie_comment+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
                     }
                     +'</div></div></div>'
                     $(movie_comment).appendTo(".ui.large.feed");
                     
               });
               
    	  }//success
      });//ajax
   }
   

   
});//document 끝
   
</script>




<!-- 빵덩어리 -->
<div class="ui container list">
   <div class="ui tiny breadcrumb">
      <a class="section">Home</a> <i class="right chevron icon divider"></i>
      <div class="active section">메인 페이지</div>
   </div>
</div>
<!-- 동영상 -->
<div class="ui fluid container video_clip" id="banner"
   data-vide-bg="resources/images/travel"
   data-vide-options="posterType: jpg, loop: true, muted: false"></div>

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
               
               <c:if test="${not empty userDTO.mem_id}">
               <div class="ui comments">
                  <form class="ui reply form">
                      <div class="field">
                           <textarea></textarea>
                      </div>
                         <div class="ui blue labeled submit icon button" id="${i.movie_num}">
                        <i class="icon edit"></i> Add Reply
                         </div>
                    </form>
               </div>
               </c:if>
               

               <div class="actions">
                  <div class="ui black deny button">닫기</div>
                  <div class="ui positive right labeled icon button">
                     상세페이지로 이동 <i class="checkmark icon"></i>
                  </div>
               </div>
            </div>
         </c:forEach>
         <a class="prev" onclick="plusSlides(-1)">❮</a> <a class="next"
            onclick="plusSlides(1)">❯</a>
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