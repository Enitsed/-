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

<script>
	function moreList() {
		var page = ($("#page").val() + 1);
		$
				.ajax({
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
						var page = ($("#page").val() + 1);
						content += '<div class="btns"><a href="javascript:moreList();" class="btn btn-primary">더보기</a><input type="hidden" value="'+ page +'" id="page"/></div>';
						$('#addbtn').remove();//remove btn
						//alert(content);
						$(content).appendTo("#aa");
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
		<div class="ui link special cards four columns">
			<c:forEach var="i" items="${movie}">
				<div class="card column blurring dimmable image main_movie">
					<input type="hidden" value="${i.movie_num}" />
					<!-- 영화 번호 넣을자리 -->
					<c:choose>
						<c:when test="${i.movie_image eq '이미지 없음'}">
							<img src="resources/images/travel.jpg">
						</c:when>
						<c:otherwise>
							<c:forTokens var="item" items="${i.movie_image}" delims="|" end="0">
								<img src="${item}">
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
							<p>
								개봉일 :
								<fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short"
									value="${i.movie_opening_date}" />
							</p>
						</div>
					</div>

					<div class="actions">
						<div class="ui black deny button">닫기</div>
						<div class="ui positive right labeled icon button">
							상세페이지로 이동 <i class="checkmark icon"></i>
						</div>
						<div id="bb"></div>
					</div>
				</div>
			</c:forEach>
			<div class="ui top right attached label green">
				<a href="javascript:moreList();"> <i
					class="far fa-hand-point-down"></i> &nbsp; 더 보기
				</a> <input type="hidden" value="1" id="page" />
			</div>
		</div>
	</div>

	<div class="ui segment">
		<div class="ui items">
			<div class="item">
				<div class="image">
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
				<div class="image">
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
		<div class="ui top right attached label green">
			<i class="far fa-hand-point-down"></i> &nbsp; 더 보기
		</div>
	</div>

	<div class="ui segment">
		<c:forEach items="${list}" var="list">
			<div class="ui items">
				<div class="item">
					<div class="content">
						<a href="${list.originallink}">${list.title}</a>
						<div class="meta">
							<span>Description</span>
						</div>
						<div class="description">
							<p></p>
						</div>
						<div class="extra">${list.description}</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="ui top right attached label green">
			<i class="far fa-hand-point-down"></i> &nbsp; 더 보기
		</div>
	</div>
</div>
