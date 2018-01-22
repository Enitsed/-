<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 빵덩어리 -->
<div class="ui container list">
	<div class="ui tiny breadcrumb">
		<a class="section">Home</a> <i class="right chevron icon divider"></i>
		<div class="active section">자유게시판 상세보기</div>
	</div>
</div>

<div class="ui segment">
<!-- 상세보기  -->

	<div class="ui container">
		
		<div class="ui main text container center aligned">
			
			<h1 class="ui header">글 제목 </h1>
			<h3 class="ui header">작성자 : 현우 </h3>
			<h3 class="ui header">작성일 : 2018-01-22</h3>
			
			<div class="circular ui image items">
				<img src="resources/images/travel.jpg" width="300px">
			</div>
			
			<p>This is a basic fixed menu template using fixed size containers.</p>
			<p>A text container is used for the main container, which is useful for single column layouts</p>
			
		</div>
	
		<!-- 버튼 리스트 -->
		<div class="ui animated button">
			<div class="visible content">
				<i class="left arrow icon"></i></div>
			<div class="hidden content">
				이전
			</div>
		</div>
		<div class="ui animated button">
			<div class="visible content"><i class="right arrow icon"></i></div>
			<div class="hidden content">
				다음
			</div>
		</div>
		<div class="ui right floated vertical animated fade button">
			<div class="visible content"><i class="pencil icon"></i></div>
			<div class="hidden content">
				글쓰기
			</div>
		</div>
		<div class="ui right floated sizes animated fade button">
			<div class="visible content">
				&nbsp;&nbsp;&nbsp;&nbsp;<i class="list icon"></i>&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="hidden content">
			목록 보기
			</div>
		</div>
	</div>
</div>