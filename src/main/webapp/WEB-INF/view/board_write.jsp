<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Calendar" %>
<%
	Calendar cal = Calendar.getInstance();
%>
    
	<!-- 빵덩어리 -->
	<div class="ui container list">
		<div class="ui tiny breadcrumb">
			<a class="section">Home</a> <i class="right chevron icon divider"></i>
			<div class="active section">글 쓰기</div>
		</div>
	</div>
    
    <div class="ui container">
    
		<div class="ui form segment">
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
		        </div>
		        
		        <div class="field">
					<label>작성일</label>
		          <input type="text" value="<%=cal.get(Calendar.YEAR) %>-<%=cal.get(Calendar.MONTH)+1 %>-<%=cal.get(Calendar.DATE) %>" disabled>
		        </div>
		        
		        <div class="field">
					<label>추천 수</label>
		          <input type="text" value="0" disabled>
		        </div>
		        
			</div>
			
			<div class="field">
				<label>제목</label>
				<input type="text">
	        </div>
		        
		        
			<div class="field">
				<label>내용</label>
				<textarea></textarea>
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
			<div class="ui submit button">Submit</div>
		</div>
    </div>
