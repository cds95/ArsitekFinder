<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Home</title>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="js/bootstrap.min.js"></script>
      <!-- Bootstrap -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <!-- JQuery UI Widget -->
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
      <link href="universal.css" rel="stylesheet">
   </head>
<body>
	<nav class="navbar navbar-inverse">
         <div class="container-fluid">
            <div class="navbar-header">
               <a class="navbar-brand" href="#">JobFinder</a>
            </div>
            <ul class="nav navbar-nav">
               <li class="active"><a href="http://localhost:8080/Freelance/index.jsp">Home</a></li>
               <li> <a href="http://localhost:8080/Freelance/FinderController">Browse</a></li>
               <li> <a href="http://localhost:8080/Freelance/post.jsp">Post Job</a></li>
            </ul>
            <c:choose>
               <c:when test="${empty user}">
                  <ul class="nav navbar-nav navbar-right">
                     <li><a href="http://localhost:8080/Freelance/index.jsp#drop"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                     <li><a href="http://localhost:8080/Freelance/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                  </ul>
               </c:when>
               <c:otherwise>
                  <ul class="nav navbar-nav navbar-right">
                     <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> ${user.handle}</a>
                        <span class="caret"></span>
                        <ul class="dropdown-menu">
                           <li><a href="http://localhost:8080/Freelance/ProfilLoaderController?userId=${user.uid}">My Profile</a></li>
                           <li><a href="http://localhost:8080/Freelance/LogoutController">Logout</a></li>
                        </ul>
                     </li>
                  </ul>
               </c:otherwise>
            </c:choose>
         </div>
      </nav>
</body>
</html>