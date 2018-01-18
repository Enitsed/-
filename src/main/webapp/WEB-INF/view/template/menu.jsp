<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 메뉴 -->
	<div class="ui attached stackable menu">
		<div class="ui container">
			<a class="item" href="main">
				<i class="home icon"></i> Home
			</a>
			<a class="item">
				<i class="grid layout icon"></i> 영화정보
			</a>
			<a class="item" href="free">
				<i class="grid layout icon"></i> 게시판
			</a>
			<div class="ui simple dropdown item">
				더 보기
				<i class="dropdown icon"></i>
				<div class="menu">
					<a class="item"><i class="edit icon"></i> 정보 수정</a>
					<a class="item"><i class="settings icon"></i> 환경 설정</a>
				</div>
			</div>
			<div class="ui item right">
				<div class="ui action input">
					<input type="text" placeholder="Search...">
					<button class="ui icon button">
						<i class="search icon"></i>
				  	</button>
				</div>
			</div>
			<div class="ui item">
				<div class="ui">
					<a href="signup"><button class="ui green basic button">회원 가입</button></a>
					<button class="ui orange basic button">로그인</button>
				</div>
			</div>
		</div>
	</div>