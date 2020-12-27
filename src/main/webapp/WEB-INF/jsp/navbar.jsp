<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeroen Leyssen
  Date: 11/21/2020
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-md sticky-top navbar-dark navbar-color">

    <!-- Navigation menu toggler - Burger menu button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle Navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Branding -->
    <a class="navbar-brand ml-md-1 ml-auto" href="/"><span class="highlight">DiceRoll</span></a>

    <!-- User menu - Does not collapse -->
    <div class="nav-item dropdown ml-auto order-md-2">
        <a class="nav-link dropdown-toggle text-light pl-0" href="#" id="userMenu" role="button" data-toggle="dropdown">
            <i class="fa fa-user mr-2"></i><%=request.getParameter("userFirstName")%>
        </a>
        <div class="dropdown-menu dropdown-menu-md-right dropdown-default navbar-dropdown-menu" aria-labelledby="userMenu">
            <!-- Menu elements when user is not logged in (= anonymous user) -->
            <sec:authorize access="isAnonymous()">

                <form class="pt-2 px-4" method="post" action="/login?from=${requestScope['javax.servlet.forward.request_uri']}">
                    <div class="form-row">
                        <label class="mb-1">Email:</label>
                        <input type="text" class="form-control" name="username" id="username" placeholder="email@voorbeeld.com"/>
                    </div>

                    <div class="form-row mt-2">
                        <label class="mb-1">Paswoord:</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="paswoord"/>
                    </div>

                    <div class="row justify-content-center">
                        <button type="submit" class="btn btn-dark btn-block mt-2">Inloggen</button>
                    </div>

                </form>

                <div class="dropdown-divider"></div>
                <a class="nav-link text-dark" href="register">
                    <i class="fa fa-user-plus mr-2"></i>Registreren

                </a>
            </sec:authorize>

            <!-- Menu elements when user is logged in -->
            <sec:authorize access="isAuthenticated()">
                <a class="nav-link text-dark" href="/user/info">Mijn informatie</a>
                <a class="nav-link text-dark" href="userOrders">Mijn bestellingen</a>
                <div class="dropdown-divider"></div>
                <a class="nav-link text-dark" href="/logout">
                    <i class="fa fa-sign-out mr-2"></i>Uitloggen
                </a>
            </sec:authorize>
        </div>
    </div>

    <!-- Navigation menu - Collapse on medium screen to burger menu -->
    <div class="collapse navbar-collapse order-md-1" id="navigation">
        <div class="navbar-nav">
            <a class="nav-link d-flex justify-content-between" href="/">Home</a>
            <a class="nav-link d-flex justify-content-between" href="/contact">Contact</a>
            <a class="nav-link d-flex justify-content-between" href="/categorie">Categorie</a>
            <a class="nav-link d-flex justify-content-between" href="/overons">Over Ons</a>
            <a class="nav-link disabled d-flex justify-content-between" href="/games">Games</a>

            <!-- Admin user only -->
            <sec:authorize access="hasRole('Admin')">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="adminMenu" role="button" data-toggle="dropdown">
                    Admin
                </a>

                <div class="dropdown-menu dropdown-default navbar-dropdown-menu" aria-labelledby="adminMenu">
                    <a class="nav-link text-dark" href="/admin/users">Gebruikers</a>
                    <a class="nav-link text-dark" href="/admin/orders">Bestellingen</a>
                </div>
            </div>
            </sec:authorize>

        </div>
    </div>

</nav>
