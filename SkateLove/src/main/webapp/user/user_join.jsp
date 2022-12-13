<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file = "../include/header.jsp" %>

<body>
<section>
		<div style="color: black;" font-weight="bold;" ;>
    	<div class="outer"  align="center">
		<br><h3>JOIN</h3><br>
		<form action="joinForm.user" method="post">
			<table>
				<tr>

					<td>
					ID <br>
					<input type="text" name="id" placeholder="user_id" pattern="\w{4,8}"
						style="text-align: left;width: 400px; height: 50px;" required="required" />
					</td>
				</tr>
				<tr>

					<td><br>Password 
 						<br> <input type="password" name="pw"  placeholder="user_password"
 						 style="text-align: left; width: 400px; height: 50px;" required="required"  >
 					</td>	
  						
				
				</tr>
				<tr>
					<td>
					<br> Name
					<br><input type="text" name="name" placeholder="user_name" pattern="[가-힣]{3,}"
			 			style="text-align: left; width: 400px; height:50px;" required="required"/></td>
				</tr>
				<tr>
					<td>
					<br>Age
					<br><input type="number" name="age" placeholder="user_age"
						style="text-align: left; width: 400px; height: 50px;" /></td>
				</tr>
				<tr>
					<td>
					<br>PhoneNumber 
					<br><input type="text" name="phonenumber" placeholder="'-'제외하고 입력" 
					style="text-align: left;width: 400px; height: 50px;"/>
				
					</td>
			
				</tr> 
				<tr>
					<td><br>Gender
					<br><input type="radio" name="gender" value="f" checked="checked" />여자 
					<input type="radio" name="gender" value="m" /> 남자
					</td>
				</tr>

				<tr>
					<td><br>
					<input type="submit" value="가입하기" name="join"
					style="text-align: center; width: 400px; height: 50px;" />
					</td>
				</tr>
			</table>


		</form>
	</div>
	</div>
</section> 


<script>
	//아이디가 중복되면 나오는 알림창
	var msg='${msg}';
	if(msg != ''){
		alert(msg);
	}
	
</script>
</body>
</html>

<%@ include file = "../include/footer.jsp" %>
