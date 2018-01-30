<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 빵덩어리 -->
	<div class="ui container list">
		<div class="ui tiny breadcrumb">
			<a class="section">Home</a> <i class="right chevron icon divider"></i>
			<div class="active section">글 수정</div>
		</div>
	</div>
    
   <div class="ui container">
   
	<form class="ui form segment" action="boardUpdate" method="post">
		<div class="four fields">
			<div class="field">
				<label>목록 선택</label>
				<div class="ui selection dropdown">
					<div class="default text">Select</div>
					<i class="dropdown icon"></i>
					<input name="hidden-field" type="hidden">
					<div class="menu">
						<div class="item" data-value="1">자유게시판</div>
						<div class="item" data-value="2">건의게시판</div>
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
				<input type="text" name="board_name" value="${dto.board_name }">
	        </div>
		        
			<div class="field">
				<label>내용</label>
				<textarea name="board_content">${dto.board_content }</textarea>
			</div>
			
			<div class="four fields">
				<div class="field">
					<label>글 비밀번호</label>
					<div class="ui left icon input">
						<i class="lock icon"></i>
						<input type="password">
					</div>
				</div>
			</div>
			<input type="hidden" value="${currentPage }" name="currentPage">
			<input type="hidden" value="${dto.board_num }" name="board_num">
			<button class="ui button" type="submit">글 올리기</button>
		</form>
    </div>