<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Vehicles</title>
</head>
<body>
<h1><a href="vehicles/vehicle"/>add vehicle </h1>
<br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Название, модель ТС</th>
        <th>Номер ТС</th>
        <th>Год выпуска ТС</th>
        <th>Категория</th>
        <th>Цвет ТС</th>
        <th>Расход топлива л/100км</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${vehicles}" var="vehicle">
        <jsp:useBean id="vehicle" scope="page" type="com.taxi.speedy.company.model.Vehicle"/>
        <tr>
            <td><a href="vehicles/get/${vehicle.id}"/>${vehicle.nameCar}</td>
            <td>${vehicle.vehicleNumber}</td>
            <td>${vehicle.yearIssue}</td>
            <td>${vehicle.category}</td>
            <td>${vehicle.color}</td>
            <td>${vehicle.fuelConsumption}</td>
            <td><a href="vehicles/update/${vehicle.id}"/>update</td>
            <td><a href="vehicles/delete?id=${vehicle.id}"/>delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
