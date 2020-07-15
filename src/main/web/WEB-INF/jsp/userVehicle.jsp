<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="POST" modelAttribute="userVehicle"  action="google.com.ua">
    <table>
    <tr>
    <td>
    <ul>
    <form:select path="idUser" items="${userVehicles}"/>
    </ul>
    </td>
    </tr>
    <tr>
    <td>
    <input type="submit" value="Submit"/>
    </td>
    </tr>
    </table>
</form:form>
<br>
<form:form method="POST" modelAttribute="userVehicle"  action="google.com.ua">
    <table>
        <tr>
            <td>
                <ul>
                    <form:select path="idUser">
                        <form:option  value="-" label="--Select phone"/>
                        <form:options items="${userVehicles}" />
                    </form:select>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
<br>
<form:form method="POST" modelAttribute="userVehicle"  action="google.com.ua">
    <table>
        <tr>
            <td>
                <ul>
                    <form:select path="idUser">
                        <form:option  value="-" label="--Select phoneqqqq"/>
                        <form:options items="${userVehicles}" />
                    </form:select>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
<%--<br>
<form:select path="phone">
    <form:option  value="-" label="--Select sdssssssssssssssssssssssssss"/>
    <form:options items="${userVehicles}" />
</form:select>--%>

<br>
<form:form method="POST" modelAttribute="userVehicle"  action="google.com.ua">
    <table>
        <tr>
            <td>
                <ul>
                    <form:select path="idUser">
                        <form:options items="${userVehicles}" />
                    </form:select>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>

<br>
<form:form method="POST" modelAttribute="userVehicle"  action="google.com.ua">
    <table>
        <tr>
            <td>
                <ul>
                    <form:select path="idUser">
                        <form:option  value="" label=""/>
                        <c:forEach items="${userVehicles}" var="userVehicle">
                            <jsp:useBean id="userVehicle" scope="page" type="com.taxi.speedy.company.model.UserVehicle"/>
                             <form:option  value="${userVehicle.idUser}" label="${userVehicle.idUser.name} ${userVehicle.idUser.email}"/>
                            </c:forEach>
                    </form:select>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
    </body>
</html>
