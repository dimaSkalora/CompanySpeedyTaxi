<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Access To Route</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/accessToRoutes/createOrUpdate" />
        <form:form method="POST" modelAttribute="accessToRoute" action="${createOrUpdateUrl}"
                   class="box userVehicle">

            <fieldset class="boxBody">
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="accessToRoute.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="checksDateTime">
                                <spring:message code="accessToRoute.checksDateTime"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input type="datetime-local" path="checksDateTime" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="idUserVehicle">
                                <spring:message code="accessToRoute.idUserVehicle"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="idUserVehicle">
                                <form:option  value="${!empty accessToRoute.id ? accessToRoute.idUserVehicle.id : ''}"
                                              label="${!empty accessToRoute.id ? accessToRoute.idUserVehicle.idUser.name.concat('; ')
                                              .concat(accessToRoute.idUserVehicle.idVehicle.nameCar).concat(' - ')
                                              .concat(accessToRoute.idUserVehicle.idVehicle.vehicleNumber) : ''}"/>
                                <%--<form:option  value="${accessToRoute.idUserVehicle.id}" label="${accessToRoute.idUserVehicle.idUser.name }"/>--%>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${atrAllUserVehicles}" var="userVehicle">
                                    <jsp:useBean id="userVehicle" scope="page" type="com.taxi.speedy.company.model.UserVehicle"/>
                                    <form:option  value="${userVehicle.id}" label="${userVehicle.idUser.name}; ${userVehicle.idVehicle.nameCar}
                                                                                - ${userVehicle.idVehicle.vehicleNumber}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="idUserState">
                                <spring:message code="accessToRoute.idUserState"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="idUserState">
                                <form:option  value="${!empty accessToRoute.id ? accessToRoute.idUserState.id : ''}"
                                              label="${!empty accessToRoute.id ? accessToRoute.idUserState.nameUS : ''}"/>
                                <%--<form:option  value="${accessToRoute.idUserState.id}" label="${accessToRoute.idUserState.nameUS}"/>--%>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${atrAllUserStates}" var="userState">
                                    <jsp:useBean id="userState" scope="page" type="com.taxi.speedy.company.model.UserState"/>
                                    <form:option  value="${userState.id}" label="${userState.nameUS}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="idVehicleState">
                                <spring:message code="accessToRoute.idVehicleState"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="idVehicleState">
                                <form:option  value="${!empty accessToRoute.id ? accessToRoute.idVehicleState.id : ''}"
                                              label="${!empty accessToRoute.id ? accessToRoute.idVehicleState.nameVS : ''}"/>
                                <%--<form:option  value="${accessToRoute.idVehicleState.id}" label="${accessToRoute.idVehicleState.nameVS}"/>--%>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${atrAllVehicleStates}" var="vehicleState">
                                    <jsp:useBean id="vehicleState" scope="page" type="com.taxi.speedy.company.model.VehicleState"/>
                                    <form:option  value="${vehicleState.id}" label="${vehicleState.nameVS}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="isAccess">
                                <spring:message code="accessToRoute.isAccess"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="isAccess">
                                <form:option  value="${!empty accessToRoute.id ? accessToRoute.isAccess : ''}"
                                              label="${!empty accessToRoute.id ? accessToRoute.isAccess : ''}"/>
                                <form:option  value="1" label="YES"/>
                                <form:option  value="0" label="NO"/>
                            </form:select>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                    <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                            <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/accessToRoutes/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/CompanySpeedyTaxi/accessToRoutes" class="btnLogin"><spring:message text="OK"/></a>
                    <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/accessToRoutes'"/>--%>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty accessToRoute.id}">
                        <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
                    </c:if>
                    <c:if test="${empty accessToRoute.id}">
                        <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>
</body>
</html>
