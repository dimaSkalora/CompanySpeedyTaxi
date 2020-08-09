<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>vehicleStates</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="vehicleStates/vehicleState">add Vehicle State </a></li>
        </ul>
    </div>
    <div class="container">
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Название состояния</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${vehicleStates}" var="vehicleState">
                <jsp:useBean id="vehicleState" scope="page" type="com.taxi.speedy.company.model.VehicleState"/>
                <tr>
                    <td><a href="vehicleStates/get/${vehicleState.id}"/><c:out value="${vehicleState.nameVS}"/></td>
                    <td><a href="vehicleStates/update/${vehicleState.id}"/>update</td>
                    <td><a href="vehicleStates/delete?id=${vehicleState.id}"/>delete</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
