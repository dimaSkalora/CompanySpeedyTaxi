<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>UserVehicles</title>
</head>
<body>
<h1><a href="userVehicles/userVehicle">add User Vehicle</a> </h1>
<br>
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
            <td><c:out value="${userVehicle.startDate.toLocalDate()} ${userVehicle.startDate.toLocalTime()}"/></td>
            <td><c:out value="${userVehicle.endDate.toLocalDate()} ${userVehicle.endDate.toLocalTime()}"/></td>
            <td><c:out value="${userVehicle.idUser.name}"/></td>
            <td><c:out value="${userVehicle.idVehicle.nameCar} [${userVehicle.idVehicle.vehicleNumber}]"/></td>
            <td><c:out value="${userVehicle.isCurrentUserMachine}"/></td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
