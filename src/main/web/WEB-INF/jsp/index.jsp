<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Company Speedy Taxi</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="users"><spring:message code="app.users"/></a></li>
            <li><a href="vehicles"><spring:message code="app.vehicles"/></a></li>
            <li><a href="userVehicles"><spring:message code="app.userVehicles"/></a></li>
            <li><a href="userStates"><spring:message code="app.userStates"/></a></li>
            <li><a href="vehicleStates"><spring:message code="app.vehicleStates"/></a></li>
            <li><a href="accessToRoutes"><spring:message code="app.accessToRoutes"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
