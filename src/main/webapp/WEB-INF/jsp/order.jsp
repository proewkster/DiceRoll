<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.RentGameDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.SessionGameDTO" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.SoldGameDTO" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <title> DiceRoll - Order Bevestigen</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="container">
<form:form method="post" action="order" modelAttribute="orderDTO">
    <div class="form-row py-1 m-4">
        <div class="col-1"></div>
        <div class="col-4">
            <spring:bind path="orderDate">
                <label>Afhaaldatum</label>
                <form:input type="date" min="${date}" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="orderDate" id="orderDate"/>
                <form:errors path="orderDate" cssClass="text-danger"/>
            </spring:bind>
            <button class="buttonac" style="margin-top: 10px;margin-left: 0;" type="submit">Order bevestigen</button>
        </div>
        <div class="col-1"></div>
        <div class="col-6 border-dark">
            <h1>Overzicht</h1>
            <h2>Gekochte games</h2>
            <ol>
                <% List<SoldGameDTO> soldGameDTOS = (List<SoldGameDTO>) request.getAttribute("soldGameDTOS");
                    if (soldGameDTOS== null){soldGameDTOS=new ArrayList<SoldGameDTO>();}
                    for (SoldGameDTO soldGameDTO : soldGameDTOS)
                    {
                        out.print("<dt>"+soldGameDTO.getGame().getTitle()+"</dt>");
                        out.print("<dd>korting: "+soldGameDTO.getDiscountAsString()+"</dd>");
                        out.print("<dd>prijs: "+soldGameDTO.getPricePaidAsString()+" x  Aantal: "+soldGameDTO.getAmount()+" = "+soldGameDTO.getSubTotalAsString()+"</dd>");
                    }
                %>
            </ol>
        </div>
    </div>
    <div class="form-row py-1">
        <div class="col-6 ">
        </div>
        <div class="col-6 border-dark">
            <h2>Verhuurde Games</h2>
            <ol>
                <% List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>)session.getAttribute("RentGameDTOS");
                    if (rentGameDTOS== null){rentGameDTOS=new ArrayList<RentGameDTO>();}
                    for (RentGameDTO rentGameDTO: rentGameDTOS)
                    {
                        out.print("<dt>"+rentGameDTO.getGame().getTitle()+"</dt>");
                        out.print("<dd>korting: "+rentGameDTO.getDiscountAsString()+"</dd>");
                        out.print("<dd>prijs: "+rentGameDTO.getPricePaidAsString()+" x  Aantal: "+rentGameDTO.getAantal()+" = "+rentGameDTO.getSubTotalAsString()+"</dd>");
                    }
                %>
            </ol>
        </div>
    </div>
</form:form>
</div>
<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>