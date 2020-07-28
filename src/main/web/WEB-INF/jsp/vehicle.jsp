<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/vehicles/createOrUpdate" />
        <form:form method="POST" modelAttribute="vehicle" action="${createOrUpdateUrl}"
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
                            <form:input path="nameCar"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="vehicleNumber">
                                <spring:message text="vehicleNumber"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="vehicleNumber" />
                        </td>
                        <td>
                            <form:label path="yearIssue">
                                <spring:message text="yearIssue"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="yearIssue" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="category">
                                <spring:message text="category"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="category" />
                        </td>
                        <td>
                            <form:label path="color">
                                <spring:message text="color"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="color" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="fuelConsumption">
                                <spring:message text="fuelConsumption"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="fuelConsumption"/>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                    <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                            <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/vehicles/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/CompanySpeedyTaxi/vehicles" class="btnLogin"><spring:message text="OK"/></a>
                    <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/vehicles'"/>--%>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty vehicle.id}">
                        <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
                    </c:if>
                    <c:if test="${empty vehicle.id}">
                        <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>
</body>
</html>
