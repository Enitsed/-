<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<link rel="stylesheet" type="text/css"
	href="resources/css/semantic.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/custom.css">
</head>
<body>
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="menu" />

	<tiles:insertAttribute name="body" />

	<tiles:insertAttribute name="footer" />

	<script src="http://developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.vide.min.js"></script>
	<script src="resources/js/semantic.min.js"></script>
	<script src="resources/js/custom.js"></script>

</body>
</html>