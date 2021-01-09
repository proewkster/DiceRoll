<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 15/11/2020
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddGame</title>
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/style.css">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body style="background: white">
<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="row justify-content-center">
    <div class="col text-center">
        <h1 class="display-5 bg-dark text-light py-1">Add Game</h1>
    </div>
</div>
<%--@elvariable id="GameDTO" type="be.thomasmore.graduaten.diceroll.objects.GameDTO"--%>
<div class="container-md" >
<form:form method="post" action="addGame" modelAttribute="game">
    <div class="row">

        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="title">
                <div class="form-row py-1">
                    <label>Title:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="title" id="title"/>
                    <form:errors path="title" cssClass="text-danger"/>
                </div>
            </spring:bind>


            <spring:bind path="minPlayers">
                <div class="form-row py-1">
                    <label>Minplayers:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="minPlayers" id="minplayers"/>
                    <form:errors path="minPlayers" cssClass="text-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="maxPlayers">
                <div class="form-row py-1">
                    <label>Maxplayers:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="maxPlayers" id="maxPlayers"/>
                    <form:errors path="maxPlayers" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="minTime">
                <div class="form-row py-1">
                    <label>Mintime:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="minTime" id="minTime"/>
                    <form:errors path="minTime" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="maxtime">
                <div class="form-row py-1">
                    <label>Maxtime:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="maxtime" id="maxtime"/>
                    <form:errors path="maxtime" cssClass="text-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="avgTime">
                <div class="form-row py-1">
                    <label>AvgTime:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="avgTime" id="avgTime"/>
                    <form:errors path="avgTime" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="year">
                <div class="form-row py-1">
                    <label>Year:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="year" id="year"/>
                    <form:errors path="year" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="rating">
                <div class="form-row py-1">
                    <label>Rating:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="rating" id="rating"/>
                    <form:errors path="rating" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="age">
                <div class="form-row py-1">
                    <label>Age:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="age" id="age"/>
                    <form:errors path="age" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="category">
                <div class="form-row py-1">
                    <label>Category:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="category" id="category"/>
                    <form:errors path="category" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="designer">
                <div class="form-row py-1">
                    <label>Designer:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="designer" id="designer"/>
                    <form:errors path="designer" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="distributer">
                <div class="form-row py-1">
                    <label>Distributer:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="distributer" id="distributer"/>
                    <form:errors path="distributer" cssClass="text-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="numVotes">
                <div class="form-row py-1">
                    <label>Numvotes:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="numVotes" id="numVotes"/>
                    <form:errors path="numVotes" cssClass="text-danger"/>
                </div>
            </spring:bind>

            <spring:bind path="imgURL">
                <div class="form-row py-1">
                    <label>ImgURL:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="imgURL" id="imgURL"/>
                    <form:errors path="imgURL" cssClass="text-danger"/>
                    <img class="card-img" src="${game.imgURL}" onerror="this.src='images/no-image-available-icon-13.jpg'">
                </div>
            </spring:bind>

        </div>

        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="price_Sale">
                <div class="form-row py-1">
                    <label>Price_Sale:</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="price_Sale" id="price_Sale"/>
                    <form:errors path="price_Sale" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="price_Rent">
                <div class="form-row py-1">
                    <label>Price_Rent</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="price_Rent" id="price_Rent"/>
                    <form:errors path="price_Rent" cssClass="text-danger"/>
                </div>
            </spring:bind>

        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="stock_Sale">
                <div class="form-row py-1">
                    <label>Stock_Sale</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="stock_Sale" id="stock_Sale"/>
                    <form:errors path="stock_Sale" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="stock_Rent">
                <div class="form-row py-1">
                    <label>Stock_Rent</label>
                    <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="stock_Rent" id="stock_Rent"/>
                    <form:errors path="stock_Rent" cssClass="text-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="col-6">
            <div class="form-row py-1">
                <button class="btn-dark" type="submit">submit</button>
            </div>
        </div>
            <div class="col-6">
                <div class="form-row py-1">
                    <a class="btn-dark" style="padding: 5px;text-decoration: none;float: right" href="/game">Terug naar overzicht</a>
                </div>
            </div>

</form:form>
</div>
    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>
