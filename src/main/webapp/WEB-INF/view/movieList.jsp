<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
						alert(content);
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

<!-- 바디 -->
<div class="ui container contents">
	<div class="ui segment">
		<div class="ui link special cards four columns" id = "aa">
			<c:forEach var="i" items="${movie}" begin="1" end="4">
				<div class="card column blurring dimmable image main_movie">
					<input type="hidden" value="${i.movie_num}" />
					<!-- 영화 번호 넣을자리 -->
					<img src="resources/images/travel.jpg">
					<!-- 영화이미지 넣을자리 -->

					<div class="ui dimmer">
						<div class="ui content">
							<div class="ui center">
								<div class="ui inverted button" id="movie_modal">더 보기</div>
								<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
								<br /> <br />
								<div class="ui divider"></div>
								<br /> <br /> <br />
								<div class="ui star rating" data-rating="5" data-max-rating="5"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="ui modal movie">
					<i class="close icon"></i>
					<div class="header">영화</div>
					<div class="image content">
						<div class="ui medium image">
							<img src="resources/images/travel.jpg">
						</div>
						<div class="description">
							<div class="ui header">영화제목 : 여행</div>
							<h4>줄거리 :</h4>
							<p>난나라 난나난난나</p>
						</div>
					</div>
					<div class="actions">
						<div class="ui black deny button">닫기</div>
						<div class="ui positive right labeled icon button">
							상세페이지로 이동 <i class="checkmark icon"></i>
						</div>
					</div>
				</div>

			</c:forEach>
			<div class="addbtn" id="addbtn">
				<a href="javascript:moreList();" class="btn btn-primary">더보기</a> <input
					type="hidden" value="1" id="page" />
			</div>
		</div>
	</div>
</div>