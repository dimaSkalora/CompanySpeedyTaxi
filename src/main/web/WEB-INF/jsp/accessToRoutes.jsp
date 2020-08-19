<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Access To Routes</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="accessToRoutes/accessToRoute">add Access To Route</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="accessToRoute.checksDateTime"/></th>
                <th><spring:message code="accessToRoute.idUserVehicle"/></th>
                <th><spring:message code="accessToRoute.idUserState"/></th>
                <th><spring:message code="accessToRoute.idVehicleState"/></th>
                <th><spring:message code="accessToRoute.isAccess"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${accessToRoutes}" var="accessToRoute">
                <jsp:useBean id="accessToRoute" scope="page" type="com.taxi.speedy.company.model.AccessToRoute"/>
                <tr>
                        <%--  <td><a href="accessToRoutes/get/${accessToRoute.id}"/><c:out value="${accessToRoute.checksDateTime.toLocalDate()} ${userVehicle.checksDateTime.toLocalTime()}"/></td>--%>
                    <td><a href="accessToRoutes/getAccessToRouteDate/${accessToRoute.id}"/><c:out value="${accessToRoute.checksDateTime.toLocalDate()} ${accessToRoute.checksDateTime.toLocalTime()}"/></td>
                    <td><c:out value="${accessToRoute.idUserVehicle()}: ${accessToRoute.idUserVehicle().idUser.name} ${accessToRoute.idUserVehicle().idVehicle.nameCar}"/></td>
                    <td><c:out value="${accessToRoute.idUserState()} : ${accessToRoute.idUserState.nameUS}"/></td>
                    <td><c:out value="${accessToRoute.idVehicleState()} [${accessToRoute.idVehicleState().nameVS}]"/></td>
                    <td>
                        <c:out value="${accessToRoute.isAccess  == 1 ? 'Yes' : 'No'}"/>
                    </td>
                    <td><a href="accessToRoutes/update/${userVehicle.id}"/>update</td>
                    <td><a href="accessToRoutes/delete?id=${userVehicle.id}"/>delete</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
