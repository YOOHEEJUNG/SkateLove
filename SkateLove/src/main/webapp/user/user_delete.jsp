<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="../include/header.jsp" %>
    
   <!doctype html>
  
  <html>

  
	<head>
	 <style>
	 
/* 	 body{
   		background-image:url('../img/sk2.jpg');
   		background-color:#D9E5FF;
  	} */
	.container {
   		display: flex;
   		height: 100vh;
  		 justify-content: center;
   		align-items: center;
		}

	#wrap {
   		border: 1px solid #777;
  		background-color: #ffffff;
   		padding: 10px;
   		text-align: center;
	}

  		
  </style>
	
</head>
   
 <body>
  	<div class="container">
<section>
	<div align="center">
		<form action="deleteForm.user" method="post">
		
			
			<input type="password" name="pw" placeholder=" 사용자비밀번호" style="text-align: left; width: 400px; height: 50px;"><br>
			<br>
			<input type="submit" value="탈퇴" style="text-align:center; width: 200px; height: 50px;">
		</form>

	</div>
</section>
</div>
 </body>
 
 <%@ include file = "../include/footer.jsp" %>