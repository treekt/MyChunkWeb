<html>
<head>
    <meta
            http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/static/css/app.css' />"
          rel="stylesheet">
    </link>
</head>
<body>
<div class="flex-container">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="menu" />
    <article class="article">
        <tiles:insertAttribute name="body" />
    </article>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>