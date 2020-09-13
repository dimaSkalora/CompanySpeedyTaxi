<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi Order Acceptance</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/taxiOrderAcceptances/createOrUpdate" />
        <form:form method="POST" modelAttribute="taxiOrderAcceptance" action="${createOrUpdateUrl}"
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
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${toaAllUserVehicles}" var="userVehicle">
                                    <jsp:useBean id="userVehicle" scope="page" type="com.taxi.speedy.company.model.UserVehicle"/>
                                    <form:option  value="${userVehicle.id}" label="${userVehicle.id}"/>
                                </c:forEach>
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
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${toaAllTaxiDispatcherOrders}" var="taxiDispatcherOrder">
                                    <jsp:useBean id="taxiDispatcherOrder" scope="page" type="com.taxi.speedy.company.model.TaxiDispatcherOrder"/>
                                    <form:option  value="${taxiDispatcherOrder.id}" label="${taxiDispatcherOrder.id}"/>
                                </c:forEach>
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
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${toaAllTaxiDispatcherOrders}" var="taxiUserOrder">
                                    <jsp:useBean id="taxiUserOrder" scope="page" type="com.taxi.speedy.company.model.TaxiUserOrder"/>
                                    <form:option  value="${taxiUserOrder.id}" label="${taxiUserOrder.id}"/>
                                </c:forEach>
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
                    <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                            <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/taxiOrderAcceptances/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/CompanySpeedyTaxi/taxiOrderAcceptances" class="btnLogin"><spring:message code="app.ok"/></a>
                    <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/taxiOrderAcceptances'"/>--%>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty taxiOrderAcceptance.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty taxiOrderAcceptance.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>
</body>
</html>
