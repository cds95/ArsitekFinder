<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="Entities.Job,Entities.Location"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>Contact</title>
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
      
      <!-- Non Bootstrap -->
      <link href="css/universal.css" rel="stylesheet">
      <link href="css/contact.css" rel="stylesheet">
      <script src="js/contact.js"></script>
   </head>
<body>
	 <%@ include file = "navbar.jsp" %>
	 <h1>Contact Us</h1>
	<form>
		<div class="form-group">
			<label>Name</label>
			<input type="text" class="form-control" id="name">
		</div>
		<div class="form-group">
			<label>Email</label>
			<input type="text" class="form-control" id="email">
		</div>
		<div class="form-group">
			<label>Subject</label>
			<input type="text" class="form-control" id="subject">
		</div>
		<div class="form-group">
			<label>Message</label>
			<textarea class="form-control" id="message"></textarea>
		</div>
		<btn class="btn btn-success">Submit</btn>
	</form>
	
	<div id="result">
		<h3>Email Successfully Sent!</h3>
		<p>Thank you for your business.  A member of our team will get into contact with you within 2-3 business days</p>
	</div>
</body>
</html>