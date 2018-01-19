<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


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
							가입</button></a> <a href="login"><button class="ui orange basic button">로그인</button></a>
				</div>
			</div>
		</c:if>

		<c:if test="${not empty sessionScope.kid}">
			<a>${sessionScope.kid}님 환영합니다.</a> &nbsp;&nbsp;&nbsp;
				<button class="ui orange basic button" onclick="ktout()">로그아웃</button>
		</c:if>
	</div>
</div>

<script type="text/javascript">
	function ktout() {
		Kakao.init('331f6e91bdb4a956167313811ffb0d23');
		Kakao.Auth.logout(function() {
			setTimeout(function() {
				location.href = "logout";
				persistAccessToken: false;
				alert('로그아웃 되엇습니다');
			}, 1000);//로그아웃 처리되는 타임을 임시적으로 1000설정
		});
	}
</script>
<div class="ui mini modal">
	<i class="close icon"></i>
	<div class="header">로그인 화면</div>
	<form class="ui form segment" id="loginForm">
		<div class="input field">
			<label>아이디</label> <input placeholder="아이디 입력" name="id" type="text"
				id="id">
		</div>
		<div class="input field">
			<label>비밀번호</label> <input name="password" type="password"
				id="password">
		</div>
		<div class="ui field middle center aligned grid">
			<div class="ui positive tiny button submit">로그인</div>
			<div class="ui black deny tiny button">취소</div>
		</div>
		<div class="ui error message"></div>
	</form>
	<div class="ui center aligned grid message">
		처음 인가요? <a href="#">회원 가입</a>하세요!
	</div>
</div>
