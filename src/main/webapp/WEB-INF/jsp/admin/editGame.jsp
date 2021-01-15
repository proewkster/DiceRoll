<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="be.thomasmore.graduaten.diceroll.entity.Game" %>
<%@ page import="be.thomasmore.graduaten.diceroll.objects.GameDTO" %><%--
  Created by IntelliJ IDEA.
  User: Koen Van Looy
  Date: 15/11/2020
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DiceRoll - Game Wijzigen</title>
    <link href="../webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body style="background: white">
<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
<div class="row justify-content-center">
    <div class="col text-center">
        <h1 class="display-5 bg-dark text-light py-1">Wijzig Game</h1>
    </div>
</div>
<div class="container-md">
<%--@elvariable id="gameDTO" type=""--%>
<form:form method="post" action="/admin/editGame" modelAttribute="game">
<div class="row">
    <div class="col col-md-6 col-12 text-center">
        <spring:bind path="gameID">
            <div class="form-row py-1">
                <label>Gameid:</label>
                <form:input readonly="true" type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="gameID" id="gameID"/>
                <form:errors path="gameID" cssClass="text-danger"/>
            </div>
        </spring:bind>
    </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="title">
                <div class="form-row py-1">
                    <label>Titel:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="title" id="title"/>
                    <form:errors path="title" cssClass="text-danger"/>
                </div>
            </spring:bind>

        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="maxPlayers">
                <div class="form-row py-1">
                    <label>Max spelers:</label>
                    <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="maxPlayers" id="maxPlayers"/>
                    <form:errors path="maxPlayers" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="minTime">
                <div class="form-row py-1">
                    <div class="col-12 text-left">
                        <label>Min tijd:</label>
                    </div>
                    <div class="col-6 px-0">
                        <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="minTime" id="minTime"/>
                        <form:errors path="minTime" cssClass="text-danger"/>
                    </div>
                    <div class="col-6 py-2 text-left"><p>Minuten</p></div>
                </div>
            </spring:bind>
        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="minPlayers">
                <div class="form-row py-1">
                    <label>Min spelers:</label>
                    <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="minPlayers" id="minplayers"/>
                    <form:errors path="minPlayers" cssClass="text-danger"/>
                </div>
            </spring:bind>

            <spring:bind path="maxtime">
                <div class="form-row py-1">
                    <div class="col-12 text-left">
                        <label>Max tijd:</label>
                    </div>
                    <div class="col-6 px-0">
                        <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="maxtime" id="maxtime"/>
                        <form:errors path="maxtime" cssClass="text-danger"/>
                    </div>
                    <div class="col-6 py-2 text-left"><p>Minuten</p></div>
                </div>
            </spring:bind>
        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="avgTime">
                <div class="form-row py-1">
                    <div class="col-12 text-left">
                        <label>gem tijd:</label>
                    </div>
                    <div class="col-6 px-0">
                        <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="avgTime" id="avgTime"/>
                        <form:errors path="avgTime" cssClass="text-danger"/>
                    </div>
                    <div class="col-6 py-2 text-left"><p>Minuten</p></div>
                </div>
            </spring:bind>
            <spring:bind path="year">
                <div class="form-row py-1">
                    <label>Jaar:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="year" id="year"/>
                    <form:errors path="year" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="rating">
                <div class="form-row py-1">
                    <div class="col-12 text-left">
                        <label for="rating">Score:</label>
                    </div>
                    <div class="col-6 px-0">
                        <form:input type="number" max="10" min="0" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="rating" id="rating"/>
                        <form:errors path="rating" cssClass="text-danger"/>
                    </div>
                    <div class="col-6 text-left py-2"><p> /10</p></div>
                </div>
            </spring:bind>
            <spring:bind path="category">
                <div class="form-row py-1">
                    <label>Categorie:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="category" id="category"/>
                    <form:errors path="category" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="designer">
                <div class="form-row py-1">
                    <label>Uitvinder:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="designer" id="designer"/>
                    <form:errors path="designer" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="distributer">
                <div class="form-row py-1">
                    <label>Distributeur:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="distributer" id="distributer"/>
                    <form:errors path="distributer" cssClass="text-danger"/>
                </div>
            </spring:bind>
        </div>
        <div class="col col-md-6 col-12 text-center">

            <spring:bind path="imgURL">
                <div class="form-row py-1">
                    <label>ImgURL:</label>
                    <form:input type="text" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="imgURL" id="imgURL"/>
                    <form:errors path="imgURL" cssClass="text-danger"/>
                    <img class="card-img" src="${game.imgURL}" onerror="this.src='../images/no-image-available-icon-13.jpg'">
                </div>
            </spring:bind>

        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="numVotes">
                <div class="form-row py-1">
                    <label>Aantal stemmen:</label>
                    <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="numVotes" id="numVotes"/>
                    <form:errors path="numVotes" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="price_Sale">
                <div class="form-row py-1">
                    <div class="col-12 text-left">
                        <label>Verkoopprijs:</label>
                    </div>
                    <div class="col-6 px-0">
                        <form:input type="number"  cssClass="form-control ${status.error ? 'is-invalid' : ''}" min="0" max="1000" step=".01" path="price_Sale" id="price_Sale"/>
                        <form:errors path="price_Sale" cssClass="text-danger"/>
                    </div>
                    <div class="col-6 text-left py-2"><p>€</p></div>
                </div>
            </spring:bind>
            <spring:bind path="stock_Sale">
                <div class="form-row py-1">
                    <label>VerkoopStock</label>
                    <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="stock_Sale" id="stock_Sale"/>
                    <form:errors path="stock_Sale" cssClass="text-danger"/>
                </div>
            </spring:bind>

        </div>
        <div class="col col-md-6 col-12 text-center">
            <spring:bind path="age">
                <div class="form-row py-1">
                    <label>Leeftijd:</label>
                    <form:input type="number" min="3" max="18" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="age" id="age"/>
                    <form:errors path="age" cssClass="text-danger"/>
                </div>
            </spring:bind>
            <spring:bind path="price_Rent">
                <div class="form-row py-1">
                    <div class="col-12 text-left">
                        <label>Verhuurprijs</label>
                    </div>
                    <div class="col-6 px-0">
                        <form:input type="number" cssClass="form-control ${status.error ? 'is-invalid' : ''}" min="0" max="1000" step=".01" path="price_Rent" id="price_Rent"/>
                        <form:errors path="price_Rent" cssClass="text-danger"/>
                    </div>
                    <div class="col-6 text-left py-2"><p>€</p></div>
                </div>
            </spring:bind>
            <spring:bind path="stock_Rent">
                <div class="form-row py-1">
                    <label>VerhuurStock</label>
                    <form:input type="number" min="0" max="1000" cssClass="form-control ${status.error ? 'is-invalid' : ''}" path="stock_Rent" id="stock_Rent"/>
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
                <a class="btn-dark" style="padding: 5px;text-decoration: none;float: right" href="/admin/game">Terug naar overzicht</a>
            </div>
        </div>
</form:form>
</div>
<script type="text/javascript" src="http://livejs.com/live.js"></script>
<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>
