<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.RentGameDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.SessionGameDTO" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.SoldGameDTO" %>
<%@ page import="org.springframework.ui.Model" %><%--
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
    <title> DiceRollBoardGames | Order</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="container">
<form:form method="post" action="/order" modelAttribute="saleOrder">
    <div class="form-row py-1">
    <div class="col-4">
        <label>Afhaaldatum</label>
        <form:input type="date" min="${date}" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="orderDate" id="orderDate"/>
        <form:errors path="orderDate" cssClass="text-danger"/>
    </div>
        <div class="col-4">
            <h1>Overzicht</h1>
            <h2>Verhuurde Games</h2>
            <ol>
                <% List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>)session.getAttribute("RentGameDTOS");
                    for (RentGameDTO rentGameDTO: rentGameDTOS)
                    {
                        out.print("<li>"+rentGameDTO.getTitle()+"</li>");
                    }
                %>
            </ol>
        </div>
    </div>
    <div class="form-row py-1">
        <div class="col-4">
            <button class="buttonac" type="submit">Order bevestigen</button>
        </div>
        <div class="col-4">
            <h2>Gekochte games</h2>
            <dl>
                <% List<SoldGameDTO> soldGameDTOS = (List<SoldGameDTO>) request.getAttribute("soldGameDTOS"); ;
                    for (SoldGameDTO soldGameDTO : soldGameDTOS)
                    {
                        out.print("<dt>"+soldGameDTO.getGame().getTitle()+"</dt>");
                        out.print("<dd>korting: "+soldGameDTO.getDiscountAsString()+"   prijs: "+soldGameDTO.getPricePaidAsString()+"</dd>");
                    }
                %>
            </dl>
        </div>
    </div>
</form:form>
</div>
</body>
</html>