<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 25/10/2020
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Categorie</title>
</head>
<body>
<header>
    <div class="container">
        <div id="Branding">
            <H1><span class="highlight">DiceRoll</span> BoardGames</H1>
        </div>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/contact">Contact</a></li>
                <li><a href="/overons">Over Ons</a></li>
                <li><a class="highlight" href="/categorie">Categorie</a></li>
                <li><a href="/winkelmand">Winkelmand</a></li>
                <li><a href="/signin">Sign in</a></li>
            </ul>
        </nav>
    </div>
</header>
<main>
    <%
        List<Game> games = (List<Game>) request.getAttribute("games");
        for (Game game: games){
            out.print("ID: "+game.getGameID() +" - Naam: "+game.getTitle() + " - minimum aantal spelers: "+game.getMinPlayers()+ "<br>");
        }
    %>

</main>
<footer><p>under construction</p></footer>

</body>
</html>
