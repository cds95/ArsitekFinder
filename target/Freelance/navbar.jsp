<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Entities.Job,Entities.Location"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- JQuery UI -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">JobFinder</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="http://localhost:8080/Freelance/">Home</a></li>
					<li><a href="http://localhost:8080/Freelance/jobs">Browse</a></li>
					<c:if test="${user.handle == 'admin'}">
						<li><a href="http://localhost:8080/Freelance/post">Post
								Job</a></li>
					</c:if>
				</ul>
				<c:choose>
					<c:when test="${empty user}">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="http://localhost:8080/Freelance/#drop"><span
									class="glyphicon glyphicon-user"></span> Sign Up</a></li>
							<li><a href="http://localhost:8080/Freelance/login"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#"><span
									class="glyphicon glyphicon-user"></span> <span id="handle">${user.handle}</span></a>
								<span class="caret"></span>
								<ul class="dropdown-menu">
									<li><a
										href="http://localhost:8080/Freelance/profile?handle=${user.handle}">My
											Profile</a></li>
									<li><a href="http://localhost:8080/Freelance/logout">Logout</a></li>
								</ul></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
</body>
</html>