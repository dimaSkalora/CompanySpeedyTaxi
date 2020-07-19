<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
    <title>UserVehicle</title>
</head>
<body>

<c:url var="createOrUpdateUrl" value="/userVehicles/createOrUpdateHSR" />
<form method="POST" action="${createOrUpdateUrl}"
           class="box userVehicle">

    <fieldset class="boxBody">
        <table>
            <tr>
                <td>
                    <label>
                        <spring:message text="id"/>
                    </label>
                </td>
                <td>
                    <input type="number" name="id" value="${param.id}" readonly="true" disabled="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <spring:message text="startDate"/>
                    </label>
                </td>
                <td>
                    <input type="datetime-local" name="startDate" value="${param.startDate}">
                </td>
            </tr>
            <tr>
                <td>
                    <label >
                        <spring:message text="endDate"/>
                    </label>
                </td>
                <td>
                    <input type="datetime-local" name="endDate" value="${param.endDate}">
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <spring:message text="idUser"/>
                    </label>
                </td>
                <td>
                    <select name="idUser" value="${param.idUser}">
                       <option  value="" label=""/>
                        <c:forEach items="${uvAllUsers}" var="user">
                            <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
                            <option label="${user.name} ${user.email}" value="${user.id}"/>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <spring:message text="idVehicle"/>
                    </label>
                </td>
                <td>
                     <select name="idVehicle" value="${param.idVehicle}">
                        <option  value="" label=""/>
                        <c:forEach items="${uvAllVehicles}" var="vehicle">
                            <jsp:useBean id="vehicle" scope="page" type="com.taxi.speedy.company.model.Vehicle"/>
                            <option value="${vehicle.id}" label="${vehicle.nameCar}; ${vehicle.vehicleNumber}"/>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
    </fieldset>

    <footer>
                <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
    </footer>
</form>
<%--
<c:url var="createOrUpdateUrl" value="/userVehicles/createOrUpdate" />
<form:form method="POST" modelAttribute="userVehicle" action="${createOrUpdateUrl}"
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
              &lt;%&ndash;      <form:select path="idUser">
                        <form:option  value="" label=""/>
                        <c:forEach items="${uvAllUsers}" var="user">
                            <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
                            <form:option  value="${user.id}" label="${user.name}; ${user.email}"/>
                        </c:forEach>
                    </form:select>&ndash;%&gt;
                &lt;%&ndash;  <form:hidden path="idUser" />&ndash;%&gt;
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="idVehicle">
                        <spring:message text="idVehicle"/>
                    </form:label>
                </td>
                <td>
      &lt;%&ndash;              <form:select path="idVehicle">
                        <form:option  value="" label=""/>
                        <c:forEach items="${uvAllVehicles}" var="vehicle">
                            <jsp:useBean id="vehicle" scope="page" type="com.taxi.speedy.company.model.Vehicle"/>
                            <form:option  value="${vehicle.id}" label="${vehicle.nameCar}; ${vehicle.vehicleNumber}"/>
                        </c:forEach>
                    </form:select>&ndash;%&gt;
         &lt;%&ndash;         <form:hidden path="idVehicle" />&ndash;%&gt;
                </td>
                <form:input path="endDatesss" />
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
            &lt;%&ndash;        <p>${requestScope.get("javax.servlet.forward.context_path")}</p>
                    <p>${requestScope.get("javax.servlet.forward.servlet_path")}</p>&ndash;%&gt;
        <c:set var="jsf_request_uri" value="${requestScope.get(\"javax.servlet.forward.request_uri\")}"/>
        <c:set var="jsf_request_uriStr" value="/CompanySpeedyTaxi/userVehicles/get"/>
        <c:if test="${jsf_request_uri.startsWith(jsf_request_uriStr)}">
            <a href="/CompanySpeedyTaxi/userVehicles" class="btnLogin"><spring:message text="OK"/></a>
            &lt;%&ndash;<input class ="btnLogin" type="button" value="<spring:message text="OK2"/>" onclick="location.href = '/CompanySpeedyTaxi/userVehicles'"/>&ndash;%&gt;
        </c:if>
        <c:if test="${!jsf_request_uri.startsWith(jsf_request_uriStr)}">
            <c:if test="${!empty userVehicle.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="UPDATE"/>"/>
            </c:if>
            <c:if test="${empty userVehicles.id}">
                <input type="submit" class="btnLogin" value="<spring:message text="SAVE"/>"/>
            </c:if>
        </c:if>
    </footer>
</form:form>--%>
</body>
</html>
