<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<form method="post" class="myPage" action="updateInfo">
	<p><label>아이디</label><input type="text" name="mem_id" value="${userDTO.mem_id}"></p>
	<p><label>비밀번호</label><input type="text" name="mem_pw" value="${userDTO.mem_pw}"></p>
	<p><label>성별</label><input type="text" value="${userDTO.mem_sex}" disabled></p>
	<p><label>이름</label><input type="text" name="mem_name" value="${userDTO.mem_name}"></p>
	<p><label>이메일</label><input type="text" name="mem_email" value="${userDTO.mem_email}"></p>
	<p><label>주소</label><input type="text" name="mem_address" value="${userDTO.mem_address}"></p>
	<input type="submit" id="updateBtn" value="수정">
</form>
</body>
</html>