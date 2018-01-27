<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<html>
<head>
    <meta
            http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/static/css/bootstrap.css' />"
          rel="stylesheet">
    <link href="<c:url value='/static/css/style.css' />"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,400,700" rel="stylesheet">
</head>
<body>
<div class="flex-container">
    <header>
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
    </header>
    <article class="article">
        <tiles:insertAttribute name="body" />
    </article>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>