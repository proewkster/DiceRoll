<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 5/12/2020
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DiceRoll - Fout</title>
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
    .container, body, html, row{
        height: 100vh;
        overflow: hidden;
    }
    h1{
        margin-bottom: 25px;
    }
</style>
</head>
<body>
<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="container d-flex align-items-center">
    <div class="row ">

        <h1 class="col-12 text-center">Onbekende fout</h1>

        <div class="col-6"><img src="${pageContext.request.contextPath}/images/Dice.png" class="img-fluid"></div>

        <h2 class="col-6 text-center">Oh nee, je hebt gegokt en verloren.Er heeft zich een onbekend probleem voorgedaan.</h2>
    </div>

</div>

</body>
</html>
