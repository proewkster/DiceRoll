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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">

    <title>DiceRoll - Admin - Users</title>
</head>
<body>

<jsp:include page="../navbar.jsp">
    <jsp:param name="userFirstName" value="${authUser.firstName}"/>
</jsp:include>

<c:if test="${not empty validationError}">
    <div class="row">
        <div class="col-12 text-center">
            <div id="error" class="alert alert-danger" role="alert">
                ${validationError}
            </div>
        </div>
    </div>
</c:if>

<c:if test="${not empty validationMessage}">
    <div class="row">
        <div class="col-12 text-center">
            <div id="validation" class="alert alert-success" role="alert">
                    ${validationMessage}
            </div>
        </div>
    </div>
</c:if>

<div class="row">
    <div class="col-12 p-4 mt-0">
        <table class="table table-striped table-bordered table-advance table-hover table-responsive-xl">
            <thead class="thead-dark">
            <tr>
                <th style="width: 15%">ID</th>
                <th style="width: 25%">Emailadres</th>
                <th style="width: 25%">Voornaam</th>
                <th style="width: 25%">Achternaam</th>
                <th style="width: 10%; min-width: 100px">Acties</th>
            </tr>
            <tr>
                <td><input type="search" id="searchId" class="form-control m-1" placeholder="zoeken..." oninput="search()" /></td>
                <td><input type="search" id="searchEmail" class="form-control m-1" placeholder="zoeken..." oninput="search()" /></td>
                <td><input type="search" id="searchFirstName" class="form-control m-1" placeholder="zoeken..." oninput="search()" /></td>
                <td><input type="search" id="searchLastName" class="form-control m-1" placeholder="zoeken..." oninput="search()" /></td>
            </tr>
            </thead>

            <tbody id="userTableBody">
                <c:forEach var="user" items="${displayUsers}">
                    <tr>
                        <td><c:out value="${user.userId}" /></td>
                        <td><c:out value="${user.email}" /></td>
                        <td><c:out value="${user.firstName}" /></td>
                        <td><c:out value="${user.lastName}" /></td>
                        <td>
                            <button class="btn btn-primary p-1 ml-1" title="Aanpassen" onclick="openEditModal(${user.userId});"><i class="fa fa-edit"></i></button>
                            <button class="btn btn-danger p-1 ml-1" title="Verwijderen" onclick="openDeleteModal(${user.userId}, '${user.email}');"><i class="fa fa-trash-o"></i></button>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<!-- Modal dialogs -->
<div class="modal fade" role="dialog" id="editUserModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content" id="editUserModalContent">
            <!-- Dynamic Modal Content -->
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="deleteUserModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-md" role="document">
        <div class="modal-content" id="deleteUserModalContent">
            <div class="modal-header navbar-color">
                <h4 class="modal-title text-light">Gebruiker verwijderen</h4>
                <button type="button" class="close text-light" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body text-center">
                <p class="text-danger">Weet u zeker dat u de gebruiker</p>
                <p id="userEmail" class="text-danger" style="font-size: large;"></p>
                <p class="text-danger">wil verwijderen?</p>
            </div>

            <div class="modal-footer">
                <a href="#" class="btn btn-danger mx-1" id="btnRemoveUser">Verwijderen</a>
                <button type="button" class="btn buttonac mx-1" data-dismiss="modal">Annuleren</button>
            </div>
        </div>
    </div>
</div>

<script src="../webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="../webjars/bootstrap/4.5.3/js/bootstrap.min.js"></script>
<script>
    var userList = [
      <c:forEach items="${displayUsers}" var="user" varStatus="status">
        {userId: '${user.userId}',
            email: '${user.email}',
            firstName: '${user.firstName}',
            lastName: '${user.lastName}'
        }
        <c:if test="${!status.last}">
        ,
        </c:if>
        </c:forEach>
    ];

    function search() {
        var filteredData = [];

        for (var i = 0; i < userList.length; i++) {
            var searchValueUserId  = document.getElementById("searchId").value;
            var searchValueEmail = document.getElementById("searchEmail").value;
            var searchValueFirstName = document.getElementById("searchFirstName").value;
            var searchValueLastName = document.getElementById("searchLastName").value;

            if (userList[i].userId.toLowerCase().startsWith(searchValueUserId) &&
                userList[i].email.toLowerCase().includes(searchValueEmail) &&
                userList[i].firstName.toLowerCase().includes(searchValueFirstName) &&
                userList[i].lastName.toLowerCase().includes(searchValueLastName)) {
                filteredData.push(userList[i]);
            }
        }

        rebuildTable(filteredData);
    }

    function rebuildTable(list) {
        var table = document.getElementById("userTableBody")

        table.innerHTML = '';

        for (var i = 0; i < list.length; i++) {
            var row = '<tr>' +
                '<td>' + list[i].userId + '</td>' +
                '<td>' + list[i].email + '</td>' +
                '<td>' + list[i].firstName + '</td>' +
                '<td>' + list[i].lastName + '</td>' +
                '<td>' +
            '<button class="btn btn-primary p-1 ml-1" title="Aanpassen" onclick="openEditModal(' + list[i].userId + ');"><i class="fa fa-edit"></i></button>' +
            '<button class="btn btn-danger p-1 ml-1" title="Verwijderen" onclick="openDeleteModal(' + list[i].userId + ', \'' + list[i].email + '\');"><i class="fa fa-trash-o"></i></button>' +
            '</td>' +
            '</tr>';

            table.innerHTML += row;
        }
    }

    function openEditModal(id) {
        $.ajax ({
            url: "/admin/users/edit/" + id,
            success: function (response) {
                // Get partial with selected user content
                $('#editUserModalContent').html(response);

                // Show modal
                $("#editUserModal").modal("show");
            }
        });
    }

    function openDeleteModal(id, email) {
        // Add user's email address to modal content
        document.getElementById("userEmail").innerHTML = email;

        // Add correct URL to delete button
        document.getElementById("btnRemoveUser").href = "/admin/users/delete/" + id;

        // Show modal
        $("#deleteUserModal").modal();
    }
</script>

</body>
</html>
