<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h1><a href="users/user">add user</a></h1>
<br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>name</th>
            <th>email</th>
            <th>password</th>
            <th>phone</th>
            <th>address</th>
            <th>roles</th>
            <th>registered</th>
            <th>enabled</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
            <tr>
                <td><a href="users/get/${user.id}"/><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.phone}"/></td>
                <td><c:out value="${user.address}"/></td>
                <td><c:out value="${user.roles}"/></td>
                <td><c:out value="${user.registered}"/></td>
                <td><c:out value="${user.enabled}"/></td>
                <td><a href="users/update/${user.id}"/>update</td>
                <td><a href="users/delete?id=${user.id}"/>delete</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
