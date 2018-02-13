<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<script>

	function moreList() {
		var page = (parseInt($("#currentPage").val()) + 1);
		var category = parseInt($("#category").val());
		$.ajax({
			url : 'addMovie.do?page=' + page + '&category=' + category,
			type : 'GET',
			dataType : 'json',
			success : function(data) {
						//console.log(data);
				var content = "";
				$.each(data, function(index, value){
					var imagUrl = value.movie_image;
					var image;
					if(imagUrl == "이미지 없음"){
						image = "resources/images/no_image.png";
					}else{
						imagUrl = imagUrl.split('|');
						image = imagUrl[0];
					}
					content = '<div class="card column blurring dimmable image main_movie">' + 
								'<input type="hidden" value="'+value.movie_num+'" />' +
								'<img class="slideImg" src="'+image+'">' +
								'<div class="ui dimmer">'+
								'<div class="ui content">'+
								'<div class="ui center">'+
								'<p>'+value.movie_kor_title+'</p>'+
								'<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />'+
								'<br /> <br />'+
								'<div class="ui divider"></div>'+
								'<br /> <br /> <br />'+
								'<div class="ui star rating" data-rating="5" data-max-rating="5" id="'+value.movie_num+'"></div>'+
								'</div>'+
								'</div>'+
								'</div>'+
								'</div>'+
								'<div class="ui modal movie" id="modal'+value.movie_num+'">'+
								'<i class="close icon"></i>'+
								'<div class="header">영화</div>'+
								'<div class="image content">'+
								'<div class="ui medium image">'+
								'<img src="'+ image +'">'+
								'</div>'+
								'<div class="description">'+
								'<div class="ui header">영화제목 : ' + value.movie_kor_title + '</div>'+
								'<h4>줄거리 : '+value.movie_summary+'</h4>'+
								'<p>개봉일 : '+
								'</p>'+
								'</div>'+
								'</div>'+
								'<div class="actions">'+
								'<div class="ui black deny button">닫기</div>'+
								'</div>'+
								'<div id="bb"></div>'+
								'</div>';
								
					$("#movieListWindow").append(content); 
					$('.special.cards .image.main_movie').dimmer({
					on: 'hover'
					});
					
							
					$(".ui.rating").unbind("click"); //클릭이벤트 없앰
					$('.ui.rating').rating();
					$('.ui.rating').on("click",function(){
						event.stopPropagation();
						var rating = $(this).rating("get rating", this);
						var num =  $('#member_num').val();
						var movie_num = $(this).attr("id");
						/* alert(rating + " " + num + " " + movie_num); */
						if(num < 1){
							alert("로그인부터 해라");
							return; 
						}
						$.ajax({
							type: 'GET',
							dataType: 'json',
							url: 'addrating.do?movie_num=' + movie_num + "&member_num=" + num + "&rating="+rating,
							success: function (res) {
								alert("평점이 등록 되었습니다.")
							},
							error: function (request, status, error) {
								alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
							}
						});
					}); /////////////////ui rating click function

					
					$('.main_movie').on('click', function () {
					      var mnum = $(this).find('input[type="hidden"]').val();
					      var modal = '#modal' + mnum;
					      var hi = $(this).parent().find('.hiddennum').attr('name');//10으로 초기화

					      $.ajax({
					         url: 'info?movie_num=' + mnum,
					         type: 'GET',
					         async: false,
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
										'<input type="hidden" class="movie_num" value="'+value.movie_num+'"/>'+
										'<div class="date">' + sm + '</div></div>' +
										'<div class="extra text">' + value.replytext + '</div>' +
										'<div class="meta">' +
										'<a class="like" value="'+value.comment_num+'" name="'+value.comment_num+'"><i class="like icon"></i>' + value.likecount + '</a>'
										if(session_id==value.mem_id){
											comment+='<a class="del" value="'+value.comment_num+'" id="'+value.movie_num+'"><i class="trash icon"></i>삭제</a>'
										}
										+'</div></div></div>';
										$(comment).appendTo(".ui.large.feed");
										if(index==more)
						            		return false;
					            	
								});//each

					            $('#hidden').remove();
					            $('.more').remove();
					            var plus="";
					            if(data.length>more)
					            	plus+= '<input type="hidden" value="'+mnum+'" id="hidden"/>'+
										 '<a class="more" id="10">댓글 더보기</a>'
									$(plus).appendTo('.seemore');

					            $(modal).modal('show');
					           
					         }//success
					      });//ajax끝
					      
					   });//movie modal 클릭 끝

				})
				
				if(data.length < 8){
					$("#currentPage").val(page - 1);
				}else{	
					$("#currentPage").val(page);
				}
			}, //success function 끝
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
			}
			
		});

		
	};

</script>


<div class="ui container contents">
	<div class="ui segment">
			<div id="navi">
		     <div id="menu1">
		       <h2><a href="#menu1">카테고리</a></h2>
		       <p><a href="movieInfoList?category=0">전체</a></p>
		       <p><a href="movieInfoList?category=1">액션</a></p>
		       <p><a href="movieInfoList?category=2">드라마</a></p>
		       <p><a href="movieInfoList?category=3">공포</a></p>
		       <p><a href="movieInfoList?category=4">스릴러</a></p>
		       <p><a href="movieInfoList?category=5">코메디</a></p>
		       <p><a href="movieInfoList?category=6">미스터리</a></p>
		       <p><a href="movieInfoList?category=7">범죄</a></p>
		       <p><a href="movieInfoList?category=8">스포츠</a></p>
		       <p><a href="movieInfoList?category=9">어드벤처</a></p>
		       <p><a href="movieInfoList?category=10">전쟁</a></p>
		     </div>
		  </div>
	
		<div class="ui link special cards four columns" id="movieListWindow">
			<c:forEach var="i" items="${movie}">
				<div class="card column blurring dimmable image main_movie">
					<!-- 영화 번호 넣을자리 -->
					<input type="hidden" value="${i.movie_num}" />
					<!-- 영화이미지 넣을자리 -->
					<c:choose>
						<c:when test="${i.movie_image eq '이미지 없음'}">
							<img class="slideImg" src="resources/images/no_image.png">
						</c:when>
						<c:otherwise>
							<c:forTokens var="item" items="${i.movie_image}" delims="|" end="0">
								<img class="slideImg" src="${item}">
							</c:forTokens>
						</c:otherwise>
					</c:choose>
					<div class="ui dimmer">
						<div class="ui content">
							<div class="ui center">
								<p>${i.movie_kor_title}</p>
								<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
								<br /> <br />
								<div class="ui divider"></div>
								<br /> <br /> <br />
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
							<img class="aa" src="${i.movie_image}">
						</div>
						<div class="description">
							<div class="ui header">영화제목 : ${i.movie_kor_title}</div>
							<h4>줄거리 : ${i.movie_summary}</h4>
							<p>
								감독 :
								<c:forEach var="j" items="${i.movie_director}">
									${j.director_name}
								</c:forEach>
							</p>
							<p>
								배우 :
								<c:forEach var="j" items="${i.movie_actor}">
									${j.actor_name}
								</c:forEach>
							</p>
							<p>
								장르 :
								<c:forEach var="j" items="${i.category}">
									${j.category_name}
								</c:forEach>
							</p>
							
							<p>개봉일 : 
							<fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short"
									value="${i.movie_opening_date}" /></p>
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

					</div>
					<div id="bb"></div>

				</div>
			</c:forEach>
		</div>
		<div class="ui horizontal divider">


			<a class="ui teal button" href="javascript:moreList();">
			<i class="far fa-hand-point-down"></i> &nbsp; 더 보기
				<input type="hidden" value = "1" id ="currentPage"/>
				<input type="hidden" value = "${category}" id ="category"/>
			</a>
		</div>

	</div>

</div>