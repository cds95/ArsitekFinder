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
      
      <!-- Non Bootstrap -->
      <link href="css/universal.css" rel="stylesheet">
      <link href="css/index.css" rel="stylesheet">
      <script src="js/index.js"></script>
   </head>
   <body>
      <%@ include file = "navbar.jsp" %>
      <div class="jumbotron">
         <div class="container-fluid">
            <div class="information" id="signup">
               <div class="header">
                  <h2>Find an Architect Today!</h2>
               </div>
               <form class="form-inline">
                  <div class="form-group ui-widget">
                     <input type="text" class="form-control" id="jobSearch" placeholder="Search for Jobs">
                  </div>
                  <div class="form-group">
                     <button type="submit" class="btn btn-default">Search</button>
                  </div>
               </form>
            </div>
         </div>
      </div>
      <c:if test="${empty user}">
         <div class="signup" id="drop">
            <div class="container-fluid">
               <h2>Create a Free Account Today</h2>
               <form class="form-horizontal">
                  <div class="form-group">
                     <div class="col-sm-1"> </div>
                     <label class="control-label col-sm-2">First Name:</label>
                     <div class="col-sm-7">
                        <input type="text" class="form-control" id="first">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-1"> </div>
                     <label class="control-label col-sm-2">Last Name:</label>
                     <div class="col-sm-7">
                        <input type="text" class="form-control" id="last">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-1"> </div>
                     <label class="control-label col-sm-2">Username:</label>
                     <div class="col-sm-7">
                        <input type="text" class="form-control" id="handle">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-1"> </div>
                     <label class="control-label col-sm-2">Password:</label>
                     <div class="col-sm-7">
                        <input type="password" class="form-control" id="password">
                     </div>
                  </div>
                  <div class="form-group">
                     <div class="col-sm-1"> </div>
                     <label class="control-label col-sm-2">Email:</label>
                     <div class="col-sm-7">
                        <input type="text" class="form-control" id="email">
                     </div>
                  </div>
               </form>
               <button id="submit" class="btn btn-default">Sign Up</button>
               <div id="wait"></div>
            </div>
         </div>
      </c:if>
   </body>
</html>