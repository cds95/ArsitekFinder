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
      <title>Home</title>
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
      <link href="css/job.css" rel="stylesheet">
      <script src="js/job.js"></script>
   </head>
   <body>
      <c:set var="userJob" value="${job.employer}"/>
      <c:set var="currUser" value="${user.handle}"/>
      <%@ include file = "navbar.jsp" %>
      <div id="applicationForm">
      	 <h3>${job.jobTitle}</h3>
      	 <h5>Please upload a sample of your work to continue</h5>
      	 <div id="formBody">
      	 	<c:choose>
            <c:when test="${empty user}">
               <h5>Please login or make an account to apply for a job</h5>
               <a href="https://jh-studio.herokuapp.com/login">Go to Login Page</a>
            </c:when>
            <c:otherwise>
               <form action="registerjob" enctype="multipart/form-data" method="post" id="applyForm">
               	  <input type="hidden" name="${job.jid}">
               	  <label>Work Sample</label>
                  <input type="file" id="fileInput" name="selectedFile" multiple size="50">
                  <input type="submit" class="btn btn-success subApp">
               </form>
            </c:otherwise>
         </c:choose>
      	 </div>  
      </div>
      <div class="container-fluid head">
         <div class="left">
            <h3>${job.jobTitle} </h3>
            <div class="available">Open</div>
         </div>
         <h3 class="right">${job.price} IDR / Page</h3>
      </div>
      <div class="jumbotron">
         <div class="container-fluid">
            <p>Job ID: <span id="jid">${job.jid}</span></p>
            <div class="description">
               <div class="projectDescription" >
                  <h5 class="headers">Project Description</h5>
                  <br>
                  <p>${job.description}</p>
               </div>
               <hr>
               <div class="skillsRequired">
                  <h5 class="skills">Skills Required</h5>
                  <c:if test="${currUser == 'admin'}">
                     <div class="skills ui-widget">
                     
                     	<label>Skills (Maximum of 5):</label>
            				<select class="form-control" id="skill">
			            		<c:forEach items="${tags}" var="tag">
			            			<option>${tag.skill}</option>
			            		</c:forEach>
                 			 </select>
            				<a href="#" id="add">Add</a>
                        <div id="skill-error"></div>
                     </div>
                  </c:if>
                  <div id="displaySkills">
                     <c:forEach items="${job.tags}" var="skill">
                        <div id="${skill.tid}" class="tag">
                        	${skill.skill}
                        	<c:if test="${currUser == 'admin'}">
                        		<span class="glyphicon glyphicon-remove remove"></span>
                        	</c:if>
                        </div>
                     </c:forEach>
                  </div>
               </div>  
               <hr>
               <c:choose>
                  <c:when test="${user.handle != 'admin'}">
                     <div class="miscInfo">
                        <c:if test="${user.checkApplied(job) == false || empty user}">
                           <button type="button" class="btn btn-success applyButton">Apply</button>
                        </c:if>
                        
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="header">
                        <h4>Applicants</h4>
                     </div>
                     <c:choose>
                        <c:when test="${empty applicants}">
                           <div id="noApplication">
                              <h6>There are currently no applicants for this job</h6>
                           </div>
                        </c:when>
                        <c:otherwise>
                           <div class="applicantList">
                              <c:forEach items="${applicants}" var="app">
                                 <div class="applicantDisplay">
                                    <div class="userInfo">
                                       <p id="userName"><a href="https://jh-studio.herokuapp.com/profile?uid=${app.user.uid}">${app.user.first} ${app.user.last} </a></p>
                                       <p id="userLocation">${app.user.location.city}, ${app.user.location.state}, ${app.user.location.country}</p>
                                       <c:forEach items="${app.user.skills}" var="skill">
                                          <div class="tag">${skill.skill}</div>
                                       </c:forEach>
                                    </div>
                                    <div class="accept">
                                       <a href="${app.user.resume}" download>View Resume</a> <br>
                                       <a href="${app.portPath}" download>View Work Sample</a>
                                    </div>
                                 </div>
                                 <hr>
                              </c:forEach>
                           </div>
                        </c:otherwise>
                     </c:choose>
                     <button type="button" class="btn btn-danger">Remove Job</button>
                  </c:otherwise>
               </c:choose>
            </div>
         </div>
      </div>
   </body>
</html>