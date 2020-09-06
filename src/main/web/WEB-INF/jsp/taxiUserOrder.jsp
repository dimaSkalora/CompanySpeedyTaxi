<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi User Order</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/taxiUserOrders/createOrUpdate" />
        <form:form method="POST" modelAttribute="taxiUserOrder" action="${createOrUpdateUrl}"
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
                            <form:label path="idTaxiDispatcher">
                                <spring:message code="taxiDispatcherOrder.idTaxiDispatcher"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="idUser">
                                <form:option  value="${!empty taxiUserOrder.id ? taxiUserOrder.idUser.id : ''}"
                                              label="${!empty taxiUserOrder.id ? taxiUserOrder.idUser.name : ''}"/>
                                <%--<form:option  value="${taxiUserOrder.id}" label="${taxiUserOrder.idUser.name }"/>--%>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${allUsers}" var="user">
                                    <jsp:useBean id="useruser" scope="page" type="com.taxi.speedy.company.model.User"/>
                                    <form:option  value="${user.id}" label="${user.name}"/>
                                </c:forEach>
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
                    <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                            <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/taxiUserOrders/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/CompanySpeedyTaxi/taxiUserOrders" class="btnLogin"><spring:message code="app.ok"/></a>
                    <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/taxiUserOrders'"/>--%>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty taxiUserOrder.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty taxiUserOrder.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>
</body>
</html>
