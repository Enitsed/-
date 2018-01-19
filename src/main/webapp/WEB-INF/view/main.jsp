<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="resources/css/semantic.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/custom.css">
</head>
<body>
	<header>
		<p>
			<tiles:insertAttribute name="header" />
		</p>
	</header>

	<menu>
		<tiles:insertAttribute name="menu" />
	</menu>

	<section>
		<p>
			<tiles:insertAttribute name="body" />
		</p>
	</section>

	<footer>
		<p>
			<tiles:insertAttribute name="footer" />
		</p>
	</footer>

	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.vide.min.js"></script>
	<script src="resources/js/semantic.min.js"></script>
	<script src="resources/js/custom.js"></script>

</body>
</html>