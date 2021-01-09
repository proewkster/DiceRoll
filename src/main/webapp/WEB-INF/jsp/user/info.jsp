<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 11/21/2020
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="../webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">
    <title>DiceRoll - Mijn Informatie</title>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<div class="container p-0">
    <div class="row">
        <div class="col-xl-6 col-12 d-flex align-items-lg-stretch order-1">
            <div class="card text-center mx-xl-1 my-1 w-100">
                <div class="card-header navbar-color pb-1">
                    <h2 class="card-title text-light">Account informatie</h2>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Email-adres: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.email}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Aangemaakt: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.created.toString()}</p>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <a href="/user/edit-accountinfo" class="btn btn-primary btn-block">Aanpassen</a>
                </div>
            </div>
        </div>
        <div class="col-xl-6 col-12 d-flex align-items-lg-stretch order-2">
            <div class="card text-center mx-xl-1 my-1 w-100">
                <div class="card-header card-header navbar-color pb-1">
                    <h2 class="card-title text-light">Persoonlijke informatie</h2>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Voornaam: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.firstName}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Achternaam: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.lastName}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Geboortedatum: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.formattedBDate}</p>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <a href="/user/edit-personalinfo" class="btn btn-primary btn-block">Aanpassen</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">

        <div class="col-xl-6 col-12 d-flex align-items-lg-stretch order-xl-4 order-3">
            <div class="card text-center mx-xl-1 my-1 w-100">
                <div class="card-header card-header navbar-color pb-1">
                    <h2 class="card-title text-light">Contactinformatie</h2>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Adres: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.streetAddress}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Postcode: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.zipCode}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Gemeente: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.city}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Telefoon: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.phoneNumber}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4 text-right">
                            <p><strong>Mobiel: </strong></p>
                        </div>
                        <div class="col-8 text-left text-muted">
                            <p>${authUser.mobileNumber}</p>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <a href="/user/edit-contactinfo" class="btn btn-primary btn-block">Aanpassen</a>
                </div>
            </div>
        </div>

        <div class="col-xl-6 col-12 d-flex align-items-lg-stretch order-xl-3 order-4 justify-content-center">
            <div class="row justify-content-center">
                <div class="col-8 offset-2 w-100 m-2 p-3">
                    <div class="border border-dark w-100 p-3 rounded text-center">
                        <i class="fa fa-download fa-4x mx-auto"></i>
                        <a href="/user/download" class="btn btn-primary btn-block">Gegevens downloaden</a>
                    </div>
                </div>
                <div class="col-8 offset-2 w-100 m-2 p-3 rounded">
                    <div class="border border-danger w-100 p-3 text-center">
                        <i class="fa fa-trash-o fa-4x text-danger"></i>
                        <button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#warningModal" data-backdrop="static">Gegevens verwijderen</button>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Modal dialogs -->
    <div class="modal fade" role="dialog" id="warningModal" tabindex="-1" aria-labelledby="warningModal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title text-danger">Waarschuwing</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <p class="text-center">U wordt uitgelogd en Uw account wordt verwijderd.</p>
                    <p class="text-center">Al Uw gegevens gaan verloren!</p>
                    <p class="text-center">Dit is onomkeerbaar.</p>
                    <p class="mt-5 text-center text-danger"><strong>Weet U zeker dat U Uw gegevens wil verwijderen?</strong></p>

                    <form:form method="post" action="/user/delete-account" modelAttribute="userDeleteAccountDTO">
                        <form:input type="text" cssClass="form-control" id="password" path="password" placeholder="Geef Uw wachtwoord in om te bevestigen"/>

                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">Verwijderen</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Annuleren</button>
                </div>
                </form:form>
            </div>
        </div>
    </div>

</div>

<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>

</body>
</html>