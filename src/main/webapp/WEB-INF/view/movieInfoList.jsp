<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	function moreList() {
		var page = (parseInt($("#currentPage").val()) + 1);
		alert(page);
		$.ajax({
			url : 'addMovie.do?page=' + page,
			type : 'GET',
			dataType : 'json',
			success : function(data) {
						//console.log(data);
				var content = "";
						
				for (var i = 0; i < data.length; i++) {
					content += '<div class="card column blurring dimmable image">'
							+ '<input type="hidden" value="${data.movie_num}" />'
							+ '<img src="resources/images/travel.jpg">'
							+ '<div class="ui dimmer">'
							+ '<div class="content">'
							+ '<div class="center">'
							+ '<div class="ui inverted button">CLICK</div>'
							+ '</div>' + '</div>' + '</div>' + '</div>'
				}
						$(content).appendTo("#movieListWindow");
						
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

<!-- 빵덩어리 -->
<div class="ui container list">
	<div class="ui tiny breadcrumb">
		<a class="section">Home</a>
		<i class="right chevron icon divider"></i>
		<div class="active section">영화정보</div>
	</div>
</div>

<div class="ui container contents">

	<div class="ui segment">

		<div class="ui link special cards four columns">
			<c:forEach var="i" items="${movie}">
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
		</div>
		<div class="ui horizontal divider">
			<a class="ui teal button" href="javascript:moreList();" >
				<i class="far fa-hand-point-down"></i> &nbsp; 더 보기
			</a>
		</div>
		
	</div>
	
</div>