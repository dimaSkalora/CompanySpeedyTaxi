<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>Access To Route Data</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs box accessToRoute_nav_tabs">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#taxiDispatcher"><spring:message code="app.taxiDispatchers"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#user"><spring:message code="app.users"/></a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane container active" id="taxiDispatcher">
                <form:form method="POST" modelAttribute="taxiDispatcherData" class="box formTwoFields">
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
                                        <form:option  value="${!empty taxiDispatcherData.id ? taxiDispatcherData.idUser.id : ''}"
                                                      label="${!empty taxiDispatcherData.id ? taxiDispatcherData.idUser.name.concat('; ')
                                                                .concat(taxiDispatcherData.idUser.email): ''}"/>

                                    </form:select>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                    <footer>
                        <a href="/CompanySpeedyTaxi/taxiDispatchers" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>

            <div class="tab-pane container fade" id="user">
                <form:form method="POST" modelAttribute="tdUser"
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
                            <c:if test="${!empty tdUser.name}">
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
                        <a href="/CompanySpeedyTaxi/taxiDispatchers" class="btnLogin"><spring:message code="app.ok"/></a>
                    </footer>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
