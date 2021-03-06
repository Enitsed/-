<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <div class="ui container">
   
	<form class="ui form segment boardWrite" action="boardWrite" method="post">
		<div class="four fields">
			<div class="field">
				<label>목록 선택</label>
				<div class="ui selection dropdown">
					<div class="default text">Select</div>
					<i class="dropdown icon"></i>
					<input name="board_category" id="board_category" type="hidden">
					<div class="menu">
						<c:if test="${userDTO.mem_id eq 'admin' }">
							<div class="item" data-value="1">공지사항</div>
						</c:if>
						<div class="item" data-value="2">건의게시판</div>
						<div class="item" data-value="3">질문과답변</div>
						<div class="item" data-value="4">자유게시판</div>
					</div>
				</div>
			</div>
			
	        <div class="field">
				<label>작성자</label>
				<input type="text" value="${userDTO.mem_name}" disabled>
				<input type="hidden" name="board_writer" value="${userDTO.mem_name}">
				<input type="hidden" name="mem_num" value="${userDTO.mem_num}">
	        </div>
	        
	        <div class="field">
				<label>작성자 이메일</label>
				<input type="text" value="${userDTO.mem_email}" disabled>
	        </div>
		        
	        <div class="field">
				<label>추천 수</label>
				<input type="text" value="0" disabled>
	        </div>
		        
		</div>
			
			<div class="field">
				<label>제목</label>
				<input type="text" name="board_name" id="board_name">
	        </div>
		        
			<div class="field">
				<label>내용</label>
				<textarea name="board_content" id="board_content"></textarea>
			</div>
			
			<button class="ui button" type="submit">글 올리기</button>
			<div class="ui error message"></div>
		</form>
    </div>
