<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 화면에 보여줘야 하니까 JSTL 태그 사용 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ include file = "../include/header.jsp" %>

	<div class="container text-center">
		<p>
			<h3> recommend equipment</h3>
		</p>	
		
		<table class="table table-hover ">
			<thead>
				<tr >
					<th>no</th>
					<th>writer</th>
					<th>title</th>
					<th>date</th>
				</tr>
			</thead>
			
			<tbody>

			<!-- 회전하면서 객체에서 하나씩 뺴기 -->
			<c:forEach var="relist" items="${list}" varStatus="num">
			<tr>
				<td>${num.count }</td>
				<td>${relist.writer }</td>
				
				<td>
					<a href="re_content.re?rno=${relist.rno}">${relist.title}</a></td>
				<td><fmt:formatDate value="${relist.regdate}" pattern="yyyy-MM-dd"/></td>
			
			</tr>
			</c:forEach>
			
			</tbody>

			
			<tbody>
				<tr>
					<td colspan="6" align="right">
						<form action="re_list.re" class="form-inline" >
						  <div class="form-group">
						    <input type="text" name="searchWord" placeholder="title search" class="form-control" >
						  	<input type="submit" value="검색" class="btn btn-default">
							<input type="button" value="글 작성" class="btn btn-default" onclick="location.href='re_write.re'">
						  </div>
						</form> 
					</td>
				</tr>
			</tbody>
		
		</table>

	</div>

<%@ include file = "../include/footer.jsp" %>
