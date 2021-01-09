<%@ page import="org.springframework.web.bind.annotation.ModelAttribute" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.RentOrder" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.RentedGame" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.SaleOrder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <title> DiceRoll - Bevestigd</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="container">
    <p class="col-md-12 text-center" style="font-size: 17px;"> Uw order is correct behandeld. </p>
    <div class="col-md-12 text-center">
        <p><%
            SaleOrder saleOrder = (SaleOrder) request.getAttribute("saleOrder");
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            out.print("<p>Uw Bestelling kan worden afgehaald op "+simpleDateFormat.format(saleOrder.getOrderDate())+"</p>");
            RentOrder rentOrder =(RentOrder) request.getAttribute("rentOrder");
            List<RentedGame> rentedGames = (List<RentedGame>)request.getAttribute("rentedGames");
            if (rentedGames.size()!=0) {
                RentedGame rentedDateGame = rentedGames.get(0);
                Date date = rentedDateGame.getEndDate();
                out.print("Volgende gehuurde spellen moeten terug gebracht worden op " + simpleDateFormat.format(date) + "<br>");
                for (RentedGame rentedGame : rentedGames) {

                    out.print(rentedGame.getGame().getTitle());
                    out.print("<br>");
                }
            }
        %>

        </p>

        <a href="/" class="buttonac">Continue shopping</a>
    </div>
</div>
<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>
