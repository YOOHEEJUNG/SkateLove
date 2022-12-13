<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>     

<%@ include file = "../include/header.jsp" %>

<div align="center" class="div_center">

	<h3>게시글 내용 보기</h3>
	<hr>
	<table border="1" width="500">
<!--			
		<tr>
			<td width="20%" align="center">글번호</td>
			<td width="30%" style="padding-left:10px">${content.lno }</td>
			<td width="20%">---</td>
			<td width="30%"></td>
		</tr>
  -->
  
  
  <style>
        table {
         border-top: 1px solid #444444;
         border-collapse: collapse;
         background-color:white;
         background-color: rgba( 255, 255, 255, 0.7 );
      }
      </style>
  
		<tr>
			<td width="20%" align="center" readonly required>${sessionScope.user_id }</td>
			<td width="30%" style="padding-left:10px">${content.writer }</td>
			
			<td width="20%" align="center">작성일</td>
			<td width="30%" style="padding-left:10px"><fmt:formatDate value="${content.regdate }" pattern="yyyy-MM-dd HH:mm"/></td></td>
		</tr>
		
		<tr>
			<td width="20%" align="center">글제목</td>
			<td colspan="3" style="padding-left:10px" >${content.title }</td>
		</tr>
		<tr>
			<td width="20%" align="center">글내용</td>
			<td colspan="3" height="250px" style="padding-left:10px">${content.content }</td>
		</tr>
		
		<tr>
			<td colspan="4" align="center">
				<input type="button" value="목록" onclick="location.href='location_list.location' ">&nbsp;&nbsp;
				
				<!-- 로그인 되어 있어야 글 수정시 수정, 삭제 버튼이 보임, 이미 로그인 완 생각안해도 되지? -->
				
				<input type="button" value="수정" onclick="location.href='location_modify.location?lno=${content.lno}&witer=${content.writer }'">&nbsp;&nbsp;
				<input type="button" value="삭제" onclick="location.href='location_delete.location?lno=${content.lno}&witer=${content.writer }'">&nbsp;&nbsp;
				
				
			</td>
		</tr>
	</table>
	

</div>







<%@ include file = "../include/footer.jsp" %>