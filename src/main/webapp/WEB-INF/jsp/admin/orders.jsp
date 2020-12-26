<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 13/12/2020
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="../webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">

    <title>DiceRoll - Admin - Orders</title>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<div class="container p-3">
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item" role="presentation">
            <a class="nav-link active" data-toggle="tab" href="#verkoop" role="tab" aria-controls="verkoop" aria-selected="true">Verkoop</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" data-toggle="tab" href="#verhuur" role="tab" aria-controls="verhuur" aria-selected="false">Verhuur</a>
        </li>
    </ul>

    <div class="tab-content p-3">
        <div id="verkoop" class="tab-pane fade show active" role="tabpanel">
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
                <c:forEach var="saleOrder" items="${displaySaleOrders}">
                    <tr class="align-middle">
                        <td class="align-middle"><c:out value="${saleOrder.saleOrderId}" /></td>
                        <td class="align-middle"><c:out value="${saleOrder.orderDate}" /></td>
                        <td class="align-middle"><c:out value="${saleOrder.userSummary}" /></td>
                        <td class="text-center align-middle">
                            <c:choose>
                                <c:when test="${saleOrder.paid==true}">
                                    <input type="checkbox" checked onclick="return false;" />
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="return false;" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle">
                            <c:choose>
                                <c:when test="${saleOrder.delivered==true}">
                                    <input type="checkbox" checked onclick="return false;" />
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="return false;" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle">
                            <button class="btn btn-primary p-1" title="Details" onclick="openSaleOrderDetails(${saleOrder.saleOrderId});"><i class="fa fa-th-list"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
        <div id="verhuur" class="tab-pane fade" role="tabpanel">
            <jsp:include page="./rentOrdersPartial.jsp"></jsp:include>
        </div>
    </div>
</div>

<!-- Modal Dialogs -->
<div class="modal fade" role="dialog" id="saleOrderDetailsModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content" id="saleOrderDetailsContent">
            <!-- Dynamic Modal Content -->
        </div>
    </div>
</div>

<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
<script>
    function openSaleOrderDetails(id) {
        $.ajax ({
           url: "/admin/orders/sale/" + id,
           success: function (response) {
               // Get partial with order details
               $('#saleOrderDetailsContent').html(response);

               // Show modal
               $("#saleOrderDetailsModal").modal("show");
           }
        });
    }
</script>

</body>
</html>
