<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="Entities.Job,Entities.Location"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
      <title>${user.first} ${user.last}</title>
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="js/bootstrap.min.js"></script>
      <!-- Bootstrap -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      
      <!-- JQuery UI -->
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
      
      <!-- Non Bootstrap -->
      <link href="css/universal.css" rel="stylesheet">
      <link href="css/prof.css" rel="stylesheet">
      <script src="js/edit.js"></script>
      <script src="js/profile.js"></script>
   </head>
   <body>
      <c:set var="sessionUserId" value="${user.uid}"></c:set>
      <c:set var="selectUserId" value="${selectedUser.uid}"></c:set>
      <%@ include file = "navbar.jsp" %>
      <h2>Edit Your Personal Information</h2>
      <div id="basic-information">
      	<form>
      			<div class="form-group">
	      			<label>First Name:</label>
	   				<input type="text" class="form-control" id="first" value="${user.first}">
      			</div>
      		 	<div class="form-group">
				    <label>Last Name:</label>
				    <input type="text" class="form-control" id="last" value="${user.last}">
			 	</div>
			 	<div class="form-group">
				    <label>Email Address:</label>
				    <input type="email" class="form-control" id="email" value="${user.email}">
			 	</div>
			 	<div class="form-group">
				    <label>Phone Number</label>
				    <input type="text" class="form-control" id="phone" value="${user.phoneNumber}">
			 	</div>
			 	<div class="form-group">
			 		<label>Location:</label>
			 		<select id="location">
			 			<c:forEach items="${manager.getLocations()}" var="loc" >
				 			<c:choose>
				 				<c:when test="${user.location eq loc }">
				 					<option value="${loc.cid}" selected>${loc.city}, ${loc.state}, ${loc.country}</option>
				 				</c:when>
				 				<c:otherwise>
				 					<option value="${loc.cid}">${loc.city}, ${loc.state}, ${loc.country}</option>
				 				</c:otherwise>
				 			</c:choose>
			 			</c:forEach>	
			 		</select>
			 	</div>
			 	<div class="row">
			 		<div class="col-md-5 form-inline">
			 			<label>University: </label>
				 		<select id="university">
				 			<c:forEach items="${manager.getUniversities()}" var="uni" >
					 			<c:choose>
					 				<c:when test="${user.uni eq uni }">
					 					<option value="${uni.eid}" selected>${uni.name}</option>
					 				</c:when>
					 				<c:otherwise>
					 					<option value="${uni.eid}">${uni.name}</option>
					 				</c:otherwise>
					 			</c:choose>
				 			</c:forEach>
						</select>	
			 		</div>"
			 		<div class="col-md-5 form-inline">
			 			<label>Graduation Year: </label>
				 		<input type="text" value="${user.graduationYear}" id="graduation">
				 		<div id="err"></div>
			 		</div>	 		 	
			 	</div>
      	</form>
      	<div class="submit">
			<button id="save" class="btn btn-success">Save</button>
		</div>
		<hr>
		<h2>Change Your Password</h2>
		<form>
			<div class="form-group">
				<label>Old Password</label>
				<input type="password" class="form-control" id="old-pass">
			</div>
			<div class="form-group">
				<label>Confirm Old Password</label>
				<input type="password" class="form-control" id="confirm-pass">
			</div>
			<div class="form-group">
				<label>New Password</label>
				<input type="password" class="form-control" id="new-pass">
			</div>
		</form>
		<div id="display-err"></div>
		<button id="save-pass" class="btn btn-success">Save</button>
      </div>
   </body>
</html>