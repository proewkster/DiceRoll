<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="modal-header navbar-color">
    <h4 class="modal-title text-light">Gebruiker aanpassen</h4>
    <button type="button" class="close text-light" data-dismiss="modal">&times;</button>
</div>

<form:form method="post" action="/admin/users/edit" modelAttribute="selectedUser">

<div class="model-body p-4">
        <div class="row">
            <div class="col-6">
                <h4 class="text-center mb-3 highlight">Gebruikersgegevens</h4>
                <hr/>
                <table>
                <tbody>
                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">ID:</label></td>
                        <td><form:input type="text" cssClass="form-control" path="userId" id="userId" readonly="true" /></td>
                    </tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Created on:</label></td>
                        <td><form:input type="text" cssClass="form-control" path="created" id="created" readonly="true"/></td>
                    </tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Emailadres:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="email" id="email" /></td>
                    </tr>
                    <tr><form:errors path="email" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Voornaam:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="firstName" id="firstName" /></td>
                    </tr>
                    <tr><form:errors path="firstName" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Achternaam:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="lastName" id="lastName"/></td>
                    </tr>
                    <tr><form:errors path="lastName" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Geboortedatum:</label></td>
                        <td><form:input type="date" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="birthDate" id="birthDate"/></td>
                    </tr>
                    <tr><form:errors path="birthDate" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Adres:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="streetAddress" id="streetAddress" /></td>
                    </tr>
                    <tr><form:errors path="streetAddress" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">PostCode:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="zipCode" id="zipCode" /></td>
                    </tr>
                    <tr><form:errors path="zipCode" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Gemeente:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="city" id="city" /></td>
                    </tr>
                    <tr><form:errors path="city" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Telefoonnummer:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="phoneNumber" id="phoneNumber" /></td>
                    </tr>
                    <tr><form:errors path="phoneNumber" cssClass="text-danger" /></tr>

                    <tr>
                        <td class="text-right pr-2"><label class="pt-2">Mobiel nummer:</label></td>
                        <td><form:input type="text" cssClass="form-control ${status.errors ? 'is-invalid' : ''}" path="mobileNumber" id="mobileNumber" /></td>
                    </tr>
                    <tr><form:errors path="mobileNumber" cssClass="text-danger" /></tr>
                </tbody>
            </table>
            </div>

            <div class="col-6">
                <h4 class="text-center mb-3 highlight">Gebruikersrollen</h4>
                <hr/>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <form:checkbox path="userRole" cssClass="checkbox" id="isUserToggle" />
                        </div>
                    </div>
                    <input type="text" class="form-control" disabled value="User" readonly>
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text">
                            <form:checkbox path="adminRole" cssClass="checkbox" id="isAdminToggle"/>
                        </div>
                    </div>
                    <input type="text" class="form-control" disabled value="Admin" readonly>
                </div>

                <h4 class="text-center mb-3 mt-5 highlight">Status</h4>
                <hr/>

                <div class="custom-control custom-switch text-center">
                    <form:checkbox cssClass="custom-control-input" id="userEnableToggle" path="enabled"/>
                    <form:label path="enabled" cssClass="custom-control-label" for="userEnableToggle">Account actief</form:label>
                </div>

            </div>
        </div>
</div>

<div class="modal-footer">
    <button type="submit" class="btn btn-primary mx-1">Aanpassen</button>
    <button type="button" class="btn btn-dark mx-1" data-dismiss="modal">Annuleren</button>
</div>

</form:form>

