<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Taxi Dispatcher</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:url var="createOrUpdateUrl" value="/taxiDispatchers/createOrUpdate" />
        <form:form method="POST" modelAttribute="taxiDispatcher" action="${createOrUpdateUrl}"
                   class="box formTwoFields">
            <fieldset class="boxBody">
                <table>
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message code="taxiDispatcher.id"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="id"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="idUser">
                                <spring:message code="taxiDispatcher.idUser"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="idUser">
                                <form:option  value="${!empty taxiDispatcher.id ? taxiDispatcher.idUser.id : ''}"
                                              label="${!empty taxiDispatcher.id ? taxiDispatcher.idUser.name.concat('; ')
                                                                .concat(taxiDispatcher.idUser.email): ''}"/>
                                <%--<form:option  value="${taxiDispatcher.idUser.id}" label="${taxiDispatcher.idUser.name }"/>--%>
                                <%--<form:option  value="" label=""/>--%>
                                <c:forEach items="${allUsers}" var="user">
                                    <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
                                    <form:option  value="${user.id}" label="${user.name}; ${user.email}"/>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                </table>
            </fieldset>

            <footer>
                    <%--        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                            <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>--%>
                <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
                <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/taxiDispatchers/get"/>
                <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <a href="/CompanySpeedyTaxi/taxiDispatchers" class="btnLogin"><spring:message code="app.ok"/></a>
                    <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/taxiDispatchers'"/>--%>
                </c:if>
                <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                    <c:if test="${!empty taxiDispatcher.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.update"/>"/>
                    </c:if>
                    <c:if test="${empty taxiDispatcher.id}">
                        <input type="submit" class="btnLogin" value="<spring:message code="app.save"/>"/>
                    </c:if>
                </c:if>
            </footer>
        </form:form>
    </div>
</div>
</body>
</html>
