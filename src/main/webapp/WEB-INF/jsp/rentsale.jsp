<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 04/12/2020
  Time: 00:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title> DiceRoll - Stock Verhuur</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="container">
    <div class="row">
    <% Game game = (Game)request.getAttribute("Game");
        if (game.getStock_Sale()==0){
    %>
    <p class="text-center text-black-50"> Geen <%out.print(game.getTitle());%> spel meer beschikbaar.</p>
    <% }else if (game.getStock_Rent()==1){%>
    <p class="text-center text-black-50">Nog maar <%out.print(game.getStock_Rent());%> spel van <%out.print(game.getTitle());%> verhuurbaar.</p>
    <%} else {%>
    <p class="text-center text-black-50">Nog maar <%out.print(game.getStock_Rent());%> spellen van <%out.print(game.getTitle());%> verhuurbaar.</p>
    <%};%>
    </div>
    <div class="row">
        <a class="buttonac" <%out.print("href=/koopResterend?id="+game.getGameID()+"&aantal="+game.getStock_Rent());%>>Huur <%out.print(game.getStock_Rent());%> </a>
        <a class="buttonac" href="/categorie">Herbekijk Het gamma</a>
    </div>
</div>
</body>
</html>