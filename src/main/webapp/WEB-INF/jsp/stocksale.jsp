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
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title> DiceRollBoardGames | stocksale</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
    <div class="container">
        <% Game game = (Game)request.getAttribute("Game");
            if (game.getStock_Sale()==0){
        %>
        <p class="text-center text-black-50">No More items of <%out.print(game.getTitle());%> Available</p>
        <% }else if (game.getStock_Sale()==1){%>
        <p class="text-center text-black-50">Only <%out.print(game.getStock_Sale());%> More item of <%out.print(game.getTitle());%> Available</p>
        <%} else {%>
        <p class="text-center text-black-50">Only <%out.print(game.getStock_Sale());%> More items of <%out.print(game.getTitle());%> Available</p>
        <%};%>
    </div>
</body>
</html>
