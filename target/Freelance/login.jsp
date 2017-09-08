<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Entities.Job,Entities.Location"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Login</title>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    
    <!-- Non Bootstrap -->
    <link href="css/login.css" rel="stylesheet">
    <link href="css/universal.css" rel="stylesheet">
    <script src="js/login.js"></script>
  </head>
<body>
	<%@ include file = "navbar.jsp" %>
    <div class="jumbotron">
    	<div class="container-fluid">
    		<h2>Login Form</h2>
    		<div id="invalid-container">
    		</div>
    		<form class="form-horizontal signin">
  				<div class="form-group">
				    <label class="control-label col-sm-2">Username:</label>
					<div class="col-sm-4">
	      				<input type="text" class="form-control" id="username" placeholder="Enter username">
	    			</div>
  				</div>
  				<div class="form-group">
    				<label class="control-label col-sm-2" for="pwd">Password:</label>
				    <div class="col-sm-4"> 
				      <input type="password" class="form-control" id="password" placeholder="Enter password">
				      <a href=#">Forgot Password</a>
				    </div>
  				</div>
  				<div class="form-group"> 
    				<div class="col-sm-offset-2 col-sm-10">
						<a href="http://localhost:8080/Freelance/index.jsp#drop">Sign Up</a>
    				</div>
  				</div>
  				<div class="form-group"> 
    				<div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label><input type="checkbox"> Remember me</label>
				      </div>
    				</div>
  				</div>
			</form>
			<div class="enter">
				<button id="log" class="btn btn-default">Submit</button>
    			<div id="wait"></div>
			</div>
    	</div>
    </div>
<body>

</body>
</html>