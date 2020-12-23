<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Categorie" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 25/10/2020
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
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

        <h1>Categoriën</h1>
    <p> Kies er maximaal 2 om specifiek te filteren.</p>
        <div class="container-fluid">
        <div class="row">
        <div class="col-md-3 col-lg-2  d-flex flex-column  border-right border-dark">

            <form:form  id="formCategories" action="/categorie" modelAttribute="filter" method="get">

                <c:forEach items="${categories}" var="cat">

                <form:checkbox name="${cat.categorieId}"  path="categorieIds" value="${cat.categorieId}" onchange="document.getElementById('formCategories').submit()"/> ${cat.genre}
<br>
                </c:forEach>
            </form:form>

   </div>


    <%
        List<Game> games = (List<Game>) request.getAttribute("games");
        out.print("<div class=\"col-md-9 col-lg-10 flex-wrap d-flex  justify-content-center align-items-baseline\">");
        for (Game game: games){
            out.print("<div class=\"gameList\">");
            if (game.getImgURL()==null){
                out.print("<img src=\"images/no-image-available-icon-13.jpg\"></img>");
            }
            else{ out.print("<img src="+game.getImgURL()+"></img>");}
            out.print("<div class=\"gametext\">");
            out.print("<button type=\"button\" class=\"btn moreInfo\" data-toggle=\"modal\" data-target=\"#gameid"+game.getGameID()+"\">"+game.getTitle()+"</button>");
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


            <!-- Modal -->


            <div class="modal fade" id="<%out.print("gameid"+game.getGameID());%>" tabindex="-1" aria-labelledby="moreInfoLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="<%out.print(game.getGameID());%>"><%out.print(game.getTitle());%></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Titel: <%out.print(game.getTitle());%></p>
                            <div class="afbModal">
                          <%  if (game.getImgURL()==null){
                            out.print("<img src=\"images/no-image-available-icon-13.jpg\"></img>");
                            }
                            else{ out.print("<img class=\"img-fluid\" src="+game.getImgURL()+"></img>");}%>
                            </div>
                            <p>Mininimum aantal spelers: <%out.print(game.getMinPlayers());%></p>
                            <p>Maximum aantal spelers: <%out.print(game.getMaxPlayers());%></p>
                            <p>De gemiddelde duur: <%out.print(game.getAvgTime());%> min</p>
                            <p>Vanaf: <%out.print(game.getAge());%> jaar</p>
                            <p>Categorieën: <%out.print(game.getCategory());%></p>
                            <p>Uitgever: <%out.print(game.getDistributer());%></p>
                            <p>Jaar van publicatie: <%out.print(game.getYear());%></p>
                            <p>Koopprijs: €<%out.print(game.getPrice_Sale());%></p>
                            <p>Huurprijs: €<%out.print(game.getPrice_Rent());%></p>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <%
            out.print("</div>");
            out.print("</div>");
        }
            out.print("</div>");
        out.print("</div>");
        out.print("</div>");
    %>


            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                <c:set var="enabledprev" value="${pageinfo.currentPage==0?'disabled':''}"/>
                    <li class="page-item ${enabledprev}"><a class="page-link" href="/categorie/0">First</a></li>
                    <li class="page-item ${enabledprev}"><a class="page-link" href="/categorie/${pageinfo.currentPage-1}">Previous</a></li>
                    <c:set var="active" value=""/>
                       <form:form  id="paging" action="/categorie" modelAttribute="pageinfo" method="get">
<%--                           <c:choose>--%>
<%--                               <c:when test="${pageinfo.currentPage<5}">--%>
<%--                                   <c:forEach var = "i" begin = "0" end = "9">--%>
<%--                                   <c:set var="active" value="${pageinfo.currentPage==i ? 'btn-primary': 'btn-secondary'}"/>--%>
<%--                                       <form:button  class="btn ${active}" path="currentPage" type="submit" value="${i}">${i+1}</form:button>--%>
<%--                                   </c:forEach>--%>
<%--                               </c:when>--%>
<%--                           </c:choose>--%>

                           <c:choose>
                               <c:when test="${pageinfo.currentPage<=5}">
                                   <c:forEach var = "i" begin = "0" end = "9">
                                       <c:set var="active" value="${pageinfo.currentPage==i ? 'btn-primary': 'btn-secondary'}"/>
                                       <a  class="btn ${active}" href="/categorie/${i}" >${i+1}</a>
                                   </c:forEach>
                               </c:when>
                               <c:when test="${pageinfo.currentPage>5 && pageinfo.currentPage<pageinfo.totalPages-5}">
                                   <c:forEach var = "i" begin = "${pageinfo.currentPage-5}" end = "${pageinfo.currentPage+4}">
                                       <c:set var="active" value="${pageinfo.currentPage==i ? 'btn-primary': 'btn-secondary'}"/>
                                       <a  class="btn ${active}" href="/categorie/${i}" >${i+1}</a>
                                   </c:forEach>
                               </c:when>
                               <c:when test="${pageinfo.currentPage>=pageinfo.totalPages-5}">
                                   <c:forEach var = "i" begin = "${pageinfo.totalPages-11}" end = "${pageinfo.totalPages-1}">
                                       <c:set var="active" value="${pageinfo.currentPage==i ? 'btn-primary': 'btn-secondary'}"/>
                                       <a  class="btn ${active}" href="/categorie/${i}" >${i+1}</a>
                                   </c:forEach>
                               </c:when>
                           </c:choose>
                    </form:form>
                    <c:set var="enablednext" value="${pageinfo.currentPage ==pageinfo.totalPages-1?'disabled':''}"/>
                    <li class="page-item ${enablednext}"><a class="page-link" href="/categorie/${pageinfo.currentPage+1}">Next</a></li>
                    <li class="page-item ${enablednext}"><a class="page-link" href="/categorie/${pageinfo.totalPages-1}">Last</a></li>
                </ul>
            </nav>
</main>
<footer>

</footer>

</body>
</html>
