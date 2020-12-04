<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Categorie" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 25/10/2020
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
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
                <li><a href="/login">Sign in</a></li>
            </ul>
        </nav>
    </div>
</header>
<main>
    <%
        List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");%>
        <h1>Categoriën</h1>
        <div class="container-fluid">
        <div class="row">
        <div class="col-md-3 col-lg-2  d-flex flex-column  border-right border-dark">

            <form:form  id="formCategories" action="/categorie" modelAttribute="filter" method="get">

                <c:forEach items="${categories}" var="cat">

                <form:checkbox name="${cat.categorieId}" path="id" value="${cat.categorieId}" onchange="document.getElementById('formCategories').submit()"/> ${cat.genre}
<br>
                </c:forEach>
            </form:form>

   </div>


    <%
        List<Game> games = (List<Game>) request.getAttribute("games");
        out.print("<div class=\"col-md-9 col-lg-10 d-flex justify-content-between flex-wrap\">");
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
            %>

            <table>
                <form action="categories" method="get">
                    <tr>
                        <% out.print("<td><input id=\"id\" name=\"id\" type=\"hidden\" value="+game.getGameID()+ "></td>"); %>
                    </tr>
                    <tr>
                        <td><label for="aantal">Aantal</label></td>
                        <td><input id="aantal" type="number"  name="aantal"/></td>
                    </tr>
                    <tr>
               <td><button class="buttonac" type="submit">Buy</button></td>
                        <td><% out.print("<a class=buttonac href=/RentGame?id="+game.getGameID()+">Rent</a>");%></td>
                    </tr>

               </form>
            </table>


            <%
            out.print("</div>");
            out.print("</div>");
        }
            out.print("</div>");
        out.print("</div>");
        out.print("</div>");
    %>
</main>
<footer>

</footer>

</body>
</html>
