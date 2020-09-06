<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Taxi User Orders</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="taxiUserOrders/taxiUserOrder">add Taxi User Order</a> </li>
        </ul>
        <ul>
            <li><a href="taxiUserOrders/filter" class="btnSearchBy">Search by Taxi User Order</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="taxiUserOrder.id"/></th>
                <th><spring:message code="taxiUserOrder.dateTimeOrder"/></th>
                <th><spring:message code="taxiUserOrder.idUser"/></th>
                <th><spring:message code="taxiUserOrder.addressDeparture"/></th>
                <th><spring:message code="taxiUserOrder.addressArrival"/></th>
                <th><spring:message code="taxiUserOrder.startDate"/></th>
                <th><spring:message code="taxiUserOrder.endDate"/></th>
                <th><spring:message code="taxiUserOrder.fulfilled"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${taxiUserOrders}" var="taxiUserOrder">
                <jsp:useBean id="taxiUserOrder" scope="page" type="com.taxi.speedy.company.model.TaxiUserOrder"/>
                <tr>
                    <td><a href="taxiUserOrders/getTaxiUserOrderData/${taxiUserOrder.id}"/><c:out value="${taxiUserOrder.dateTimeOrder.toLocalDate()} ${taxiUserOrder.dateTimeOrder.toLocalTime()}"/></td>
                    <td><c:out value="${taxiUserOrder.id}"/></td>
                    <td><c:out value="${taxiUserOrder.idUser.id}: ${taxiUserOrder.idUser.name} "/></td>
                    <td><c:out value="${taxiUserOrder.addressDeparture}"/></td>
                    <td><c:out value="${taxiUserOrder.addressArrival}"/></td>
                    <td><c:out value="${taxiUserOrder.startDate.toLocalDate()} ${taxiUserOrder.startDate.toLocalTime()}"/></td>
                    <td><c:out value="${taxiUserOrder.endDate.toLocalDate()} ${taxiUserOrder.endDate.toLocalTime()}"/></td>
                    <td>
                        <c:out value="${taxiUserOrder.fulfilled  == 1 ? 'Yes' : 'No'}"/>
                    </td>
                    <td><a href="taxiUserOrders/update/${taxiUserOrder.id}"/><spring:message code="app.update"/></td>
                    <td><a href="taxiUserOrders/delete?id=${taxiUserOrder.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
