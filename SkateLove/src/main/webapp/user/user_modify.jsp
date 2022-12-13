<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>
<section>
	<div align="center">
	<br>
	<h3>Update Info</h3>
		<form action="updateForm.user" method="post">
			<table>
				<tr>

					 <td>ID
					
					<br><input type="text" name="id" value="${vo.id}" pattern="\w{4,8}"
						style="text-align: left; width: 400px; height: 40px;" required readonly />
						<%--아이디는 수정 불가 오직 읽기만 가능 --%>
					</td>
				</tr>
				<tr>

					 <td><br>Password
					 
					<br>
					 <input type="text" name="pw" value="${vo.pw}" pattern="\w{4,8}"
						style="text-align: left; width: 400px; height: 40px;" /></td>
				</tr>
				
				<tr>
				
				 <td><br>Name
					
					<br>
					<input type="text" name="name" value="${vo.name}" pattern="[가-힣]{3,}"
						style="text-align: left; width: 400px; height: 40px;"  /></td>
				</tr>
				
				<tr>
				 <td><br>Age
					
					<br>
					<input type="number" name="age" value="${vo.age}"
						style="text-align: left; width: 400px; height: 40px;" /></td>
				</tr> 
				
				<tr>
				 <td><br>PhoneNumber
					
					<br>
					
					<input type="text" name="phonenumber" value="${vo.phoneno}" 
					style="text-align: left; width: 400px; height: 40px;"/>
				
					</td>
			
				</tr>
				 
				<tr>
				 <td><br>Gender
					
					<br>
					
					<input type="radio" name="gender" value="f" ${vo.gender =='f' ? 'checked' : ''}/>여자 
					<input type="radio" name="gender" value="m" ${vo.gender =='m' ? 'checked' : ''}/> 남자
					<br></td>
				</tr>
				<tr>
					<td align="center">
				<br>
					<input type="submit" value="회원정보수정" name="update"
					style="text-align: center; width: 200px; height: 40px;" />
					</td>
				</tr>
			</table>


		</form>
	</div>
</section> 



<%@ include file = "../include/footer.jsp" %>
