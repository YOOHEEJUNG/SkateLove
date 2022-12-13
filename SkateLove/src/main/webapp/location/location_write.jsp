<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>


	<div align="center" class="div_center">
		<h3> 장소 추천 글쓰기 </h3>
	
	<hr>
	<form action="registForm.location" method="post">
	
		<table border="1" width="500" >
			<tr>
				<td align="center">작성자</td>
				<td>
					<input type="text" name="writer" size="10" value="${sessionScope.user_id }" required readonly >
				</td>
			</tr>
			<tr>
				<td align="center">글 제목</td>
				<td>
					<input type="text" name="title" required>
				</td>
			</tr>
			<tr>
				<td align="center">글 내용</td>
				<td>
					<textarea rows="10" style="width: 90%; vertical-align: middle;" name="content" required ></textarea>
				</td>
			</tr>
			
			<!-- 
			<<tr>
				<td>지도 올리기</td>
				<td>
					<input type="file" name="????">
				</td>
			</tr>
			 -->
			 
			<tr>
				<td colspan="8" align="center">
					<input type="submit" value="작성 완료" >
					&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href= 'location_list.location' ">         
				</td>
			</tr>
			
		</table>
	</form>

	
	</div>


<%@ include file = "../include/footer.jsp" %>