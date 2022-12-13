<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<div align="center">
	<h3>실시간 게시글 수정</h3>
	<hr>
	
	<form action="chat_update.chat" method="post">
		<table  border="1" width="500">
			<tr>
				<td>글 번호</td>
				<td>${vo.cno }
					<input type="hidden" name="chno" value="${vo.chno }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${sessionScope.user_id }" readonly></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input type="text" name="title" value="${vo.title }"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea rows="20"  style="width: 95%;" name="content">${vo.content }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정 하기">&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='chat_list.chat'">        
				</td>
			</tr>
		</table>
	</form>
</div>

<%@ include file="../include/footer.jsp" %>