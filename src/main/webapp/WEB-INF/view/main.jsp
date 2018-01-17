<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/semantic.min.css">
</head>
<body>

	<!-- 헤더 -->
	<div class="top">
		<h2 class="ui center aligned icon header">
			<i class="circular users icon"></i>
			<h2 class="ui header center aligned">
				Final Project
				<div class="sub header center aligned">영화 평점 사이트</div>
			</h2>
		</h2>
	</div>
	<div class="ui attached stackable menu">
		<div class="ui container">
			<a class="item"> <i class="home icon"></i> Home
			</a> <a class="item"> <i class="grid layout icon"></i> 영화정보
			</a>
			<div class="ui simple dropdown item">
				더 보기 <i class="dropdown icon"></i>
				<div class="menu">
					<a class="item"><i class="edit icon"></i> 정보 수정</a> <a class="item"><i
						class="settings icon"></i> 환경 설정</a>
				</div>
			</div>
			<div class="right item">
				<div class="ui input">
					<input type="text" placeholder="Search...">
				</div>
			</div>
		</div>
	</div>

	<div class="ui container list">
		<div class="ui tiny breadcrumb">
			<a class="section">Home</a> <i class="right chevron icon divider"></i>
			<div class="active section">메인 페이지</div>
		</div>
	</div>

	<div class="ui container contents">
		<div class="ui link cards three columns">
			<div class="card column">
				<div class="image">
					<img src="resources/images/test.jpg">
				</div>
				<div class="content">
					<div class="header">Matt Giampietro</div>
					<div class="meta">
						<a>Friends</a>
					</div>
					<div class="description">Matthew is an interior designer
						living in New York.</div>
				</div>
				<div class="extra content">
					<span class="right floated"> Joined in 2013 </span> <span> <i
						class="user icon"></i> 75 Friends
					</span>
				</div>
			</div>
			<div class="card column">
				<div class="image">
					<img src="resources/images/test.jpg">
				</div>
				<div class="content">
					<div class="header">Molly</div>
					<div class="meta">
						<span class="date">Coworker</span>
					</div>
					<div class="description">Molly is a personal assistant living
						in Paris.</div>
				</div>
				<div class="extra content">
					<span class="right floated"> Joined in 2011 </span> <span> <i
						class="user icon"></i> 35 Friends
					</span>
				</div>
			</div>
			<div class="card column">
				<div class="image">
					<img src="resources/images/test.jpg">
				</div>
				<div class="content">
					<div class="header">Molly</div>
					<div class="meta">
						<span class="date">Coworker</span>
					</div>
					<div class="description">Molly is a personal assistant living
						in Paris.</div>
				</div>
				<div class="extra content">
					<span class="right floated"> Joined in 2011 </span> <span> <i
						class="user icon"></i> 35 Friends
					</span>
				</div>
			</div>
			<div class="card column">
				<div class="image">
					<img src="resources/images/test.jpg">
				</div>
				<div class="content">
					<div class="header">Elyse</div>
					<div class="meta">
						<a>Coworker</a>
					</div>
					<div class="description">Elyse is a copywriter working in New
						York.</div>
				</div>
				<div class="extra content">
					<span class="right floated"> Joined in 2014 </span> <span> <i
						class="user icon"></i> 151 Friends
					</span>
				</div>
			</div>
		</div>
	</div>


	<div class="ui section divider"></div>

	<!-- 푸터 -->
	<div class="container footer">
		<div class="ui container center aligned">
			<div class="ui three column stackable doubling grid">
				<div class="column">
					<div class="ui header">카테고리</div>
					<div class="ui list">
						<a href="#" class="item">Javascript</a> <a href="#" class="item">PHP</a>
						<a href="#" class="item">Angular</a> <a href="#" class="item">Semantic-UI</a>
					</div>
				</div>
				<div class="column">
					<div class="ui header">Archive</div>
					<div class="ui list">
						<a href="#" class="item">Introduction About System JS</a> <a
							href="#" class="item">Learn About Angular 2 - Part 1</a> <a
							href="#" class="item">Learn About Angular 2 - Part 2</a> <a
							href="#" class="item">How to make Chat App</a> <a href="#"
							class="item">Learn About Angular 2 - Part 3</a>
					</div>
				</div>
				<div class="column">
					<div class="ui header">Help</div>
					<div class="ui list">
						<a href="#" class="item">Term of Service</a> <a href="#"
							class="item">Privacy Policy</a> <a href="#" class="item">FAQ</a>
						<a href="#" class="item">Contact Us</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/semantic.min.js"></script>
</body>
</html>