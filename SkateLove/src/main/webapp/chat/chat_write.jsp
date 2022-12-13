<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<div align="center" class="div_center">
	<h3>실시간 글 작성 페이지</h3>
	<hr>
	<form action="registForm.chat" method="post">
		<table border="1" width="500">
			<tr>
				<td>글쓴이</td>
				<td>
					<input type="text" name="writer" value="${sessionScope.user_id }" required readonly> 
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title"> 
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea  rows="20" style="width: 95%;"  name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>작성완료</td>
				<td>
					<input type="submit" value="작성완료" > 
				</td>
			</tr>
			<tr>
				<td>목록</td>
				<td>
					<input type="button" value="목록" onclick="location.href='chat_list.chat'"> 
				</td>
			</tr>
		</table>
	</form>

</div>




<%@ include file="../include/footer.jsp" %>