<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi User Order Search</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/taxiUserOrders/filterCondition" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="taxiUserOrder.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="taxiUserOrder.fulfilled"/></td>
                    <td><input type="number" name="fulfilled value="${param.fulfilled}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiUserOrder.dateTimeOrder"/></td>
                    <td><input type="datetime-local" name="dateTimeOrder" value="${param.dateTimeOrder}"></td>
                    <td><spring:message code="taxiUserOrder.idUser"/></td>
                    <td><input type="number" name="idUser" value="${param.idUser}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiUserOrder.addressDeparture"/></td>
                    <td><input type="text" name="addressDeparture" value="${param.addressDeparture}"></td>
                    <td><spring:message code="taxiUserOrder.addressArrival"/></td>
                    <td><input type="text" name="addressArrival" value="${param.addressArrival}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiUserOrder.startDate"/></td>
                    <td><input type="datetime-local" name="startDate" value="${param.startDate}"></td>
                    <td><spring:message code="taxiUserOrder.endDate"/></td>
                    <td><input type="datetime-local" name="endDate" value="${param.endDate}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiUserOrder.dateTimeOrderFrom"/></td>
                    <td><input type="datetime-local" name="dateTimeOrderFrom" value="${param.dateTimeOrderFrom}"></td>
                    <td><spring:message code="taxiUserOrder.dateTimeOrderTo"/></td>
                    <td><input type="datetime-local" name="dateTimeOrderTo" value="${param.dateTimeOrderTo}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiUserOrder.startDateFrom"/></td>
                    <td><input type="datetime-local" name="startDateFrom" value="${param.startDateFrom}"></td>
                    <td><spring:message code="taxiUserOrder.startDateTo"/></td>
                    <td><input type="datetime-local" name="startDateTo" value="${param.startDateTo}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiUserOrder.endDateFrom"/></td>
                    <td><input type="datetime-local" name="endDateFrom" value="${param.endDateFrom}"></td>
                    <td><spring:message code="taxiUserOrder.endDateTo"/></td>
                    <td><input type="datetime-local" name="endDateTo" value="${param.endDateTo}"></td>
                </tr>

                <div class="text-right">
                    <a class="btn btn-danger" href="taxiDispatcherOrders"><spring:message code="app.taxiUserOrders"/>
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <button class="btn btn-primary" type="submit"> <spring:message code="app.search"/>
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </button>
                </div>
            </table>
        </form>
    </div>
</div>
</body>
</html>
