<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<%@ include file="../include/header.jsp" %>
	
<div class="container">
	<h3>실시간 글 목록</h3>
		<hr>			
		<table class="table table-hover">
  		<thead>
   		 <tr>
   		   <th scope="col">순서</th>
   		   <th scope="col">작성자</th>
   		   <th scope="col">제목</th>
 		   <th scope="col">날짜</th>
  		  </tr>
 		 </thead>
 		 <tbody>
			<c:forEach var="vo" items="${list }" varStatus="num">
 		   <tr class="table-dark">
  		      <td>${num.count }</td>
    		  <td>${vo.writer }</td>
    		  <td><a href="chat_content.chat?chno=${vo.chno }"> ${vo.title }</a></td>
			  <td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
  		  </tr>
  		  	</c:forEach>
  		 <tbody>
					<tr>
					<td colspan="6" align="right">
					
						<form action="chat_list.chat" class="form-inline" >		
						    <div class="input-group mb-3" >
    					  <input type="text" name="search" class="form-control" placeholder="title" aria-label="Recipient's username" aria-describedby="button-addon2">
    					  <button class="btn btn-primary" type="submit" id="button-addon2"  >Search</button>
    					  <button class="btn btn-primary" type="button" id="button-addon2" onclick="location.href='chat_write.chat'">Write</button>
   						 </div>   
						</form>  
					</td>
				</tr>
			</tbody>
			  
		</table>
		
		
		
		
		
		
		
</div>
	
	
	
	
<%@ include file="../include/footer.jsp" %>