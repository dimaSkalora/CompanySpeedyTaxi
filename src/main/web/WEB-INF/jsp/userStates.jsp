<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Vehicles</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <ul>
            <li><a href="userStates/userState">add userState </a></li>
        </ul>
    </div>
    <div class="container">
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Название состояния</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${userStates}" var="userState">
                <jsp:useBean id="userState" scope="page" type="com.taxi.speedy.company.model.UserState"/>
                <tr>
                    <td><a href="userStates/get/${userState.id}"/><c:out value="${userState.nameUS}"/></td>
                    <td><a href="userStates/update/${userState.id}"/>update</td>
                    <td><a href="userStates/delete?id=${userState.id}"/>delete</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
