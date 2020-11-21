<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title>DiceRoll - Register</title>
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
    <div class="row justify-content-center">
        <div class="col text-center">
            <h1 class="display-5 bg-dark text-light py-1">Register for an account</h1>
        </div>
    </div>

    <div class="container-md">

        <form:form method="post" action="register" modelAttribute="userDTO">
            <div class="row">
                <div class="col col-md-6 col-12 text-center">

                    <h2 class="py-1">Account Information</h2>

                    <spring:bind path="email">
                        <div class="form-row py-1">
                            <label>Email Address*:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="email" id="email"/>
                            <form:errors path="email" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">
                        <div class="form-row py-1">
                            <label>Password*:</label>
                            <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="password" id="password"/>
                            <form:errors path="password" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="confirmPassword">
                        <div class="form-row py-1">
                            <label>Confirm Password*:</label>
                            <form:input type="password" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="confirmPassword" id="confirmPassword"/>
                            <form:errors path="confirmPassword" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                </div>

                <div class="col col-md-6 text-center">

                    <h2 class="py-1">Personal information</h2>

                    <spring:bind path="firstname">
                        <div class="form-row py-1">
                            <label>First Name*:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="firstname" id="firstname"/>
                            <form:errors path="firstname" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastname">
                        <div class="form-row py-1">
                            <label>Last Name*:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="lastname" id="lastname"/>
                            <form:errors path="lastname" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="birthdate">
                        <div class="form-row py-1">
                            <label>Birthdate*:</label>
                            <form:input type="date" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="birthdate" id="birthdate"/>
                            <form:errors path="birthdate" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <h2 class="py-1">Address information</h2>

                    <div class="row">
                        <div class="col-12">
                            <spring:bind path="streetAddress">
                                <div class="form-row py-1">
                                    <label>Street Address*:</label>
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
                                    <label>Zipcode*:</label>
                                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="zipcode" id="zipcode"/>
                                    <form:errors path="zipcode" cssClass="text-danger"/>
                                </div>
                            </spring:bind>
                        </div>

                        <div class="col-8">
                            <spring:bind path="city">
                                <div class="form-row py-1">
                                    <label>City*:</label>
                                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="city" id="city"/>
                                    <form:errors path="city" cssClass="text-danger"/>
                                </div>
                            </spring:bind>
                        </div>
                    </div>

                    <h2 class="py-1">Contact Information</h2>

                    <spring:bind path="phoneNumber">
                        <div class="form-row py-1">
                            <label>Phone Number:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="phoneNumber" id="phoneNumber"/>
                            <form:errors path="phoneNumber" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="mobileNumber">
                        <div class="form-row py-1">
                            <label>Mobile Number:</label>
                            <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="mobileNumber" id="mobileNumber"/>
                            <form:errors path="mobileNumber" cssClass="text-danger"/>
                        </div>
                    </spring:bind>

                </div>
            </div>

            <div class="row justify-content-center">
                <p class="text-muted">Fields marked with * are mandatory</p>
            </div>
            <div class="row justify-content-center">
                <button type="submit" class="btn btn-dark btn-block col-md-6 mt-4 mx-4">Register</button>
            </div>
            <div class="row justify-content-center">
                <p class="py-1">Already have an account? <a href="login">Sign in</a></p>
            </div>

        </form:form>
    </div>

    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>

</body>
</html>
