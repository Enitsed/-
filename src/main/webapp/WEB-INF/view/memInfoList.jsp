<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<table border="1">
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>등급</th>
	</tr>
	<c:forEach var="list" items="${memList}">
	<tr>
		<td>${list.mem_num}</td>
		<td>${list.mem_id}</td>
		<td>${list.mem_name}</td>
		<td>${list.mem_grade}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>