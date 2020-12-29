<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../css/style.css">

    <title>DiceRoll - Admin - Orders</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<div class="container p-3">
    <div class="row">
        <div class="col-12 text-center">
            <h5>Overzicht Verhuur</h5>
            <hr/>
        </div>
    </div>
    <div class="row">
        <!-- Filter -->
        <div class="col-12">
            <button class="btn btn-dark mb-3" type="button" data-toggle="collapse" data-target="#rentFilter" aria-expanded="false" aria-controls="rentFilter">
                Filter
            </button>
        </div>
        <div class="col-12">
            <div class="collapse mb-3" id="rentFilter">
                <div class="card card-body bg-transparent border-dark">
                    <form:form method="post" action="/admin/rent/filter" modelAttribute="rentOrderFilter" id="filterForm">
                        <form:hidden path="currentPage" value="0" id="pageNumber" />
                        <div class="row">
                            <div class="col-12 col-lg-6 px-5 py-2">
                                <table>
                                    <tr>
                                        <th scope="row" class="align-middle text-right pt-2 pr-3">
                                            <label>Order ID:</label>
                                        </td>
                                        <td class="align-middle">
                                            <form:input type="number" min="0" max="65536" cssClass="form-control" path="orderId" />
                                        </td>
                                    </tr>

                                    <tr>
                                        <th scope="row" class="align-middle text-right pt-2 pr-3">
                                            <label>Gebruiker:</label>
                                        </td>
                                        <td class="align-middle">
                                            <form:select path="userId" cssClass="form-control selectpicker" data-live-search="true" data-size="10" dropupAuto="false" data-live-search-placeholder="Zoeken..." data-live-search-style="contains">
                                                <option value="">-- Geen --</option>
                                                <c:forEach var="user" items="${applicationUsers}">
                                                    <option value="${user.userId}">${user.displayName}</option>
                                                </c:forEach>
                                            </form:select>
                                        </td>
                                    </tr>
                                </table>

                                <table class="mt-3">
                                    <tr>
                                        <th scope="row" class="align-middle text-right pt-2 pr-3">
                                            <label>Betaald:</label>
                                        </td>
                                        <td class="align-middle">
                                            <div class="btn-group btn-group-toggle btn-group-sm" data-toggle="buttons">
                                                <label class="btn btn-secondary">
                                                    <form:radiobutton path="paid" autocomplete="off" value="On"/>JA
                                                </label>
                                                <label class="btn btn-secondary active">
                                                    <form:radiobutton path="paid" autocomplete="off" value="Disabled"/>UIT
                                                </label>
                                                <label class="btn btn-secondary">
                                                    <form:radiobutton path="paid" autocomplete="off" value="Off" />NEE
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12 text-right">
                                <a href="/admin/rent" class="btn btn-danger">Filters verwijderen</a>
                                <button type="submit" class="btn btn-dark">Filter toepassen</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <!-- Data table -->
        <table class="table table-striped table-bordered table-advance table-hover table-responsive-xl">
            <thead class="thead-dark">
            <tr>
                <th style="width: 15%;">Order ID</th>
                <th style="width: 65%;">Gebruiker</th>
                <th style="width: 10%;">Betaald</th>
                <th style="width: 10%;"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="rentOrder" items="${displayRentOrders}">
                <tr class="align-middle">
                    <td class="align-middle"><c:out value="${rentOrder.rentOrderId}" /></td>
                    <td class="align-middle"><c:out value="${rentOrder.userSummary}" /></td>
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

    <!-- Pagination tools -->
    <div class="row">
        <div class="col-12">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center pagination-sm">
                    <c:set var="enabledprev" value="${rentOrderFilter.currentPage == 0 ? 'disabled' : ''}" />
                    <button class="btn btn-secondary btn-sm" type="button" onclick="submitWithPageNumber(0)" ${enabledprev}><i class="fa fa-fast-backward"></i></button>
                    <button class="btn btn-secondary btn-sm" type="button" onclick="submitWithPageNumber(${rentOrderFilter.currentPage - 1})" ${enabledprev}><i class="fa fa-step-backward"></i></button>
                    <c:set var="active" value=""/>
                    <c:choose>
                        <c:when test="${rentOrderFilter.totalPages <= 1}">
                            <button class="btn btn-primary btn-sm" type="button" onclick="">1</button>
                        </c:when>
                        <c:when test="${rentOrderFilter.totalPages <= 10}">
                            <c:forEach var = "i" begin = "0" end = "${rentOrderFilter.totalPages - 1}">
                                <c:set var="active" value="${rentOrderFilter.currentPage == i ? 'btn-primary' : 'btn-secondary'}"/>
                                <button class="btn ${active} btn-sm" type="button" onclick="submitWithPageNumber(${i})">${i+1}</button>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${rentOrderFilter.currentPage <= 5}">
                                    <c:forEach var = "i" begin = "0" end = "9">
                                        <c:set var="active" value="${rentOrderFilter.currentPage == i ? 'btn-primary' : 'btn-secondary'}"/>
                                        <button class="btn ${active} btn-sm" type="button" onclick="submitWithPageNumber(${i})">${i+1}</button>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${rentOrderFilter.currentPage > 5 && rentOrderFilter.currentPage < rentOrderFilter.totalPages - 5}">
                                    <c:forEach var = "i" begin = "${rentOrderFilter.currentPage - 5}" end = "${rentOrderFilter.currentPage + 4}">
                                        <c:set var="active" value="${rentOrderFilter.currentPage == i ? 'btn-primary' : 'btn-secondary'}"/>
                                        <button class="btn ${active} btn-sm" type="button" onclick="submitWithPageNumber(${i})">${i+1}</button>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${rentOrderFilter.currentPage >= rentOrderFilter.totalPages - 5}">
                                    <c:forEach var = "i" begin = "${rentOrderFilter.totalPages - 11}" end = "${rentOrderFilter.totalPages - 1}">
                                        <c:set var="active" value="${rentOrderFilter.currentPage == i ? 'btn-primary' : 'btn-secondary'}"/>
                                        <button class="btn ${active} btn-sm" type="button" onclick="submitWithPageNumber(${i})">${i+1}</button>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                    <c:set var="enablednext" value="${rentOrderFilter.currentPage >= rentOrderFilter.totalPages - 1 ? 'disabled' : ''}" />
                    <button class="btn btn-secondary btn-sm" type="button" onclick="submitWithPageNumber(${rentOrderFilter.currentPage + 1})" ${enablednext} ><i class="fa fa-step-forward"></i></button>
                    <button class="btn btn-secondary btn-sm" type="button" onclick="submitWithPageNumber(${rentOrderFilter.totalPages - 1})" ${enablednext} ><i class="fa fa-fast-forward"></i></button>
                </ul>
            </nav>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<script>
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

    function submitWithPageNumber(page) {
        $('#pageNumber').val(page);
        $('#filterForm').submit();
    }
</script>

</body>
</html>

