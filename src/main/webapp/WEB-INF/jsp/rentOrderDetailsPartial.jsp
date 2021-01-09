<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-header navbar-color">
    <h2 class="modal-title text-light">Details bestelling</h2>
    <button type="button" class="close text-light" data-dismiss="modal">&times;</button>
</div>

<div class="modal-body">
    <div class="row">
        <div class="col-12">
            <h4 class="highlight">Bestellingsgegevens</h4>
        </div>
        <div class="col-12 col-lg-6">
            <h5 class="d-inline">Bestellingsnummer:</h5>
            <p class="d-inline">${displayModel.rentOrderID}</p>
        </div>
        <div class="col-12 col-lg-6 mt-3 mt-lg-0">
            <h5 class="d-inline">Betaald: </h5>
            <c:choose>
                <c:when test="${displayModel.paid==true}">
                    <input type="checkbox" class="d-inline" checked onclick="return false;" />
                </c:when>
                <c:otherwise>
                    <input type="checkbox" class="d-inline" onclick="return false;" />
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <hr/>

    <div class="row">
        <div class="col-12">
            <h4 class="highlight">Gebruikersgegevens</h4>
        </div>
        <div class="col-12 col-lg-6">
            <h5>Persoonlijke gegevens:</h5>
            <p class="my-1 ml-3">Gebruikers-ID: ${displayModel.user.userId}</p>
            <p class="my-1 ml-3">Emailadres: ${displayModel.user.email}</p>
            <p class="my-1 ml-3">Voornaam: ${displayModel.user.firstName}</p>
            <p class="my-1 ml-3">Achternaam: ${displayModel.user.lastName}</p>
        </div>
        <div class="col-12 col-lg-6">
            <h5>Adresgegevens:</h5>
            <p class="my-1 ml-3">Straat: ${displayModel.user.streetAddress}</p>
            <p class="my-1 ml-3">Postcode: ${displayModel.user.zipCode}</p>
            <p class="my-1 ml-3">Gemeente: ${displayModel.user.city}</p>
        </div>
    </div>

    <hr/>

    <div class="row">
        <table class="table table-striped table-hover table-responsive-xl mx-3">
            <thead>
            <tr class="text-center">
                <th style="width: 5%;">ID</th>
                <th style="width: 15%;">Naam</th>
                <th style="width: 15%;">Start</th>
                <th style="width: 15%;">Eind</th>
                <th style="width: 5%;"></th>
                <th style="width: 5%;"></th>
                <th style="width: 15%;">Prijs</th>
                <th style="width: 10%;">Korting</th>
                <th style="width: 15%;">Totaal</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="rentedGame" items="${displayModel.rentedGames}">
                <tr>
                    <td><c:out value="${rentedGame.game.gameID}" /></td>
                    <td><c:out value="${rentedGame.game.title}" /></td>
                    <td><c:out value="${rentedGame.startDateAsString}" /></td>
                    <td><c:out value="${rentedGame.endDateAsString}" /></td>
                    <td class="text-center align-middle">
                        <c:choose>
                            <c:when test="${rentedGame.delivered==true}">
                                <input type="checkbox" checked onclick="return false;" title="Geleverd" />
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" onclick="return false;" title="Geleverd" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center align-middle">
                        <c:choose>
                            <c:when test="${rentedGame.returned==true}">
                                <input type="checkbox" checked onclick="return false;" title="Teruggebracht" />
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" onclick="return false;" title="Teruggebracht" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center"><c:out value="${rentedGame.pricePaidAsString}" /></td>
                    <td class="text-center"><c:out value="${rentedGame.discountAsString}" /></td>
                    <td class="text-right"><strong><c:out value="${rentedGame.subTotalAsString}" /></strong></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

    <div class="row mt-4">
        <div class="col-12 text-right">
            <h5 class="d-inline">Totaal incl. BTW: </h5>
            <p class="d-inline ml-3"><strong>${displayModel.totalAsString}</strong></p>
        </div>
    </div>
</div>

<div class="modal-footer">
    <button type="button" class="btn btn-primary mx-1" data-dismiss="modal">Sluiten</button>
</div>
