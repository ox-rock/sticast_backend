<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>              
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/"><b>StiCast!</b></a>    
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">               
        <li id="questionsList" class="hide"><a href="${pageContext.request.contextPath}/questions">Browse questions</a></li>  
      </ul>  
      <ul class="nav navbar-nav navbar-right">      
        <li id="nav_signin" class="hide"><a href="${pageContext.request.contextPath}/login" id="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li id="nav_signup" class="hide"><a href="${pageContext.request.contextPath}/register/showRegistrationForm" id="registration"><span class="glyphicon glyphicon-plus-sign"></span> Register</a></li>  
        <li class="dropdown-active" id="user_menu">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span>  ${sessionScope.user.userName} <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#"><b>Username</b>: ${user.userName} </a></li>
            <li><a href="#"><b>Budget</b>: <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${user.budget}"/>$</a></li>
            <li><br></li>
            <li><a href="${pageContext.request.contextPath}/profile" id="profile">My profile</a></li>
            <li><form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>