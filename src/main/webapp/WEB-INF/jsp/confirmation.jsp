<%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 03/12/2020
  Time: 02:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title> DiceRollBoardGames | confirmation</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="container">
    <p class="col-md-12 text-center" style="font-size: 17px;"> The order is processed correctly! </p>
    <div class="col-md-12 text-center">
        <a href="/" class="btn-dark">Continue shopping</a>
    </div>
</div>
</body>
</html>
