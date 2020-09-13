<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi Order Acceptance Search</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/taxiOrderAcceptances/filterCondition" />
        <form method="post" action="${createOrUpdateUrl}" >
            <table border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td><spring:message code="taxiOrderAcceptance.id"/></td>
                    <td><input type="number" name="id" value="${param.id}"></td>
                    <td><spring:message code="taxiOrderAcceptance.idUserVehicle"/></td>
                    <td><input type="number" name="idUserVehicle value="${param.idUserVehicle}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiOrderAcceptance.idTaxiDispatcherOrder"/></td>
                    <td><input type="number" name="idTaxiDispatcherOrder" value="${param.idTaxiDispatcherOrder}"></td>
                    <td><spring:message code="taxiOrderAcceptance.idTaxiUserOrder"/></td>
                    <td><input type="number" name="idTaxiUserOrderv" value="${param.idTaxiUserOrder}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiOrderAcceptance.executionStatus"/></td>
                    <td><input type="number" name="executionStatus" value="${param.executionStatus}"></td>
                    <td><spring:message code="taxiOrderAcceptance.adoptionStatus"/></td>
                    <td><input type="number" name="adoptionStatus" value="${param.adoptionStatus}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiOrderAcceptance.uvStartDateFrom"/></td>
                    <td><input type="datetime-local" name="uvStartDateFrom" value="${param.uvStartDateFrom}"></td>
                    <td><spring:message code="taxiOrderAcceptance.uvStartDateTo"/></td>
                    <td><input type="datetime-local" name="uvStartDateTo" value="${param.uvStartDateTo}"></td>
                </tr>
                <tr>
                    <td><spring:message code="taxiOrderAcceptance.uvEndDateFrom"/></td>
                    <td><input type="datetime-local" name="uvEndDateFrom" value="${param.uvEndDateFrom}"></td>
                    <td><spring:message code="taxiOrderAcceptance.uvEndDateTo"/></td>
                    <td><input type="datetime-local" name="uvEndDateTo" value="${param.uvEndDateTo}"></td>
                </tr>

                <div class="text-right">
                    <a class="btn btn-danger" href="taxiOrderAcceptances"><spring:message code="app.taxiOrderAcceptances"/>
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
