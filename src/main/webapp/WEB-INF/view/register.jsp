<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<html lang="en">
  <head>
    <title>StiCast! - Register</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/forms.css" />">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script  src="js/index.js"></script>
    
    <!------- TOP NAVBAR ------->
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
    <div class="user">
      <header class="user__header">
        <h1 class="user__title"><b>StiCast!</b><br></h1>
        <h1 class="user__title2">Registration form</h1><br>
      </header>
      <form:form class="form_register" action="${pageContext.request.contextPath}/register/processRegistrationForm" modelAttribute="crmUser" method="post">
        <c:choose>
          <c:when test="${registrationError != null}">
            <div class="form_error">${registrationError}</div>
          </c:when>  
        </c:choose>      
      
        <div class="form__group"><form:errors path="userName" cssClass="error" />
							<form:input path="userName" placeholder="Username" class="form__input" /></div>
        <div class="form__group"><form:errors path="password" cssClass="error" />
							<form:password path="password" placeholder="Password " class="form__input" /></div>
							
		 <div class="form__group"><form:errors path="matchingPassword" cssClass="error" />
							<form:password path="matchingPassword" placeholder="Confirm password" class="form__input" />
					</div>
							
		<div class="form__group"><form:errors path="firstName" cssClass="error" />
							<form:input path="firstName" placeholder="First name" class="form__input" /></div>
					
		<div class="form__group"><form:errors path="lastName" cssClass="error" />
							<form:input path="lastName" placeholder="Last name" class="form__input" />
						</div>								
							
        <div class="form__group"><form:errors path="email" cssClass="error" />
							<form:input path="email" placeholder="Email" class="form__input" /></div>
        <button class="btn" type="submit" name="op" value ="register">Register</button>
      </form:form>
    </div>
  <%@ include file="footbar.jsp" %>
  </body>
</html>