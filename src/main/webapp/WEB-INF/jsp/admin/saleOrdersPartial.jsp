<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-striped table-bordered table-advance table-hover table-responsive-xl">
    <thead class="thead-dark">
        <tr>
            <th style="width: 15%;">Order ID</th>
            <th style="width: 20%;">Datum</th>
            <th style="width: 50%;">Gebruiker</th>
            <th style="width: 5%;">Betaald</th>
            <th style="width: 5%;">Geleverd</th>
            <th style="width: 5%;"></th>
        </tr>
    </thead>

    <tbody>
        <jsp:useBean id="displaySaleOrders" scope="request" type="java.util.List"/>
        <c:forEach var="saleOrder" items="${displaySaleOrders}">
            <tr>
                <td><c:out value="${saleOrder.saleOrderId}"></c:out></td>
                <td><c:out value="${saleOrder.orderDate}"></c:out></td>
                <td><c:out value="${saleOrder.userSummary}"></c:out></td>
                <td><c:out value="${saleOrder.paid}"></c:out></td>
                <td><c:out value="${saleOrder.delivered}"></c:out></td>
            </tr>
        </c:forEach>
    </tbody>

</table>