<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
  <title>StiCast! - Forecasts History</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/tables.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script  src="<c:url value="/js/index.js" />"></script>
    <!------------ TOP NAVBAR ------------>
    <%@include file="navbar.jsp" %>
    <c:choose>
      <c:when test="${not empty sessionScope.username}">
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
      <div class="row" style="margin-bottom: 90px">
        <div class="col-md-3">
          <div class="list-group">
            <a href="${pageContext.request.contextPath}/profile" class="list-group-item list-group-item-action">Edit Profile</a>
            <a href="${pageContext.request.contextPath}/following" class="list-group-item list-group-item-action">Following Questions</a>
            <a href="${pageContext.request.contextPath}/forecasts" class="list-group-item list-group-item-action active">Forecasts History</a>
          </div>
        </div>
        <!------------ FORECASTS TABLE ------------>
        <div class="col-md-9" style="padding-left: 10%;padding-right: 10%;">
          <table class="table-forecasts">
            <thead>
              <tr>
                <th class="text-left">Question ID</th>
                <th class="text-center">Answer</th>
           	    <th class="text-center">Quantity</th>
           	    <th class="text-center">Payout</th>
            	<th class="text-center">Timestamp</th>  
        	  </tr>
            </thead>
            <tbody class="table-hover">
              <c:forEach items="${forecast}" var="data" varStatus="item">
                <tr>
                  <td class="text-justify"> <a href="${pageContext.request.contextPath}/question/${data.question.id}">${data.question.id}</a></td>
                  <td class="text-center">${data.answer}</td>
                  <td class="text-center">${data.quantity}</td>
                  <td class="text-center">${data.payout} $</td>
                  <td class="text-center">${data.timestamp}</td>
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