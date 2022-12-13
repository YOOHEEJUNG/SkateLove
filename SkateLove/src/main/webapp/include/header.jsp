<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!doctype html>
<html>
 
	<head>
	  <Style>
  		body{
   		background-image:url('../img/sk13.jpg');
   		background-color:#ffffff;
   		background-size: 2000px 2000px;
  		}
  
 	 </Style>
 	 </head>
  
  
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${pageContext.request.contextPath}index.main">SkateBoard</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/location/location_list.location">Location</a>
      </li>
   
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/re/re_list.re">Recommend</a>
      </li>
        <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/chat/chat_list.chat">Chat</a>
      </li>
        </li>
        <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/club/club_list.club">Club</a>
      </li>
              </li>
        <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/user/user_mypage.user">My Page</a>
      </li>
       </li>
        <li class="nav-item">
        <c:if test= "${sessionScope.user_id != null }">
        <a class="nav-link" href="${pageContext.request.contextPath}/user/user_logout.user">Logout</a>
        </c:if>
      </li>
    </ul>
   
  </div>
</nav>
	
	<div class="jumbotron">
  <h1 class="display-4">Welcome to Skateboard Community</h1>
  <p class="lead">
This is a community that shares the skateboard related information you are looking for in the search bar.</p>
  <hr class="my-4">
  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
	

			<%--로그인창 뜨게 --%>
			<c:if test= "${sessionScope.user_id == null }">
			<a class="btn btn-primary btn-lg" href="../user/user_login.user"
				role="button">Let's Start</a>
			
			
			</c:if>
			

	

	</div>
	
	
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    -->
  </body>
</html>

