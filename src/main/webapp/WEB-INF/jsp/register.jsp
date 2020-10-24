<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
    <h1>Register for an account</h1>
    <form action="/showuser" th:th:object="user" method="post" enctype="utf8">
        <div>
            <label>First Name:</label>
            <input type="text" name="firstName" id="firstName">
        </div>
        <div>
            <label>Last Name:</label>
            <input type="text" name="lastName" id="lastName">
        </div>
        <div>
            <label>Email Address:</label>
            <input type="text" name="email" id="email">
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password" id="password">
        </div>
        <div>
            <label>Confirm Password:</label>
            <input type="password" name="matchingPassword" id="matchingPassword">
        </div>
        <button type="submit">Register</button>
    </form>
</body>
</html>
