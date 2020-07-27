<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="/" class="navbar-brand">index</a>
        <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">List<b class="caret"></b></a>
                        <ul id="menu1" class="dropdown-menu">
                            <li><a href="users">users</a></li>
                            <li><a href="vehicles">vehicles</a></li>
                            <li><a href="userVehicles">userVehicles</a></li>
                        </ul>
                    </li>
                </ul>
        </div>
    </div>
</div>