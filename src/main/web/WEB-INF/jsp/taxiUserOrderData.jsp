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
        <ul class="nav nav-tabs box accessToRoute_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#taxiUserOrder"><spring:message code="app.taxiUserOrders"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#user"><spring:message code="app.users"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="taxiUserOrder">
                <form:form method="POST" modelAttribute="taxiUserOrderData" class="box taxiUO">
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
                                    <form:label path="idTaxiDispatcher">
                                        <spring:message code="taxiDispatcherOrder.idTaxiDispatcher"/>
                                    </form:label>
                                </td>
                                <td>
                                    <form:select path="idUser">
                                        <form:option  value="${!empty taxiUserOrderData.id ? taxiUserOrderData.idUser.id : ''}"
                                                      label="${!empty taxiUserOrderData.id ? taxiUserOrderData.idUser.name : ''}"/>
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
                        <a href="/CompanySpeedyTaxi/taxiUserOrders" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="user">
                <form:form method="POST" modelAttribute="tuoUserData" class="box login">
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
                            <c:if test="${!empty tuoUserData.name}">
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
                    <footer>
                        <a href="/CompanySpeedyTaxi/taxiUserOrders" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
