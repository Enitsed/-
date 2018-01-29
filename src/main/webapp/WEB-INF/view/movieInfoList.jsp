<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	function moreList() {
		var page = (parseInt($("#currentPage").val()) + 1);
		$.ajax({
			url : 'addMovie.do?page=' + page,
			type : 'GET',
			dataType : 'json',
			success : function(data) {
						//console.log(data);
				var content = "";
				$.each(data, function(index, value){					
					var imagUrl = value.movie_image;
					var image;
					if(imagUrl == "이미지 없음"){
						image = "resources/images/travel.jpg";
					}else{
						imagUrl = imagUrl.split('|');
						image = imagUrl[0];
					}
					content = '<div class="card column blurring dimmable image main_movie">' + 
								'<input type="hidden" value="'+value.movie_num+'" />'+
								'<img class="slideImg" src="'+image+'">' +
								'<div class="ui dimmer">'+
								'<div class="ui content">'+
								'<div class="ui center">'+
								'<p>'+value.movie_kor_title+'</p>'+
								'<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />'+
								'<br /> <br />'+
								'<div class="ui divider"></div>'+
								'<br /> <br /> <br />'+
								'<div class="ui star rating" data-rating="5" data-max-rating="5"></div>'+
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
								'<div class="ui positive right labeled icon button">'+
								'상세페이지로 이동 <i class="checkmark icon"></i>'+
								'</div>'+
								'</div>'+
								'<div id="bb"></div>'+
								'</div>';
					$(content).appendTo("#movieListWindow");

				})
						
				if(data.length < 8){
					$("#currentPage").val(page - 1);
				}else{
					$("#currentPage").val(page);
				}
			},
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
		<div class="ui link special cards four columns" id="movieListWindow">
			<c:forEach var="i" items="${movie}">
				<div class="card column blurring dimmable image main_movie">
					<!-- 영화 번호 넣을자리 -->
					<input type="hidden" value="${i.movie_num}" />
					<!-- 영화이미지 넣을자리 -->
					<c:choose>
						<c:when test="${i.movie_image eq '이미지 없음'}">
							<img class="slideImg" src="resources/images/travel.jpg">
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
							<img src="${i.movie_image}">
						</div>
						<div class="description">
							<div class="ui header">영화제목 : ${i.movie_kor_title}</div>
							<h4>줄거리 : ${i.movie_summary}</h4>
							<p>개봉일 : 
							<fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short"
									value="${i.movie_opening_date}" /></p>
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
		</div>
		<div class="ui horizontal divider">
			<a class="ui teal button" href="javascript:moreList();"> <i
				class="far fa-hand-point-down"></i> &nbsp; 더 보기
				<input type="hidden" value = "1" id ="currentPage"/>
			</a>
		</div>

	</div>

</div>