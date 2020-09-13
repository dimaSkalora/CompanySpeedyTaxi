<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi User Order Data</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs box taxiOrderAcceptance_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#taxiOrderAcceptance"><spring:message code="app.taxiOrderAcceptances"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#userVehicle"><spring:message code="app.userVehicles"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#taxiDispatcherOrder"><spring:message code="app.taxiDispatcherOrders"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#taxiUserOrder"><spring:message code="app.taxiUserOrders"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="taxiOrderAcceptance">
                <form:form method="POST" modelAttribute="taxiOrderAcceptance"
                           class="box taxiOA">
                    <fieldset class="boxBody">
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="taxiOrderAcceptance.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="idUserVehicle">
                                        <spring:message code="taxiOrderAcceptance.idUserVehicle"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idUserVehicle">
                                        <form:option  value="${!empty taxiOrderAcceptance.id ? taxiOrderAcceptance.idUserVehicle.id : ''}"
                                                      label="${!empty taxiOrderAcceptance.id ? taxiOrderAcceptance.idUserVehicle.id : ''}"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="idTaxiDispatcherOrder">
                                        <spring:message code="taxiOrderAcceptance.idTaxiDispatcherOrder"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idTaxiDispatcherOrder">
                                        <form:option  value="${!empty taxiOrderAcceptance.id ? taxiOrderAcceptance.idTaxiDispatcherOrder.id : ''}"
                                                      label="${!empty taxiOrderAcceptance.id ? taxiOrderAcceptance.idTaxiDispatcherOrder.id : ''}"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="idTaxiUserOrder">
                                        <spring:message code="taxiOrderAcceptance.idTaxiUserOrder"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idTaxiUserOrder">
                                        <form:option  value="${!empty taxiOrderAcceptance.id ? taxiOrderAcceptance.idTaxiUserOrder.id : ''}"
                                                      label="${!empty taxiOrderAcceptance.id ? taxiOrderAcceptance.idTaxiUserOrder.id : ''}"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="executionStatus">
                                        <spring:message code="taxiOrderAcceptance.executionStatus"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="number" path="executionStatus"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="adoptionStatus">
                                        <spring:message code="taxiOrderAcceptance.adoptionStatus"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="number" path="adoptionStatus"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/CompanySpeedyTaxi/taxiOrderAcceptances" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="userVehicle">
                <form:form method="POST" modelAttribute="toaUserVehicle"
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
                                        <form:option  value="${!empty toaUserVehicle.id ? toaUserVehicle.idUser.id : ''}"
                                                      label="${!empty toaUserVehicle.id ? toaUserVehicle.idUser.id : ''}"/>
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
                                        <form:option  value="${!empty toaUserVehicle.id ? toaUserVehicle.idVehicle.id : ''}"
                                                      label="${!empty toaUserVehicle.id ? toaUserVehicle.idVehicle.id : ''}"/>
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
                        <a href="/CompanySpeedyTaxi/taxiOrderAcceptances" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="taxiDispatcherOrder">
                <form:form method="POST" modelAttribute="toaTaxiDispatcherOrder"
                           class="box taxiDO">
                    <fieldset class="boxBody">
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="taxiDispatcherOrder.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="dateTimeOrder">
                                        <spring:message code="taxiDispatcherOrder.dateTimeOrder"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="dateTimeOrder" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="idTaxiDispatcher">
                                        <spring:message code="taxiDispatcherOrder.idTaxiDispatcher"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idTaxiDispatcher">
                                        <form:option  value="${!empty toaTaxiDispatcherOrder.id ? toaTaxiDispatcherOrder.idTaxiDispatcher.id : ''}"
                                                      label="${!empty toaTaxiDispatcherOrder.id ? toaTaxiDispatcherOrder.idTaxiDispatcher.id : ''}"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="userName">
                                        <spring:message code="taxiDispatcherOrder.userName"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="userName"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="userPhone">
                                        <spring:message code="taxiDispatcherOrder.userPhone"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="userPhone"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="addressDeparture">
                                        <spring:message code="taxiDispatcherOrder.addressDeparture"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="addressDeparture"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="addressArrival">
                                        <spring:message code="taxiDispatcherOrder.addressArrival"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="addressArrival"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="startDate">
                                        <spring:message code="taxiDispatcherOrder.startDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="startDate" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="endDate">
                                        <spring:message code="taxiDispatcherOrder.endDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="endDate" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="fulfilled">
                                        <spring:message code="taxiDispatcherOrder.fulfilled"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="number" path="fulfilled"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/CompanySpeedyTaxi/taxiOrderAcceptances" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="taxiUserOrder">
                <form:form method="POST" modelAttribute="toaTaxiUserOrder"
                           class="box taxiUO">
                    <fieldset class="boxBody">
                        <table>
                            <tr>
                                <td>
                                    <form:label path="id">
                                        <spring:message code="taxiUserOrder.id"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="id"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="dateTimeOrder">
                                        <spring:message code="taxiUserOrder.dateTimeOrder"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="dateTimeOrder" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="idUser">
                                        <spring:message code="taxiUserOrder.idUser"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idUser">
                                        <form:option  value="${!empty toaTaxiUserOrder.id ? toaTaxiUserOrder.idUser.id : ''}"
                                                      label="${!empty toaTaxiUserOrder.id ? toaTaxiUserOrder.idUser.id : ''}"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="addressDeparture">
                                        <spring:message code="taxiUserOrder.addressDeparture"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="addressDeparture"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="addressArrival">
                                        <spring:message code="taxiUserOrder.addressArrival"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="addressArrival"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="startDate">
                                        <spring:message code="taxiUserOrder.startDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="startDate" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="endDate">
                                        <spring:message code="taxiUserOrder.endDate"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="datetime-local" path="endDate" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="fulfilled">
                                        <spring:message code="taxiUserOrder.fulfilled"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input type="number" path="fulfilled"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>

                    <footer>
                        <a href="/CompanySpeedyTaxi/taxiOrderAcceptances" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
