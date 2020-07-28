<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>UserVehicles</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="userVehicles/userVehicle">add User Vehicle</a> </li>
            <li><a href="userVehicles/userVehicleHSR">add User VehicleHSR</a> </li>
            <li><a href="userVehicles/userVehicleFull">add User VehicleFull</a></li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Дата когда пользователь взял машину</th>
                <th>Дата когда пользователь вернул машину</th>
                <th>Пользователь</th>
                <th>Машина</th>
                <th>Машина текущего пользователя(Y/N)</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${userVehicles}" var="userVehicle">
                <jsp:useBean id="userVehicle" scope="page" type="com.taxi.speedy.company.model.UserVehicle"/>
                <tr>
                    <td><a href="userVehicles/get/${userVehicle.id}"/><c:out value="${userVehicle.startDate.toLocalDate()} ${userVehicle.startDate.toLocalTime()}"/></td>
                    <td><c:out value="${userVehicle.endDate.toLocalDate()} ${userVehicle.endDate.toLocalTime()}"/></td>
                    <td><c:out value="${userVehicle.idUser.name}"/></td>
                    <td><c:out value="${userVehicle.idVehicle.nameCar} [${userVehicle.idVehicle.vehicleNumber}]"/></td>
                    <td>
                        <c:out value="${userVehicle.isCurrentUserMachine  == 1 ? 'Yes' : 'No'}"/>
                    </td>
                    <td><a href="userVehicles/update/${userVehicle.id}"/>update</td>
                    <td><a href="userVehicles/delete?id=${userVehicle.id}"/>delete</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
