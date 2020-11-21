<%@ page import="java.util.Collection" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 11/21/2020
  Time: 9:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Authenticated</title>
</head>
<body>
    <div class="row justify-content-center">
        <h1 class="display-3">Authentication Successful for: ${user.username}</h1>
    </div>
</body>
</html>
