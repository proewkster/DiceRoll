<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 15/11/2020
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DiceRoll - Admin Game</title>
    <link href="../webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="row justify-content-center">
    <div class="col text-center">
        <h1 class="display-5 bg-dark text-light py-1">CRUD Games</h1>
    </div>
</div>

<div class="container-md">
    <form:form action="/admin/game" method="get" modelAttribute="search">
        <label for="keyword">Door Titel:</label>
        <form:input path="keyword" id="keyword"/>
        <label for="id">Door Id:</label>
        <form:input path="id"/>
        <button class="btn-dark" type="submit">Zoeken</button>

        <a class="btn-dark" style="padding: 5px;text-decoration: none;float: right" href="/admin/addGame">Game Toevoegen</a>
    </form:form>
    <table class="table table-hover">
        <tr>
            <th scope="col"></th>
            <th scope="col">Title</th>
            <th scope="col">id</th>
        </tr>
    <%  List<Game> games = (List<Game>) request.getAttribute("games");
    if (games.size()==0){
        out.print("<tr><td>#</td>");
        out.print("<td>No Values available for search terms</td><td>#</td></tr>");
    }else {
    int i = 0;
        for (Game game: games){
            i++;
            out.print("<tr>");
            out.print("<th scope=\"row\">"+i+"</th>");
            out.print("<td>"+game.getTitle()+"</td>");
            out.print("<td>"+game.getGameID()+"</td>");
            out.print("<td><a href=editGame?id="+game.getGameID()+">edit</a></td>");
            out.print("<td><a href=deleteGame/"+game.getGameID()+">delete</a></td>");
            out.print("</tr>");
        }
        }
    %>
    </table>

</div>

<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>

</body>
</html>


