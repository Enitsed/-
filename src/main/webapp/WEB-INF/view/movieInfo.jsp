<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:forEach items="${info}" var="info">
		<a>영화제목 : ${info.movie_kor_title }</a>
		<br />
		<a>개봉일 : <fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short" value="${info.movie_opening_date }" /></a>
	</c:forEach>
	
	<c:if test="${comment!=null}">
		<c:forEach items="${comment}" var="cmt">
			<li class = "time_sub" id = "${cmt.comment_num}">
	      		<p>${cmt.mem_id}</p>
	      		<p>${cmt.replytext}</p>
	      		<p><fmt:formatDate pattern="yyyy/MM/dd" dateStyle="short" value="${cmt.regdate}" /></p>
	      		<p>
					<button id = "${cmt.comment_num}">delete</button>
					<button id = "${cmt.comment_num}">update</button>
				</p>
	      	</li>
		</c:forEach>
	</c:if>
		
