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
            <li><a href="taxiDispatcherOrders/taxiDispatcherOrder">add Taxi Dispatcher Order</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="taxiDispatcherOrder.dateTimeOrder"/></th>
                <th><spring:message code="taxiDispatcherOrder.idTaxiDispatcher"/></th>
                <th><spring:message code="taxiDispatcherOrder.userName"/></th>
                <th><spring:message code="taxiDispatcherOrder.userPhone"/></th>
                <th><spring:message code="taxiDispatcherOrder.addressDeparture"/></th>
                <th><spring:message code="taxiDispatcherOrder.addressArrival"/></th>
                <th><spring:message code="taxiDispatcherOrder.startDate"/></th>
                <th><spring:message code="taxiDispatcherOrder.endDate"/></th>
                <th><spring:message code="taxiDispatcherOrder.fulfilled"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${taxiDispatcherOrders}" var="taxiDispatcherOrder">
                <jsp:useBean id="taxiDispatcherOrder" scope="page" type="com.taxi.speedy.company.model.TaxiDispatcherOrder"/>
                <tr>
                    <td><a href="taxiDispatcherOrders/get/${taxiDispatcherOrder.id}"/><c:out value="${taxiDispatcherOrder.dateTimeOrder.toLocalDate()} ${taxiDispatcherOrder.dateTimeOrder.toLocalTime()}"/></td>
                    <td><c:out value="${taxiDispatcherOrder.idTaxiDispatcher.id}: ${taxiDispatcherOrder.idTaxiDispatcher.idUser.name} "/></td>
                    <td><c:out value="${taxiDispatcherOrder.userName}"/></td>
                    <td><c:out value="${taxiDispatcherOrder.userPhone}"/></td>
                    <td><c:out value="${taxiDispatcherOrder.addressDeparture}"/></td>
                    <td><c:out value="${taxiDispatcherOrder.addressArrival}"/></td>
                    <td><c:out value="${taxiDispatcherOrder.startDate.toLocalDate()} ${taxiDispatcherOrder.startDate.toLocalTime()}"/></td>
                    <td><c:out value="${taxiDispatcherOrder.endDate.toLocalDate()} ${taxiDispatcherOrder.endDate.toLocalTime()}"/></td>
                    <td>
                        <c:out value="${taxiDispatcherOrder.fulfilled  == 1 ? 'Yes' : 'No'}"/>
                    </td>
                    <td><a href="taxiDispatcherOrders/update/${taxiDispatcherOrder.id}"/><spring:message code="app.update"/></td>
                    <td><a href="taxiDispatcherOrders/delete?id=${taxiDispatcherOrder.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
