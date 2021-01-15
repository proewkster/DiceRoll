<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 11/21/2020
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>DiceRoll - Login</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<div class="row justify-content-center">
    <div class="col text-center">
        <h1 class="display-5 bg-dark text-light py-1">Inloggen</h1>
    </div>
</div>

<div class="container-md">

    <form method="post" action="login">
        <div class="row justify-content-center">
            <div class="col col-md-6 col-12 text-center">

                <c:if test="${not empty errorMessage}">
                    <div class="text-danger">${errorMessage}</div>
                </c:if>

                <c:if test="${not empty logoutMessage}">
                    <div class="text-danger">${logoutMessage}</div>
                </c:if>

                <div class="form-row py-1">
                    <label>Emailadres:</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="email address"/>
                </div>

                <div class="form-row py-1">
                    <label>Wachtwoord:</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="password"/>
                </div>

            </div>
        </div>

        <div class="row justify-content-center">
            <button type="submit" class="btn btn-primary btn-block col-md-6 mt-4 mx-4">Sign in</button>
        </div>
        <div class="row justify-content-center">
            <p class="py-1">Geen account? <a href="register">Maak er een aan</a></p>
        </div>
    </form>
</div>

<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>

</body>
</html>
