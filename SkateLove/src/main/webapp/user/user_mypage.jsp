<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file = "../include/header.jsp" %>

<section>
	<div align="center">
		<div id="pagetitle">
			<h3>MY INFO</h3>
	  		${sessionScope.user_id }님, 환영합니다!
	  	<div>
	  	<c:if test="${sessionScope.user_id!=null}">
	  		
	  		<a href="user_logout.user">[로그아웃]</a>
	  	</c:if>
	  	<c:if test="${sessionScope.user_id==null}">
	  		
	  		<a href="user_logout.user">[로그인]</a>
	  	</c:if>	
	  		<a href="user_modify.user">[정보수정]</a>
	  		<a href="user_delete.user">[회원탈퇴]</a>
	
	  	
	  	
	  	</div>
	  		
		</div>

		<style>
		table {
			width: 25%;
			border-top: 1px solid #444444;
			border-collapse: collapse;
			background-color:white;
         background-color: rgba( 255, 255, 255, 0.7 );
		}

		th, td {
			border-bottom: 1px solid #444444;
			
			padding: 10px;
		}
		
		
		

	</style>

	<div>
		<table>
		<br>
		<tr>
		
			<td>아이디</td><td> ${sessionScope.user_id }</td>
		</tr>
		<tr>
		
			<td>이름</td><td>${sessionScope.user_name }</td>
		</tr>
		<tr>
		
		<td>나이	</td><td>${sessionScope.user_age }</td>
		</tr>
		<tr>
		
			<td>핸드폰	</td><td>${sessionScope.user_phoneno }</td>
		</tr>
		<tr>
		
			<td>성별	</td><td>${sessionScope.user_gender }</td>
		</tr>
		</table>



	</div>


</section>

<%@ include file = "../include/footer.jsp" %>