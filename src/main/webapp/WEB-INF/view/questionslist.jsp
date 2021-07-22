<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
  <head>
    <title>StiCast! - Questions</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/tables.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/questions.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
    <!----------------- TOP NAVBAR ----------------->
    <%@ include file="navbar.jsp" %>
    <c:choose>
      <c:when test="${not empty sessionScope.user}">
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

  <!------------ SELECT CATEGORY FORM ------------>
  <div align="center" style="width:20%; margin:auto;">
    <form method="GET" id="formCategory">
      <select name ="category" id="selectCategory" style="height:27px; width: 150px">
        <option  value="all">All questions</option>

      </select>
      <button class="button-choose"><span>Choose</span></button>
    </form>
  </div>

  <!------------ QUESTIONS TABLE ------------>
  <div class="table-questions-title" align="center" style="font-family: Roboto; text-transform:uppercase; margin-bottom: 30px; margin-top: 40px"></div>
  <div style="padding-bottom: 100px;">
    <table class="table-questions" id="myTable">
      <thead>
      <tr>
        <th class="text-left"><b>Questions</b></th>
        <th class="text-center"><b>Expiration date</b></th>
        <th class="text-center"><b>Status</b></th>
      </tr>
      </thead>
      <tbody class="table-hover">

      </tbody>
    </table>
  </div>

    
    <%@ include file="footbar.jsp" %>

    <script type="text/javascript">

      $(document).ready(function() {
        $.getJSON("http://localhost:8080/api/categories", function(data){
          data.forEach(function(item){
            $('#formCategory option:last').after('<option  value="' + item.name + '">' + item.name + '</option>');
          })
        });
      });

      $(document).ready(function() {
        $.getJSON("http://localhost:8080/api/questions", function(data){
          data.forEach(function(item){
            $('#myTable tr:last').after('<tr>' +
                    '<td class="text-justify"><a href="${pageContext.request.contextPath}/question/' +item.id+ '" id="' +item.id+ '">' + item.text + '</a></td>' +
                    '<td class="text-center">'+item.expirationDate+'</td>' +
                    '<td class="text-center">'+item.status+'</td>' +
                    '</tr>');
          })
        });
      });

    </script>
  </body>
</html>