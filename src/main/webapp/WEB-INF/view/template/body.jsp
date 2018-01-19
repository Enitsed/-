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
				<div class="card column blurring dimmable image">
				<input type="hidden" value="${i.movie_num}"/><!-- 영화 번호 넣을자리 -->
					<img src="resources/images/travel.jpg"><!-- 영화이미지 넣을자리 -->
					<div class="ui dimmer">
						<div class="content">
							<div class="center">
								<div class="ui inverted button">CLICK</div>
							</div>
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
