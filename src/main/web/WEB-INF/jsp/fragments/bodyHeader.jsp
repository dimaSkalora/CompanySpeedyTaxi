<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href=""><spring:message code="app.home"/></a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">List</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="users"><spring:message code="app.users"/></a>
                    <a class="dropdown-item" href="vehicles"><spring:message code="app.vehicles"/></a>
                    <a class="dropdown-item" href="userVehicles"><spring:message code="app.userVehicles"/></a>
                    <a class="dropdown-item" href="userStates"><spring:message code="app.userStates"/></a>
                    <a class="dropdown-item" href="vehicleStates"><spring:message code="app.vehicleStates"/></a>
                    <a class="dropdown-item" href="accessToRoutes"><spring:message code="app.accessToRoutes"/></a>
                    <a class="dropdown-item" href="taxiDispatchers"><spring:message code="app.taxiDispatchers"/></a>
                    <a class="dropdown-item" href="taxiDispatcherOrders"><spring:message code="app.taxiDispatcherOrders"/></a>
                    <a class="dropdown-item" href="taxiUserOrders"><spring:message code="app.taxiUserOrders"/></a>
                    <a class="dropdown-item" href="taxiOrderAcceptances"><spring:message code="app.taxiOrderAcceptances"/></a>
                </div>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a></li>
                    <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a></li>
<%--                    <li><a href="?lang=en">English</a></li>
                    <li><a href="?lang=ru">Русский</a></li>--%>
                </ul>
                <ul>

                </ul>
            </li>
        </ul>
    </div>
</nav>

