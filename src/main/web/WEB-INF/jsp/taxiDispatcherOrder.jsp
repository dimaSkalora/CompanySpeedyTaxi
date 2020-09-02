<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi Dispatcher Order</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/taxiDispatcherOrders/createOrUpdate" />
        <form:form method="POST" modelAttribute="taxiDispatcherOrder" action="${createOrUpdateUrl}"
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
                                <form:option  value="${!empty taxiDispatcherOrder.id ? taxiDispatcherOrder.idTaxiDispatcher.id : ''}"
                                              label="${!empty taxiDispatcherOrder.id ? taxiDispatcherOrder.idTaxiDispatcher.idUser.name : ''}"/>
                                <%--<form:option  value="${taxiDispatcherOrder.idTaxiDispatcher.id}" label="${taxiDispatcherOrder.idTaxiDispatcher.idUser.name }"/>--%>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${allTaxiDispatcher}" var="taxiDispatcher">
                                    <jsp:useBean id="taxiDispatcher" scope="page" type="com.taxi.speedy.company.model.TaxiDispatcher"/>
                                    <form:option  value="${taxiDispatcher.id}" label="${taxiDispatcher.idUser.name}"/>
                                </c:forEach>
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
                    <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                            <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
                        <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                        <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/taxiDispatcherOrders/get"/>
                        <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                            <a href="/CompanySpeedyTaxi/taxiDispatcherOrders" class="btnLogin"><spring:message code="app.ok"/></a>
                            <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/taxiDispatcherOrders'"/>--%>
                        </c:if>
                        <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                            <c:if test="${!empty taxiDispatcherOrder.id}">
                                <input type="submit" class="btnLogin" value="<spring:message code="app.update"/>"/>
                            </c:if>
                            <c:if test="${empty taxiDispatcherOrder.id}">
                                <input type="submit" class="btnLogin" value="<spring:message code="app.save"/>"/>
                            </c:if>
                        </c:if>
            </footer>
        </form:form>
    </div>
</div>
</body>
</html>
