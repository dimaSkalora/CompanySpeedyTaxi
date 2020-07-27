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
    <title>User</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<p>Hello ${user.name}
</p>

<c:url var="createOrUpdateUrl" value="/users/createOrUpdate" />
    <form:form method="POST" modelAttribute="user" action="${createOrUpdateUrl}"
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
                        <form:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">
                            <spring:message text="password"/>
                        </form:label>
                    </td>
                    <td>
                        <form:password path="password" />
                    </td>
                    <td>
                        <form:label path="email">
                            <spring:message text="email"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="email" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="phone">
                            <spring:message text="phone"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="phone" />
                    </td>
                    <td>
                        <form:label path="address">
                            <spring:message text="address"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="address" />
                    </td>
                </tr>
                <c:if test="${!empty user.name}">
                    <tr>
                        <td>
                            <form:label path="registered">
                                <spring:message text="registered"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="registered" />
                        </td>
                        <td>
                            <form:label path="enabled">
                                <spring:message text="enabled"/>
                            </form:label>
                        </td>
                        <td>
                            <form:checkbox path="enabled" />
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
            <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
            <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/users/get"/>
            <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
                <a href="/CompanySpeedyTaxi/users" class="btnLogin"><spring:message text="OK"/></a>
                <%--<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/users'"/>--%>
            </c:if>
            <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
                <c:if test="${!empty user.id}">
                    <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
                </c:if>
                <c:if test="${empty user.id}">
                    <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
                </c:if>
            </c:if>
        </footer>
    </form:form>
</body>
</html>
