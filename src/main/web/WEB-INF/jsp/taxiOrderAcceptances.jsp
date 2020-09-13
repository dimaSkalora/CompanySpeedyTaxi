<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Taxi Dispatcher Orders</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="taxiOrderAcceptances/taxiOrderAcceptance">add Taxi Order Acceptance</a> </li>
        </ul>
        <ul>
            <li><a href="taxiOrderAcceptances/taxiOrderAcceptanceFilter" class="btnSearchBy">Search by Taxi Order Acceptance</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="taxiOrderAcceptance.id"/></th>
                <th><spring:message code="taxiOrderAcceptance.idUserVehicle"/></th>
                <th><spring:message code="taxiOrderAcceptance.idTaxiDispatcherOrder"/></th>
                <th><spring:message code="taxiOrderAcceptance.idTaxiUserOrder"/></th>
                <th><spring:message code="taxiOrderAcceptance.executionStatus"/></th>
                <th><spring:message code="taxiOrderAcceptance.adoptionStatus"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${taxiOrderAcceptances}" var="taxiOrderAcceptance">
                <jsp:useBean id="taxiOrderAcceptance" scope="page" type="com.taxi.speedy.company.model.TaxiOrderAcceptance"/>
                <tr>
                    <td><a href="taxiOrderAcceptances/getTaxiOrderAcceptanceData/${taxiOrderAcceptance.id}"/><c:out value="${taxiOrderAcceptance.id}"/></td>
                    <td><c:out value="${taxiOrderAcceptance.idUserVehicle.id} "/></td>
                    <td><c:out value="${taxiOrderAcceptance.idTaxiDispatcherOrder.id}: ${taxiOrderAcceptance.idTaxiDispatcherOrder.idTaxiDispatcher.idUser.name}"/></td>
                    <td><c:out value="${taxiOrderAcceptance.idTaxiUserOrder.id}: ${taxiOrderAcceptance.idTaxiUserOrder.idUser.name}"/></td>
                    <td>
                        <c:out value="${taxiOrderAcceptance.executionStatus  == 1 ? 'Yes' : 'No'}"/>
                    </td>
                    <td>
                        <c:out value="${taxiOrderAcceptance.adoptionStatus  == 1 ? 'Yes' : 'No'}"/>
                    </td>
                    <td><a href="taxiOrderAcceptances/update?id=${taxiOrderAcceptance.id}"/><spring:message code="app.update"/></td>
                    <td><a href="taxiOrderAcceptances/delete/${taxiOrderAcceptance.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
