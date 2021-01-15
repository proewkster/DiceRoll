<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 11/21/2020
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="../webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">
    <title>DiceRoll - Aanpassen Account Informatie</title>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>


<div class="container">
    <div class="row">
        <div class="col col-lg-6 offset-lg-3 col-12 text-center">
            <h1 class="py-1">Account Informatie</h1>
        </div>
    </div>

    <div class="row">

        <!-- SECTION - Edit email address -->
        <div class="col col-8 offset-2 text-center p-md-5">
            <h2 class="text-left highlight">Emailadres wijzigen</h2>

            <p class="text-left mt-4"><strong>Huidig emailadres:</strong></p>
            <p class="text-left pl-5">${authUser.email}</p>

            <form:form method="post" action="/user/edit-email" modelAttribute="userChangeEmailDTO">
                <spring:bind path="newEmail">
                    <div class="form-row py-1">
                        <label>Nieuw emailadres:</label>
                        <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="newEmail" id="newEmail" placeholder="Nieuw emailadres"/>
                        <form:errors path="newEmail" cssClass="text-danger"/>
                    </div>
                </spring:bind>
                <spring:bind path="verifyPassword">
                    <div class="form-row py-1">
                        <label>Wachtwoord:</label>
                        <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="verifyPassword" id="verifyPassword" placeholder="Wachtwoord"/>
                        <form:errors path="verifyPassword" cssClass="text-danger"/>
                    </div>
                </spring:bind>

                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary col-md-4 mt-4 mx-4">Aanpassen</button>
                    <a href="/user/info" class="btn btn-primary col-md-4 mt-4 mx-4">Annuleren</a>
                </div>
            </form:form>
        </div>

        <!-- SECTION - Edit password -->
        <div class="col col-8 offset-2 text-center p-md-5">

            <h2 class="text-left highlight">Wachtwoord wijzigen</h2>

            <form:form method="post" action="/user/edit-password" modelAttribute="userChangePasswordDTO">
                <spring:bind path="oldPassword">
                    <div class="form-row py-1 mt-4">
                        <label>Oud wachtwoord:</label>
                        <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="oldPassword" id="oldPassword" placeholder="Oud wachtwoord"/>
                        <form:errors path="oldPassword" cssClass="text-danger"/>
                    </div>
                </spring:bind>
                <spring:bind path="newPassword">
                    <div class="form-row py-1 mt-4">
                        <label>Nieuw wachtwoord:</label>
                        <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="newPassword" id="newPassword" placeholder="Nieuw wachtwoord"/>
                        <form:errors path="newPassword" cssClass="text-danger"/>
                    </div>
                </spring:bind>
                <spring:bind path="confirmPassword">
                    <div class="form-row py-1">
                        <label>Herhaal nieuw wachtwoord:</label>
                        <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="confirmPassword" id="confirmPassword" placeholder="Herhaal nieuw wachtwoord"/>
                        <form:errors path="confirmPassword" cssClass="text-danger"/>
                    </div>
                </spring:bind>

                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-primary col-md-4 mt-4 mx-4">Aanpassen</button>
                    <a href="/user/info" class="btn btn-primary col-md-4 mt-4 mx-4">Annuleren</a>
                </div>

            </form:form>
        </div>
    </div>
</div>

<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>