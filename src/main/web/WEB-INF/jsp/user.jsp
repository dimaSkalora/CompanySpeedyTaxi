<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Hello ${user.name}
</p>
<br>
<br>
<br>
<br>
<form:form method="POST" modelAttribute="user" action="update">
        <form:label path="name">
           name
        </form:label>
        <form:input path="name"/><br>
        <form:label path="password">
            password
        </form:label>
        <form:password path="password"/>
        <form:errors path="password" cssClass="error" />
    <footer>
        <input type="submit"
               value="update">
    </footer>
</form:form>
</body>
</html>
