<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 빵덩어리 -->
	<div class="ui container list">
		<div class="ui tiny breadcrumb">
			<a class="section">Home</a>
			<i class="right chevron icon divider"></i>
			<div class="active section">자유게시판</div>
		</div>
	</div>
<!-- 보드 테이블 -->

	<div class="ui container">
	
		<div class="ui clearing segment">
		
			<table class="ui celled padded table">
				<thead>
					<tr>
						<th class="center aligned">작성일</th>
						<th class="center aligned">제목</th>
						<th class="center aligned">추천 수</th>
						<th class="center aligned">작성자</th>
						<th class="center aligned" style="width: 650px">내용</th>
						<th class="center aligned">조회 수</th>
	
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<h2 class="ui center aligned header">A</h2>
						</td>
						<td class="center aligned">
							<a href="boardDetail">
								Power Output
							</a>
						</td>
						<td>
							<div class="ui star rating" data-rating="3" data-max-rating="5"></div>
						</td>
						<td class="center aligned">
							<a href="#">현우</a>
						</td>
						<td class="center aligned">
							<div class="description tdcontent">블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라</div>
						</td>
						<td class="center aligned">
							80
						</td>
					</tr>
				</tbody>
			</table>
			
				<!-- 버튼 리스트 -->
				<div class="ui animated button">
					<div class="visible content">이전</div>
					<div class="hidden content">
						<i class="left arrow icon"></i>
					</div>
				</div>
				<div class="ui animated button">
					<div class="visible content">다음</div>
					<div class="hidden content">
						<i class="right arrow icon"></i>
					</div>
				</div>
				<div class="ui right floated vertical animated fade button" id="writeBtn">
					<div class="hidden content">
						<i class="pencil icon"></i>
					</div>
					<div class="visible content">
						글쓰기
					</div>
				</div>
				<div class="ui right floated sizes animated fade button">
					<div class="visible content">글 목록 보기</div>
					<div class="hidden content">
						<i class="content icon"></i>
					</div>
				</div>
				
		</div>
		
	</div>