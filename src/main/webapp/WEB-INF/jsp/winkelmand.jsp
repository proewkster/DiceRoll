<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.TestDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.RentGameDTO" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 05/11/2020
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>winkelmand</title>
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="css/style.css">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

    <header>
        <div class="container">
            <div id="Branding">
                <H1><span class="highlight">DiceRoll</span> BoardGames</H1>
            </div>
            <nav>
                <ul>
                    <li><a  href="/">Home</a></li>
                    <li><a href="/contact">Contact</a></li>
                    <li><a href="/overons">Over Ons</a></li>
                    <li><a href="/categorie">Categorie</a></li>
                    <li><a class="highlight" href="/winkelmand">Winkelmand</a></li>
                    <li><a href="/login">Sign in</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <section>

        <div class="container-md">
            <h1 class="text-center">Gekochte games</h1>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Titel</th>
                    <th scope="col">Aantal</th>
                    <th scope="col">Prijs</th>
                </tr>
                </thead>
                <tbody>
                <%List<TestDTO> testen = (List<TestDTO>) session.getAttribute("test");
                if (testen == null){testen = new ArrayList<TestDTO>();}
                    int i = 1;
                    int id = 0;
                    double totaal1 = 0;
                    for (TestDTO test:testen)
                    {
                        out.print("<tr>");
                        out.print("<th scope=row>"+i+"</th>");
                        out.print("<td> "+test.getTitle() +"</td>");
                        out.print("<td> "+test.getAantal() +"</td>");
                        out.print("<td> €"+test.getAantal() * test.getPrice() +"</td>");
                        out.print("<td><a href=/delitemwinkelmand?id="+id+">Delete</a></td>");
                        out.print("<td><a href=/additemwinkelmand?id="+id+">+</a></td>");
                        out.print("<td><a href=/minitemwinkelmand?id="+id+">-</a></td>");
                        totaal1 += test.getAantal()*test.getPrice();
                        out.print("</tr>");
                        i++;
                        id++;
                    }
                %>
                </tbody>
            </table>
            <h1 class="text-center">Gehuurde games</h1>
        <table class="table" style="float: left">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Titel</th>
                <th scope="col">Aantal</th>
                <th scope="col">Prijs</th>
            </tr>
            </thead>
            <tbody>
            <%List<RentGameDTO> rentGameDTOS  = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
                if (rentGameDTOS == null){rentGameDTOS = new ArrayList<RentGameDTO>();}
                int j = 1;
                double totaal2 = 0;
                for (RentGameDTO rentGameDTO:rentGameDTOS)
                {
                    out.print("<tr>");
                    out.print("<th scope=row>"+j+"</th>");
                    out.print("<td> "+rentGameDTO.getTitle() +"</td>");
                    out.print("<td>"+rentGameDTO.getAantal()+"</td>");
                    out.print("<td> €"+rentGameDTO.getPrice()*rentGameDTO.getAantal() +"</td>");
                    totaal2 += rentGameDTO.getPrice()*rentGameDTO.getAantal();
                    out.print("</tr>");
                    j++;
                }
            %>
            </tbody>
        </table>
            <div class="col-md-12 text-right blockquote" >
                <p>Totaal: € <% double totaal = totaal1 + totaal2; out.print(totaal); %> </p>
            </div>
            <div  style="float: right">
                 <a class="buttonac" href="/delwinkelmand"> Continue To payment </a>
            </div>
        </div>
    </section>
</body>
</html>
