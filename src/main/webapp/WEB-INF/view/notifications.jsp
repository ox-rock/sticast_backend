<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
  <title>StiCast! - Notifications</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/tables.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script  src="js/index.js"></script>
    
    <!------------ TOP NAVBAR ------------>
    <%@include file="navbar.jsp" %>
    <c:choose>
      <c:when test="${not empty user.username}">
        <script>$("#questionsList").toggleClass('show');</script>
      </c:when>
      <c:otherwise>
        <script>
          $("#nav_signin").toggleClass('show');
          $("#nav_signup").toggleClass('show');
          $("#user_menu").hide();
        </script>
      </c:otherwise>
    </c:choose>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-md-3">
         
        </div>
        
        <!------------ FOLLOWS TABLE ------------>
        <div class="col-md-9" style="padding-left: 10%;padding-right: 10%;">
          <table class="table-forecasts">
            <thead>
              <tr>
                <th class="text-left">Notifications</th>     
              </tr>
            </thead>
            <tbody class="table-hover">
              <c:forEach items="${notificationsList}" var="data" varStatus="item">
                <tr>
                  <td class="text-justify">User <b>${data.sender.username}</b> commented question <b>${data.question.text}</b> (${data.timestamp}) </td>
                </tr>
              </c:forEach> 
            </tbody>
          </table>
        </div>
        
      </div>
    </div>
    <%@ include file="footbar.jsp" %>
  </body>
</html>