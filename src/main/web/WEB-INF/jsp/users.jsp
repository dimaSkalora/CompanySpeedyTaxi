<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>


<br>
<br>

<p>Hello ${user.name}!</p>
<br>
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
            <th>enabled</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="com.taxi.speedy.company.model.User"/>
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.address}"/></td>
                <td><c:out value="${user.roles}"/></td>
                <td><c:out value="${user.registered}"/></td>
                <td><c:out value="${user.enabled}"/></td>
                <td><a href="/CompanySpeedyTaxi/users/get?=${user.id}"/>update</td>
                <td><a href="/users/delete?id=${user.id}"/>delete</td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>
