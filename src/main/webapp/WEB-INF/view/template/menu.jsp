<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 메뉴 -->
<div class="ui attached stackable menu">
	<div class="ui container">
		<a class="item" href="main"> <i class="home icon"></i> Home
		</a> <a class="item"> <i class="grid layout icon"></i> 영화정보
		</a> <a class="item" href="free"> <i class="grid layout icon"></i> 게시판
		</a>
		<div class="ui simple dropdown item">
			더 보기 <i class="dropdown icon"></i>
			<div class="menu">
				<a class="item"><i class="edit icon"></i> 정보 수정</a> <a class="item"><i
					class="settings icon"></i> 환경 설정</a>
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
		<c:if test="${empty sessionScope.kid}">
			<div class="ui item">
				<div class="ui">
					<a href="signup"><button class="ui green basic button">회원
							가입</button></a>
					<button class="ui orange basic button" id="loginBtn">로그인</button>
				
					<c:if test="${not empty sessionScope.kid}">
						<a>${sessionScope.kid}님 환영합니다.</a> &nbsp;&nbsp;&nbsp;
						<button class="ui orange basic button" onclick="ktout()">로그아웃</button>
					</c:if>
				</div>
				
			</div>
		</c:if>

	</div>
</div>

<div class="ui mini modal">
	<div class="header">로그인 화면</div>
	<form class="ui form segment" id="loginForm">
		<div class="input field">
			<label>아이디</label> <input placeholder="아이디 입력" name="loginid" type="text" id="loginid">
		</div>
		<div class="input field">
			<label>비밀번호</label> <input name="loginpassword" type="password" id="loginpassword">
		</div>
		<div class="ui field middle center aligned grid">
			<div class="ui positive tiny button submit">로그인</div>
			<div class="ui black deny tiny button" id="closeBtn">
			<i class="close icon"></i> 닫기</div>
		</div>
		
		<div class="ui error message"></div>
		
	</form>
	<br />
		<div class="ui center aligned middle grid">
			<a id="kakao-login-btn" ></a>
		</div>
	<br />
	
	<div class="ui center aligned grid message">
		처음 인가요? <a href="signup">회원 가입</a>하세요!
	</div>
</div>

