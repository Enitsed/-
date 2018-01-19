<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
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
			<div class="ui link special cards four columns">
				<c:forEach var="i" items="${movie}" begin="1" end="4">
					<div class="card column blurring dimmable image main_movie">
					<input type="hidden" value="${i.movie_num}"/><!-- 영화 번호 넣을자리 -->
						<img src="resources/images/travel.jpg"><!-- 영화이미지 넣을자리 -->
						
						<div class="ui dimmer">
							<div class="ui content">
								<div class="ui center">
									<div class="ui inverted button" id="movie_modal">더 보기</div>
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<div class="ui divider"></div>
									<br />
									<br />
									<br />
									<div class="ui star rating" data-rating="5" data-max-rating="5"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="ui modal movie">
						<i class="close icon"></i>
						<div class="header">
							영화
						</div>
						<div class="image content">
							<div class="ui medium image">
								<img src="resources/images/travel.jpg">
							</div>
							<div class="description">
								<div class="ui header">영화제목 : 여행 </div>
								<h4>줄거리 : </h4>
								<p>난나라 난나난난나</p>
							</div>
						</div>
						<div class="actions">
							<div class="ui black deny button">
								닫기
							</div>
							<div class="ui positive right labeled icon button">
								상세페이지로 이동
								<i class="checkmark icon"></i>
							</div>
						</div>
					</div>

				</c:forEach>
									
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
		</div>
	</div>
