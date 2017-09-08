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
      <link href="css/jobs.css" rel="stylesheet">
      <script src="js/jobs.js"></script>
   </head>
   <body>
      <%@ include file = "navbar.jsp" %>
      <div class="heading">
         <div class="header">
            <h2 id="head">Job Search Results</h2>
         </div>
      </div>
      <hr>
      <div id="lower">
         <div class="filters">
            <a href="#" class="designer">All Jobs</a> <br>
            <hr>
            <h3>Categories</h3>
            <ul>
               <li class="designer"><a href="#">Drafter (Interior)</a></li>
               <li class="designer"><a href="#">Drafter (Architecture)</a></li>
               <li class="designer"><a href="#">Renderer (2D)</a></li>
               <li class="designer"><a href="#">Renderer (3D)</a></li>
               <li class="designer"><a href="#">FFE</a></li>
            </ul>
         </div>
         <div class="results">
            <h3 id="jobHead">All Jobs</h3>
            <hr>
         </div>
         <div class="jobContainer">
            <div class="jobs">
               <c:choose>
                  <c:when test="${empty jobs}">
                     <div class="noJobs">
                        <h4>No Jobs Currently Available</h4>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <c:forEach items="${jobs}" var="job">
                        <div class="panel panel-default work">
                           <div class="panel-heading workHead">
                              <div class="left">
                                 <h5><a href="https://jh-studio.herokuapp.com/job?jid=${job.jid}" id="jobTitle">${job.jobTitle}</a></h5>
                                 <c:if test="${job.complete eq false}">
                                    <div class="available">Open</div>
                                 </c:if>
                                 <c:if test="${job.complete eq true}">
                                    <div class="closed">Closed</div>
                                 </c:if>
                              </div>
                           </div>
                           <div class="panel-body">
                              <div class="price">
                                 ${job.price} IDR / Page<br>
                                 ${job.type} Job <br>
                                 <c:set var="employer" value="${job.employer}"/>
                                 <c:set var="currUser" value="${user.handle}"/>
                                 <c:choose>
                                    <c:when test="${user.handle != 'admin'}">
                                       <c:if test="${user.checkApplied(job) == true}">
                                          <button type="button" class="btn btn-warning" id="${job.jid}">Applied</button>
                                       </c:if>
                                    </c:when>
                                    <c:when test="${job.complete eq true}">
                                       <button type="button" class="btn btn-success open" id="${job.jid}">Open Job</button>
                                    </c:when>
                                    <c:otherwise>
                                       <button type="button" class="btn btn-danger remove" id="${job.jid}">Close Job</button>
                                    </c:otherwise>
                                 </c:choose>
                                 <br>
                              </div>
                              <div class="desc">
                                 <p>${job.description}</p>
                              </div>
                              <div class="skills">
                                 <c:forEach items="${job.tags}" var="skill">
                                    <div class="tag">${skill.skill}</div>
                                 </c:forEach>
                              </div>
                              <div class="application">
                                 <p>
                                    ${job.size()} people have applied for this job
                                    <br>
                                    Job Number: ${job.jid}
                                 </p>
                              </div>
                           </div>
                        </div>
                     </c:forEach>
                  </c:otherwise>
               </c:choose>
            </div>
         </div>
      </div>
   </body>
</html>