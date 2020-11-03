<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 24/10/2020
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>

<%--
  Temporary page to validate user registration process
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show User</title>
</head>
<body>
    <h1>New user account details</h1>
    <p>Voornaam: ${user.firstName}</p>
    <p>Achternaam: ${user.lastName}</p>
    <p>Emailadres: ${user.email}</p>
    <p>Paswoord: ${user.password}</p>
</body>
</html>
