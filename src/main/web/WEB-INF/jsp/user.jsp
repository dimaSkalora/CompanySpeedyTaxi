<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<p>Hello ${user.name}
</p>
<br>
<p> ${pageContext.request.requestURI}</p>
<p> ${pageContext.request.requestURL}</p>
<p> ${pageContext.request.requestedSessionIdFromURL}</p>
<%--<p> ${pageContext.request.requestedSessionIdFromURI}</p>--%>
<p>${pageContext.request.requestURI}</p>
<br>
<%--<p> ${param}</p>
<p> ${paramValues}</p>
<p> ${header}</p>
<p> ${headerValues}</p>
<p> ${cookie}</p>
<p> ${initParam}</p>--%>
<br>
<p> ${pageScope}</p>
<p> ${requestScope.get("javax.servlet.forward.request_uri")}</p>
<p> ${sessionScope}</p>
<p> ${applicationScope}</p>
<%--
<p>${requestScope('javax.servlet.forward.request_uri')}</p>--%>
<%--
<p>
    <%= request.getContextPath() %>
    <%= request.getRequestURI() %>
    <%= request.getRequestURL() %>
</p>
--%>

<%--<c:set var="pageUrl" scope="request">
    <c:out value="${pageContext.request.scheme}://${pageContext.request.serverName}"/>
    <c:if test="${pageContext.request.serverPort != '80'}">
        <c:out value=":${pageContext.request.serverPort}"/>
    </c:if>
    <c:out value="${requestScope['javax.servlet.forward.request_uri']}"/>
</c:set>--%>
<p> ${requestScope.get("javax.servlet.forward.request_uri")}</p>
<br>
<c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
<br>
<p> var jsf_request_uri  ${jsf_request_uri}</p>
<br>
<c:set var="jsf_context_path" value="${requestScope.get(\"javax.servlet.forward.context_path\")}"/>
<br>
<p> var jsf_context_path  ${jsf_context_path}</p>
<br>
<c:set var="jsf_servlet_path" value="${requestScope.get(\"javax.servlet.forward.servlet_path\")}"/>
<br>
<p> var jsf_servlet_path  ${jsf_servlet_path}</p>
<br>

<br>
<br>
<c:if test="${requestScope.get(\"javax.servlet.forward.request_uri\") == \"/CompanySpeedyTaxi/users/user\"} ">
    <%--<c:if test="${12==12}">--%>

    <c:out value="4545413511"/>

</c:if>
<br>

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
            </table>
        </fieldset>

        <footer>
            <c:set var="jsf_request_uri1" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
            <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/users/user"/>
            <c:if test="${jsf_request_uri1.equals(jsf_request_uriStr)}">
                <c:out value="4545413511"/>
            </c:if>
            <c:out value="${jsf_request_uri1.equals(\"/CompanySpeedyTaxi/users/user\")} "/>
            <c:out value="${requestScope.get(\"javax.servlet.forward.request_uri\")== \"/CompanySpeedyTaxi/users/user\"}"/>
            <c:if test="${jsf_request_uri1.equals(jsf_request_uriStr)}">

            <c:out value="4545413511"/>
                    <input type="submit" class="btnLogin" value="<spring:message text="SAVE52"/>"/>

                </c:if>
   <%--         <c:if test="${!empty user.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
            </c:if>
            <c:if test="${empty user.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
            </c:if>--%>

        </footer>


    </form:form>
</body>
</html>
