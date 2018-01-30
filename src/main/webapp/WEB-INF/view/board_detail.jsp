<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 빵덩어리 -->
<div class="ui container list">
	<div class="ui tiny breadcrumb">
		<a class="section">Home</a> <i class="right chevron icon divider"></i>
		<div class="active section">자유게시판 상세보기</div>
	</div>
</div>

<div class="ui segment">
<!-- 상세보기  -->

	<div class="ui container">
		
		<div class="ui container center aligned">
			
			<div class="circular ui image items">
				<img src="resources/images/travel.jpg" width="300px">
			</div>
				
				<table class="ui fixed celled table" >
					<thead>
						<tr>
						    <th>작성자</th>
						    <td>${dto.board_writer }</td>
						    <th>작성일</th>
						    <td>${dto.board_date }</td>
						</tr>
						<tr>
						    <th>
						       	 제 목
						    </th>
						    <td colspan="3">
						       	${dto.board_name }
						    </td>
						</tr>
						<tr>
						    <th colspan="4">
						    	    내 용
						    </th>
						</tr>
						
					</thead>
					
					<tbody>
						<tr>
						    <td colspan="4">
						        ${dto.board_content }
						    </td>        
						</tr>
					</tbody>
		       </table>
		       
	</div>

	
		<!-- 버튼 리스트 -->
		<div class="ui items">
			<div class="ui animated button" onclick="">
				<div class="visible content">
					<i class="left arrow icon"></i></div>
				<div class="hidden content">
					이전
				</div>
			</div>
			<div class="ui animated button" onclick="">
				<div class="visible content"><i class="right arrow icon"></i></div>
				<div class="hidden content">
					다음
				</div>
			</div>
			<div class="ui right floated vertical animated fade button" onclick="location.href='boardUpdate?num=${dto.board_num}&currentPage=${currentPage }'">
				<div class="visible content"><i class="edit icon"></i></div>
				<div class="hidden content">
					수정
				</div>
			</div>
			<div class="ui right floated vertical animated fade button" onclick="location.href='boardDelete?num=${dto.board_num}&currentPage=${currentPage }'">
				<div class="visible content"><i class="trash icon"></i></div>
				<div class="hidden content">
					삭제
				</div>
			</div>
			<div class="ui right floated sizes animated fade button">
				<div class="visible content">
					<i class="list icon"></i></div>
				<div class="hidden content">
				<c:url value="free" var="pageList">
					<c:param name="currentPage" value="${currentPage }"></c:param>
				</c:url>
				<a href="${pageList }">목록</a>
				</div>
			</div>
		</div>
	</div>
</div>