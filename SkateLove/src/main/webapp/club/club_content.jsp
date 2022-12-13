<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<%@ include file="../include/header.jsp" %>

<div align="center" class="div_center">

	<h3>동호회 게시물 내용 보기</h3>
	<hr>
	<table border="1" width="500">
		<tr>
			<td width="20%">글번호</td>
			<td width="30%">${vo.cno }</td>
			
			
			
			
		
		</tr>
	<style>
        table {
         border-top: 1px solid #444444;
         border-collapse: collapse;
         background-color:white;
         background-color: rgba( 255, 255, 255, 0.7 );
      }
      </style>		
		<tr>
			<td>작성자</td>
			<td readonly required>${sessionScope.user_id }</td>
			
			<td>작성일</td>
			<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
		</tr>
		
		<tr>
			<td width="20%">글제목</td>
			<td colspan="3">${vo.title }</td>
		</tr>
		<tr>
			<td width="20%">글내용</td>
			<td colspan="3" height="120px">${vo.content }</td>
		</tr>
		
		<tr>
			<td colspan="4" align="center">
				<input type="button" value="목록" onclick="location.href='club_list.club'">&nbsp;&nbsp;
				<input type="button" value="수정" onclick="location.href='club_modify.club?cno=${vo.cno}&writer=${vo.writer }'">&nbsp;&nbsp;
				<input type="button" value="삭제" onclick="location.href='club_delete.club?cno=${vo.cno}&writer=${vo.writer }'">&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	

</div>

<%@ include file="../include/footer.jsp" %>