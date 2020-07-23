<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>UserVehicle</title>
</head>
<body>
<c:url var="createOrUpdateUrl" value="/userVehicles/createOrUpdate" />
<form:form method="POST" modelAttribute="userVehicle" action="${createOrUpdateUrl}"
           class="box userVehicle">

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
            <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                    <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
        <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
        <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/userVehicles/get"/>
        <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
            <a href="/CompanySpeedyTaxi/userVehicles" class="btnLogin"><spring:message text="OK"/></a>
            <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/userVehicles'"/>--%>
        </c:if>
        <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
            <c:if test="${!empty userVehicle.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
            </c:if>
            <c:if test="${empty userVehicles.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
            </c:if>
        </c:if>
    </footer>
</form:form>
</body>
</html>
