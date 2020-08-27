<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Taxi Dispatcher</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="taxiDispatchers/taxiDispatcher">add Taxi Dispatcher</a> </li>
        </ul>
    </div>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th><spring:message code="taxiDispatcher.idUser"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${taxiDispatchers}" var="taxiDispatcher">
                <jsp:useBean id="taxiDispatcher" scope="page" type="com.taxi.speedy.company.model.TaxiDispatcher"/>
                <tr>
                    <td><a href="taxiDispatchers/getTaxiDispatcherData/${taxiDispatcher.id}"/>
                        <c:out value="${taxiDispatcher.idUser.name}; ${taxiDispatcher.idUser.email}"/></td>
                    <td><a href="taxiDispatchers/update/${taxiDispatcher.id}"/><spring:message code="app.update"/></td>
                    <td><a href="taxiDispatchers/delete?id=${taxiDispatcher.id}"/><spring:message code="app.delete"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
