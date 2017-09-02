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
      <script src="js/profile.js"></script>
   </head>
   <body>
      <c:set var="sessionUserId" value="${user.uid}"></c:set>
      <c:set var="selectUserId" value="${selectedUser.uid}"></c:set>
      <%@ include file = "navbar.jsp" %>
      <div class="userInfo">
      	<c:if test="${sessionUserId eq selectUserId}">
      		<a class="edit" href="http://localhost:8080/Freelance/edit.jsp"><span class="glyphicon glyphicon-pencil"></span></a>
      	</c:if>
         <div class="userInfoTop">
            <div class="basicUserInfo">
               <h3>${selectedUser.first} ${selectedUser.last}</h3>
               <div id="userLocation">
                  <c:choose>
                     <c:when test="${empty selectedUser.location}">
                        <div class="ui-widget">
                           <c:if test="${sessionUserId eq selectUserId}">
                           	  <select name="location" id="loc">
							    <option value="Surabaya, East Java, Indonesia">Surabaya, East Java, Indonesia</option>
							    <option value="Jakarta, DKI, Indonesia">Jakarta, DKI, Indonesia</option>
							    <option value="Denpasar, Bali, Indonesia">Denpasar, Bali, Indonesia</option>
							  </select>	
                              <button type="submit" class="btn btn-default" id="updateLocation">Update</button>
                           </c:if>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <h4>${selectedUser.location.city}, ${selectedUser.location.state}, ${selectedUser.location.country}</h4>
                     </c:otherwise>
                  </c:choose>
               </div>
            </div>
         </div>
         <div class="skills">
            <div class="skills ui-widget">
               <h4>Skills (Maximum of 5 Skills)</h4>
               <c:if test="${sessionUserId eq selectUserId}">
                  <input type="text" name="skill" id="skill" placeholder="Type a skill">
                  <a href="#" id="add">Add</a>
               </c:if>
            </div>
            <div id="displaySkills">
               <c:forEach items="${selectedUser.skills}" var="skill">
                  <div id="${skill.tid}" class="tag">
	                  ${skill.skill}
	                  <span class="glyphicon glyphicon-remove remove"></span>
                  </div>
               </c:forEach>
            </div>
         </div>
      </div>
      <div class="right">
      	<c:if test="${sessionUserId eq selectUserId}">
      		<a class="edit" href="http://localhost:8080/Freelance/edit.jsp"><span class="glyphicon glyphicon-pencil"></span></a>
      	</c:if>
         <h4 id="contact-header">My Contact Information</h4>
         <p class="contactInfo">Email:   <a href="#">${selectedUser.email}</a></p>
         <p class="contactInfo">
         <div id="phoneNumber">
            Phone Number:
            <c:choose>
               <c:when test="${empty selectedUser.phoneNumber}">
                  <c:if test="${sessionUserId eq selectUserId}">
                     <input type="text" id="number" placeholder="Enter Phone Number">
                     <button type="submit" class="btn btn-default" id="updatePhone">Update</button>
                  </c:if>
               </c:when>
               <c:otherwise>
                  <a href="#">${selectedUser.phoneNumber}</a>
               </c:otherwise>
            </c:choose>
         </div>
      	<hr>
      	<div class="education">
      		<h4>Education</h4>
      		<p>
      			${selectedUser.uni.name}<br>
      			Class of ${selectedUser.graduationYear}
      		</p>
      	</div>
      	<hr>
      	<div class="resume">
      		<h4>Resume</h4>
      		<h5><a href="${user.resume}">View Resume</a></h5>
      		<c:if test="${sessionUserId eq selectUserId}">
      			<button id="resume" type="button" class="btn btn-success">Upload Resume</button>
      		</c:if>
      	</div>
      </div>
      <div id="upload-resume">
      	<h3>Upload Resume</h3>
      	<h5>Please upload a copy of your resume below</h5>
      	<form action="UploadResume" enctype="multipart/form-data" method="post">
      		<input type="file" id="fileInput" name="selectedFile" multiple size="50">
      		<input type="submit" class="btn btn-success">
      	</form>
      </div>
   </body>
</html>