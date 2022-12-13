<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
    
<%@ include file="../include/header.jsp" %>

<div>
	<h3>동호회 글 목록</h3>
		<hr>
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>순서</th>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>		
			</tr>
			</thead>
			
			<tbody>
				<c:forEach var="vo" items="${list }" varStatus="num">
				
				<tr>
					<td>${num.count }</td>
					<td>${vo.cno }</td>
					<td>${sessionScope.user_id }</td>
					<td>
					<a href="club_content.club?cno=${vo.cno }"> ${vo.title }</a>
					</td>
					<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
				</tr>
				</c:forEach>
			</tbody>
			<tbody>
				<tr>
					<td colspan="6" align="right">
					
						<form action="club_search.club" class="form-inline" >
						  <div class="form-group">
						    <input type="text" name="search" placeholder="제목검색" class="form-control" >
						  	<input type="submit" value="검색" class="btn btn-default">
							<input type="button" value="글 작성" class="btn btn-default" onclick="location.href='/club/club_write.club'">
						  </div>
						</form> 
					</td>
				</tr>
			</tbody>
		</table>
</div>

<%@ include file="../include/footer.jsp" %>