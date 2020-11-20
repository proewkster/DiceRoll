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
        out.print("<div class=\"flexcontainer\">");
        for (Game game: games){
            out.print("<div class=\"gameList\">");
            if (game.getImgURL()==null){
                out.print("<img src=\"images/no-image-available-icon-13.jpg\"></img>");
            }
            else{ out.print("<img src="+game.getImgURL()+"></img>");}
            out.print("<div class=\"gametext\">");
            out.print("<p>"+game.getTitle()+"</p>");
            out.print("<p>Dit spel heeft een rating van "+game.getRating()+"/10.</p>");
            out.print("<p>Koopprijs: €"+game.getPrice_Sale()+"</p>");
            out.print("<p>Huurprijs: €"+game.getPrice_Rent()+"</p>");
            out.print("</div>");
            out.print("</div>");
        }
            out.print("</div>");
    %>


</main>
<footer>

</footer>

</body>
</html>
