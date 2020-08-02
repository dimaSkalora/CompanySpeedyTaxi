<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>UserVehicleData</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#userVehicle">UserVehicle</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#user">User</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#vehicle">Vehicle</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="userVehicle">

                <form:form method="POST" modelAttribute="uvData"
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
                                        <form:option  value="${!empty uvData.id ? uvData.idUser.id : ''}"
                                                      label="${!empty uvData.id ? uvData.idUser.name.concat('; ').concat(uvData.idUser.email) : ''}"/>

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
                                        <form:option  value="${!empty uvData.id ? uvData.idVehicle.id : ''}"
                                                      label="${!empty uvData.id ? uvData.idVehicle.nameCar.concat('; ').concat(uvData.idVehicle.vehicleNumber) : ''}"/>

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
            <div class="tab-pane container fade" id="user">
                <form:form method="POST" modelAttribute="uvUser"
                           class="box login">

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
                                <td>
                                    <form:label path="name">
                                        <spring:message text="name"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="password">
                                        <spring:message text="password"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:password path="password" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="email">
                                        <spring:message text="email"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="email" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="phone">
                                        <spring:message text="phone"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="phone" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="address">
                                        <spring:message text="address"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="address" disabled="true"/>
                                </td>
                            </tr>
                            <c:if test="${!empty uvUser.name}">
                                <tr>
                                    <td>
                                        <form:label path="registered">
                                            <spring:message text="registered"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:input path="registered" disabled="true"/>
                                    </td>
                                    <td>
                                        <form:label path="enabled">
                                            <spring:message text="enabled"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:checkbox path="enabled" disabled="true"/>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <form:label path="roles">
                                        <spring:message text="roles"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="roles" readonly="true" size="8" disabled="true"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
            </div>
            <div class="tab-pane container fade" id="vehicle">
                <form:form method="POST" modelAttribute="uvVehicle"
                           class="box vehicle">

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
                                <td>
                                    <form:label path="nameCar">
                                        <spring:message text="nameCar"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="nameCar" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="vehicleNumber">
                                        <spring:message text="vehicleNumber"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="vehicleNumber" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="yearIssue">
                                        <spring:message text="yearIssue"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="yearIssue" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="category">
                                        <spring:message text="category"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="category" disabled="true"/>
                                </td>
                                <td>
                                    <form:label path="color">
                                        <spring:message text="color"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="color" disabled="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="fuelConsumption">
                                        <spring:message text="fuelConsumption"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="fuelConsumption" disabled="true"/>
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
