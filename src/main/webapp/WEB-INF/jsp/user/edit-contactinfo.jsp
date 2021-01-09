<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">
    <title>DiceRoll - Aanpassen Contact Informatie</title>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<div class="container">
    <div class="row">
        <div class="col col-lg-6 offset-lg-3 col-12 text-center">
            <h1 class="py-1">Persoonlijke Informatie</h1>
        </div>
    </div>

    <div class="row">
        <div class="col col-8 offset-2 text-center p-md-5">
            <h2 class="text-left highlight">Gegevens wijzigen</h2>

            <form:form method="post" action="/user/edit-contactinfo" modelAttribute="userChangeContactDTO">
                <spring:bind path="streetAddress">
                    <div class="form-row py-1">
                        <label>Adres:</label>
                        <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="streetAddress" id="streetAddress" placeholder="Adres"/>
                        <form:errors path="streetAddress" cssClass="text-danger"/>
                    </div>
                </spring:bind>
                <spring:bind path="zipCode">
                    <div class="form-row py-1">
                        <label>Postcode:</label>
                        <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="zipCode" id="zipCode" placeholder="Postcode"/>
                        <form:errors path="zipCode" cssClass="text-danger"/>
                    </div>
                </spring:bind>
                <spring:bind path="city">
                    <div class="form-row py-1">
                        <label>Gemeente:</label>
                        <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="city" id="city" placeholder="Gemeente"/>
                        <form:errors path="city" cssClass="text-danger"/>
                    </div>
                </spring:bind>
                <spring:bind path="phoneNumber">
                <div class="form-row py-1">
                    <label>Telefoon:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="phoneNumber" id="phoneNumber" placeholder="Telefoonnummer"/>
                    <form:errors path="phoneNumber" cssClass="text-danger"/>
                </div>
                </spring:bind>
                <spring:bind path="mobileNumber">
                    <div class="form-row py-1">
                        <label>Mobiel:</label>
                        <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="mobileNumber" id="mobileNumber" placeholder="Mobiel nummer"/>
                        <form:errors path="mobileNumber" cssClass="text-danger"/>
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