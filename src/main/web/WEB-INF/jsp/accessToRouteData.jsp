<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Access To Route Data</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs box userVehicle_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#accessToRoute"><spring:message code="app.accessToRoutes"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#userVehicle"><spring:message code="app.userVehicles"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#userState"><spring:message code="app.userStates"/></a></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#vehicleState"><spring:message code="app.vehicleStates"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="accessToRoute">
                <form:form method="POST" modelAttribute="accessToRouteData" class="box accessToRoute">
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
                                        <form:option  value="${!empty accessToRouteData.id ? accessToRouteData.idUserVehicle.id : ''}"
                                                      label="${!empty accessToRouteData.id ? accessToRouteData.idUserVehicle.idUser.name.concat('; ')
                                              .concat(accessToRouteData.idUserVehicle.idVehicle.nameCar).concat(' - ')
                                              .concat(accessToRouteData.idUserVehicle.idVehicle.vehicleNumber) : ''}"/>

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
                                        <form:option  value="${!empty accessToRouteData.id ? accessToRouteData.idUserState.id : ''}"
                                                      label="${!empty accessToRouteData.id ? accessToRouteData.idUserState.nameUS : ''}"/>

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
                                        <form:option  value="${!empty accessToRouteData.id ? accessToRouteData.idVehicleState.id : ''}"
                                                      label="${!empty accessToRouteData.id ? accessToRouteData.idVehicleState.nameVS : ''}"/>
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
                                        <c:if test="${!empty accessToRouteData.id}">
                                            <c:if test="${ accessToRouteData.id == 1}">
                                                <form:option  value="1" label="YES"/>
                                            </c:if>
                                            <c:if test="${ accessToRouteData.id == 0}">
                                                <form:option  value="0" label="NO"/>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty accessToRouteData.id}">
                                            <form:option  value="" label=""/>
                                        </c:if>
                                    </form:select>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
            </div>

            <div class="tab-pane container active" id="userVehicle">
                <form:form method="POST" modelAttribute="atrUserVehicleData" class="box userVehicle">
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
                                    <form:input type="datetime-local" path="startDate" readonly="true" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="endDate">
                                        <spring:message text="endDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="endDate" readonly="true" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="idUser">
                                        <spring:message text="idUser"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idUser" readonly="true" disabled="true">
                                        <form:option  value="${!empty atrUserVehicleData.id ? atrUserVehicleData.idUser.id : ''}"
                                                      label="${!empty atrUserVehicleData.id ? atrUserVehicleData.idUser.name.concat('; ')
                                                                                      .concat(atrUserVehicleData.idUser.email) : ''}"/>

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
                                    <form:select path="idVehicle" readonly="true" disabled="true">
                                        <form:option  value="${!empty atrUserVehicleData.id ? atrUserVehicleData.idVehicle.id : ''}"
                                                      label="${!empty atrUserVehicleData.id ? atrUserVehicleData.idVehicle.nameCar.concat('; ')
                                                                                    .concat(atrUserVehicleData.idVehicle.vehicleNumber) : ''}"/>

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
                                    <form:input path="isCurrentUserMachine" readonly="true" disabled="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="userState">
                <form:form method="POST" modelAttribute="atrUserStateData" class="box formTwoFields">
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
                                    <form:label path="nameUS">
                                        <spring:message text="nameUS"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="nameUS" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="vehicleState">
                <form:form method="POST" modelAttribute="atrVehicleStateData" class="box formTwoFields">
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
                                    <form:label path="nameVS">
                                        <spring:message text="nameVS"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="nameVS" />
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
