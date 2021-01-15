<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 24/10/2020
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>DiceRoll - Register</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

    <div class="row justify-content-center">
        <div class="col text-center">
            <h1 class="display-5 bg-dark text-light py-1">Account aanmaken</h1>
        </div>
    </div>

    <div class="container-md">

        <form:form method="post" action="register" modelAttribute="userDTO">
            <div class="row">
                <div class="col col-md-6 col-12 text-center">

                    <h2 class="py-1 highlight">Account informatie</h2>

                    <spring:bind path="email">
                        <div class="form-row py-1">
                            <label>Emailadres*:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="email" id="email"/>
                            <form:errors path="email" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="form-row py-1">
                            <label>Wachtwoord*:</label>
                            <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="password" id="password"/>
                            <form:errors path="password" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="confirmPassword">
                        <div class="form-row py-1">
                            <label>Wachtwoord bevestigen*:</label>
                            <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="confirmPassword" id="confirmPassword"/>
                            <form:errors path="confirmPassword" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                </div>

                <div class="col col-md-6 text-center">

                    <h2 class="py-1 highlight">Persoonlijke informatie</h2>

                    <spring:bind path="firstname">
                        <div class="form-row py-1">
                            <label>Voornaam*:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="firstname" id="firstname"/>
                            <form:errors path="firstname" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastname">
                        <div class="form-row py-1">
                            <label>Achternaam*:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="lastname" id="lastname"/>
                            <form:errors path="lastname" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="birthdate">
                        <div class="form-row py-1">
                            <label>Geboortedatum*:</label>
                            <form:input type="date" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="birthdate" id="birthdate"/>
                            <form:errors path="birthdate" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <h2 class="py-1 highlight">Adres informatie</h2>

                    <div class="row">
                        <div class="col-12">
                            <spring:bind path="streetAddress">
                                <div class="form-row py-1">
                                    <label>Straat en huisnummer*:</label>
                                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="streetAddress" id="streetAddress"/>
                                    <form:errors path="streetAddress" cssClass="text-danger"/>
                                </div>
                            </spring:bind>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-4">
                            <spring:bind path="zipcode">
                                <div class="form-row py-1">
                                    <label>Postcode*:</label>
                                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="zipcode" id="zipcode"/>
                                    <form:errors path="zipcode" cssClass="text-danger"/>
                                </div>
                            </spring:bind>
                        </div>

                        <div class="col-8">
                            <spring:bind path="city">
                                <div class="form-row py-1">
                                    <label>Gemeente*:</label>
                                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="city" id="city"/>
                                    <form:errors path="city" cssClass="text-danger"/>
                                </div>
                            </spring:bind>
                        </div>
                    </div>

                    <h2 class="py-1 highlight">Contact informatie</h2>

                    <spring:bind path="phoneNumber">
                        <div class="form-row py-1">
                            <label>Telefoonnummer:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="phoneNumber" id="phoneNumber"/>
                            <form:errors path="phoneNumber" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="mobileNumber">
                        <div class="form-row py-1">
                            <label>Mobiel nummer:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="mobileNumber" id="mobileNumber"/>
                            <form:errors path="mobileNumber" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                </div>
            </div>

            <div class="row justify-content-center">
                <p class="text-muted">Velden gemarkeerd met * zijn verplicht</p>
            </div>
            <div class="row justify-content-center">
                <button type="submit" class="btn btn-primary btn-block col-md-6 mt-4 mx-4">Registreren</button>
            </div>
            <div class="row justify-content-center">
                <p class="py-1">Heb je al een account? <a href="login">Log hier in</a></p>
            </div>

        </form:form>
    </div>

    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>

</body>
</html>
