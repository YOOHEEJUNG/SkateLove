<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>	
  	<%
    	 Cookie[] remember_id = request.getCookies();
        
        String user_id ="";
       	 if(remember_id != null){
        	for(Cookie c : remember_id){   			       		
        		if(c.getName().equals("re_id")){
        			user_id  = c.getValue();
        		}
        	}
        }
  
    %>

	

	<section>
	
		<div align="center">
			<h3>LOGIN</h3><br>
			<form action="loginForm.user" method ="post">
				
				<input type="text" name="id" placeholder="아이디를 입력하세요" value="<%= user_id%>"
					style="text-align: left; width: 300px; height: 40px;" size=10 /><br>
				
				<br>
					<div class="main">
  						
 						 <input type="password" name="pw" class="form-input" placeholder="비밀번호를 입력해주세요."
 						 style="text-align: left; width: 300px; height: 40px;" size=10 >
 						 <div class="eyes">
  						<i class="fas fa-eye"></i>
 						 </div>
  						
				</div>
				<br>
				<input type="checkbox" name="autoLogin" value="yes" /> 아이디 저장<br>
				
				<br> <input type="submit" value="로그인" class="btn btn-primary"
					style="width: 300px; height: 40px;" size=10 /><br>
			
				 <br><input type="button" name="join" value="회원가입" class="btn btn-secondary" style="width: 300px; height: 40px;" size=10 
				 onclick = "location.href='user_join.user'"/>



			</form>
		</div>
	</section>

<script>
	
	var msg='${fail}';
	if(msg != ''){
		alert(msg);
	}
	
</script> 

<%@ include file = "../include/footer.jsp" %>