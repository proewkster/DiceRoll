<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 01/01/2021
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <link href="../webjars/bootstrap/4.5.3/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">

    <title>DiceRoll - Mijn orders</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<div class="container">
    <nav class="nav nav-tabs mt-3" role="tablist">
        <a class="nav-item nav-link active text-dark" href="#saleOverview" data-toggle="tab" role="tab" aria-controls="saleOverview">Overzicht aankopen</a>
        <a class="nav-item nav-link text-dark" href="#rentOverview" data-toggle="tab" role="tab" aria-controls="rentOverview">Overzicht huur</a>
    </nav>

    <div class="tab-content">
        <div class="tab-pane fade show active p-3" id="saleOverview" role="tabpanel" aria-labelledby="saleOverview">
            <table class="table table-striped table-bordered table-advance table-hover table-responsive-xl">
                <thead class="thead-dark">
                <tr>
                    <th style="width: 25%;">Order ID</th>
                    <th style="width: 30%;">Datum</th>
                    <th style="width: 15%;">Betaald</th>
                    <th style="width: 15%;">Geleverd</th>
                    <th style="width: 15%;"></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="saleOrder" items="${saleOrders}">
                    <tr class="align-middle">
                        <td class="align-middle"><c:out value="${saleOrder.saleOrderId}" /></td>
                        <td class="align-middle"><c:out value="${saleOrder.orderDate}" /></td>
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

        <div class="tab-pane fade p-3" id="rentOverview" role="tabpanel" aria-labelledby="rentOverview">
            <table class="table table-striped table-bordered table-advance table-hover table-responsive-xl">
                <thead class="thead-dark">
                <tr>
                    <th style="width: 35%;">Order ID</th>
                    <th style="width: 35%;">Betaald</th>
                    <th style="width: 30%;"></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="rentOrder" items="${rentOrders}">
                    <tr class="align-middle">
                        <td class="align-middle"><c:out value="${rentOrder.rentOrderId}" /></td>
                        <td class="text-center align-middle">
                            <c:choose>
                                <c:when test="${rentOrder.paid==true}">
                                    <input type="checkbox" checked onclick="return false;" />
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="return false;" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="text-center align-middle">
                            <button class="btn btn-primary p-1" title="Details" onclick="openRentOrderDetails(${rentOrder.rentOrderId});"><i class="fa fa-th-list"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
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

<div class="modal fade" role="dialog" id="rentOrderDetailsModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content" id="rentOrderDetailsContent">
            <!-- Dynamic Modal Content -->
        </div>
    </div>
</div>

<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
<script>
    function openSaleOrderDetails(id) {
        $.ajax ({
            url: "/admin/sale/" + id,
            success: function (response) {
                // Get partial with order details
                $('#saleOrderDetailsContent').html(response);

                // Show modal
                $("#saleOrderDetailsModal").modal("show");
            }
        });
    }

    function openRentOrderDetails(id) {
        $.ajax ({
            url: "/admin/rent/" + id,
            success: function (response) {
                // Get partial with order details
                $('#rentOrderDetailsContent').html(response);

                // Show modal
                $("#rentOrderDetailsModal").modal("show");
            }
        });
    }
</script>

</body>
</html>