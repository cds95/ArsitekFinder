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
      <title>Post</title>
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
      <link href="css/post.css" rel="stylesheet">
      <script src="js/post.js"></script>
   </head>
   <body>
   
      <%@ include file = "navbar.jsp" %>
      <c:set var="job" value="${job}"></c:set>
      <div class="container">
      	<c:choose>
      		<c:when test="${job eq null}">
      			<h2>Fill out the form below to post a job</h2>
      		</c:when>
      		<c:otherwise>
      			<h2>Editing Job Number: <span id="jid">${job.jid}</span></h2>
      		</c:otherwise>
      	</c:choose>
         <form>
            <div class="form-group">
               <label>Job Title:</label>
               <c:choose>
               	<c:when test="${job eq null}">
               		<input type="text" class="form-control" id="title">
               	</c:when>
               	<c:otherwise>
               		<input type="text" class="form-control" id="title" value="${job.jobTitle}">
               	</c:otherwise>
               </c:choose>
            </div>
            <div class="form-group">
               <label for="comment">Job Description:</label>
                <c:choose>
               	<c:when test="${job eq null}">
               		<textarea class="form-control" rows="5" id="description" placeholder="Maximum of 1000 characters" ></textarea>
               	</c:when>
               	<c:otherwise>
               		<textarea class="form-control" rows="5" id="description" >${job.description}</textarea>
               	</c:otherwise>
               </c:choose>
               
            </div>
            <div class="form-group row">
               <div class="col-md-4 form-inline">
                  <label>Cost Per Page (IDR):</label> 
                   <c:choose>
               	<c:when test="${job eq null}">
               		 <input type="text" id="price">
               	</c:when>
               	<c:otherwise>
               		 <input type="text" id="price" value=${job.price}>
               	</c:otherwise>
               </c:choose>
               </div>
               <div class="col-md-4 form-inline">
                  <label for="sel1">Type of Job:</label>
                  <select class="form-control" id="type">
                     <option>Drafter (Interior)</option>
                     <option>Drafter (Architecture)</option>
                     <option>Renderer (2D)</option>
                     <option>Renderer (3D)</option>
                     <option>FFE</option>
                  </select>
               </div>
            </div>
            <div class="skills">
            	<label>Skills (Maximum of 5):</label>
            	<select class="form-control" id="skill">
            		<c:forEach items="${tags}" var="tag">
            			<option>${tag.skill}</option>
            		</c:forEach>
                  </select>
            	<a href="#" id="add">Add</a>
            	<div id="error">
            	
            	</div>
            </div>
            <div id="displaySkills"></div>
         </form>
         <div>
         	<c:choose>
         		<c:when test="${job eq null}">
         			<button id="sub">Submit</button>
         		</c:when>
         		<c:otherwise>
         			<button id="update">Update Job</button>
         		</c:otherwise>
         	</c:choose>
         	<button id="update">Update Job</button>
         </div>
      </div>
   </body>
</html>