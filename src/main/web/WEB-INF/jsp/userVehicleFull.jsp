<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>UserVehicleFull</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<c:url var="createOrUpdateUrl" value="/userVehicles/createOrUpdateUVFull" />
<form:form method="POST" modelAttribute="userVehicleFull" action="${createOrUpdateUrl}"
         class = "box userVehicleFull"  >

    <fieldset class="boxBody">
        <table>
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="id"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="startDate">
                        <spring:message text="startDate"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="datetime-local" path="startDate" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="endDate">
                        <spring:message text="endDate"/>
                    </form:label>
                </td>
                <td>
                    <form:input type="datetime-local" path="endDate" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="idUser">
                        <spring:message text="idUser"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="idUser">
                        <form:option  value="" label=""/>
                        <c:forEach items="${uvAllUsers}" var="user">
                            <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
                            <form:option  value="${user.id}" label="${user.name}; ${user.email}"/>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="idVehicle">
                        <spring:message text="idVehicle"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="idVehicle">
                        <form:option  value="" label=""/>
                        <c:forEach items="${uvAllVehicles}" var="vehicle">
                            <jsp:useBean id="vehicle" scope="page" type="com.taxi.speedy.company.model.Vehicle"/>
                            <form:option  value="${vehicle.id}" label="${vehicle.nameCar}; ${vehicle.vehicleNumber}"/>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="isCurrentUserMachine">
                        <spring:message text="isCurrentUserMachine"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="isCurrentUserMachine" />
                </td>
            </tr>
        </table>
    </fieldset>

    <footer>
            <c:if test="${!empty userVehicleFull.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
            </c:if>
            <c:if test="${empty userVehicleFull.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
            </c:if>
    </footer>
</form:form>
</body>
</html>
