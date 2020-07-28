<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>UserVehicle</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">

        <c:url var="createOrUpdateUrl" value="/userVehicles/createOrUpdateHSR" />
        <form method="POST" action="${createOrUpdateUrl}"
              class="box userVehicle">

            <fieldset class="boxBody">
                <table>
                    <tr>
                        <td>
                            <label>
                                <spring:message text="id"/>
                            </label>
                        </td>
                        <td>
                            <input type="number" name="id" value="${param.id}" readonly="true" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <spring:message text="startDate"/>
                            </label>
                        </td>
                        <td>
                            <input type="datetime-local" name="startDate" value="${param.startDate}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label >
                                <spring:message text="endDate"/>
                            </label>
                        </td>
                        <td>
                            <input type="datetime-local" name="endDate" value="${param.endDate}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <spring:message text="idUser"/>
                            </label>
                        </td>
                        <td>
                            <select name="idUser" value="${param.idUser}">
                                <option  value="" label=""/>
                                <c:forEach items="${uvAllUsers}" var="user">
                                    <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
                                    <option label="${user.name} ${user.email}" value="${user.id}"/>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <spring:message text="idVehicle"/>
                            </label>
                        </td>
                        <td>
                            <select name="idVehicle" value="${param.idVehicle}">
                                <option  value="" label=""/>
                                <c:forEach items="${uvAllVehicles}" var="vehicle">
                                    <jsp:useBean id="vehicle" scope="page" type="com.taxi.speedy.company.model.Vehicle"/>
                                    <option value="${vehicle.id}" label="${vehicle.nameCar}; ${vehicle.vehicleNumber}"/>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
            </footer>
        </form>
    </div>
</div>
</body>
</html>
