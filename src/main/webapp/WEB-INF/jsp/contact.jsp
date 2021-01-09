<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: r0444972
  Date: 24/10/2020
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>Diceroll - Contact</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>
    <main class="container">
        <div class="col-lg-6 mx-auto">
        <h1>Contact</h1>
        <p>Hallo en welkom bij Diceroll</p>
        <p>Heb je een vraag of een opmerking? Neem dan contact met ons op via info@Diceroll.be</p>
        <p>Klachten? Contacteer ons op Joeri.Verlooy@ThomasMore.be</p>
        <br>
            <p>Je kan ook het onderstaande formulier invullen wat dan doorgestuurd zal worden.</p>
        </div>
        <br>
        <form >
                <div class="form-group col-lg-6 mx-auto">
                    <label for="inputEmail">Email</label>
                    <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                </div>
            <div class="form-group col-lg-6 mx-auto">
                <label for="inputName">Uw naam</label>
                <input type="text" class="form-control" id="inputName" placeholder="Vul hier jou volledige naam in">
            </div>
            <div class="form-group col-lg-6 mx-auto">
                <label for="message">Boodschap aan team Diceroll</label>
                <textarea type="text" class="form-control" id="message" placeholder="jou bericht" rows="5"></textarea>
            </div>
            <div class="form-group col-lg-6 mx-auto">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="gridCheck">
                    <label class="form-check-label" for="gridCheck">
                       Ik ga akkoord dat mijn data verwerkt zal worden.
                    </label>
                </div>
            </div>
            <div class="form-group col-lg-6 mx-auto">
            <button type="submit" class="btn btn-primary">Verzenden</button>
            </div>
        </form>
    </main>

<script src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
</body>
</html>
