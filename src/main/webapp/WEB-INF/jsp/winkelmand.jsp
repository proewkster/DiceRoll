<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.SessionGameDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.RentGameDTO" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.SessionGameDTO" %><%--
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
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>

</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

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
                <%List<SessionGameDTO> testen = (List<SessionGameDTO>) session.getAttribute("test");
                if (testen == null){testen = new ArrayList<SessionGameDTO>();}
                    int i = 1;
                    int id = 0;
                    double totaal1 = 0;
                    for (SessionGameDTO test:testen)
                    {
                        out.print("<tr>");
                        out.print("<th scope=row>"+i+"</th>");
                        out.print("<td> "+test.getTitle() +"</td>");
                        out.print("<td> "+test.getAmount() +"</td>");
                        out.print("<td> €"+test.getAmount() * test.getPrice() +"</td>");
                        out.print("<td><a class=\"buttonplus\" href=/additemwinkelmand?id="+id+"&buy=1>");%><i class="fa fa-plus-square"></i><%out.print("</a></td>");
                        out.print("<td><a class=\"buttonplus\" href=/minitemwinkelmand?id="+id+"&buy=1>");%><i class="fa fa-minus-square"></i><%out.print("</a></td>");
                        out.print("<td><a class=\"buttonplus\" href=/delitemwinkelmand?id="+id+"&buy=1>");%><i class="fa fa-trash-o"></i><%out.print("</a></td>");
                        totaal1 += test.getAmount()*test.getPrice();
                        out.print("</tr>");
                        i++;
                        id++;
                    }
                %>
                </tbody>
            </table>
            <h1 class="text-center">Gehuurde games</h1>
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
            <%List<RentGameDTO> rentGameDTOS  = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
                if (rentGameDTOS == null){rentGameDTOS = new ArrayList<RentGameDTO>();}
                int j = 1;
                int rentgameid = 0;
                double totaal2 = 0;
                for (RentGameDTO rentGameDTO:rentGameDTOS)
                {
                    out.print("<tr>");
                    out.print("<th scope=row>"+j+"</th>");
                    out.print("<td> "+rentGameDTO.getTitle() +"</td>");
                    out.print("<td>"+rentGameDTO.getAantal()+"</td>");
                    out.print("<td> €"+rentGameDTO.getPrice()*rentGameDTO.getAantal() +"</td>");
                    out.print("<td><a class=\"buttonplus\" href=/additemwinkelmand?id="+rentgameid+">");%><i class="fa fa-plus-square"></i><%out.print("</a></td>");
                    out.print("<td><a class=\"buttonplus\" href=/minitemwinkelmand?id="+rentgameid+">");%><i class="fa fa-minus-square"></i><%out.print("</a></td>");
                    out.print("<td><a class=\"buttonplus\" href=/delitemwinkelmand?id="+rentgameid+">");%><i class="fa fa-trash-o"></i><%out.print("</a></td>");
                    totaal2 += rentGameDTO.getPrice()*rentGameDTO.getAantal();
                    out.print("</tr>");
                    j++;
                    rentgameid++;
                }
            %>
            </tbody>
        </table>
            <div class="col-md-12 text-right blockquote" >
                <p>Totaal: € <% double totaal = totaal1 + totaal2; out.print(totaal); %> </p>
            </div>
            <div  style="float: right">
                 <a class="buttonac" href="/order"> Order bevestigen </a>
                <p class="text-danger">
                    <% String noNull = (String)request.getAttribute("noNull");
                        if(noNull!=null){out.print(noNull);}%>
                </p>
            </div>
        </div>

    </section>
<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>
