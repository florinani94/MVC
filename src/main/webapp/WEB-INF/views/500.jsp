<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="bootstrapImports.jsp" />

    <c:url var="URL500" value="/resources/500.png"/>

    <title>404 Not found</title>
</head>
<body>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <h1 style="text-align: center">We apologise</h1>
    </div>
</div>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <img src="${URL500}" alt="500Error" width="590" height="300">
    </div>
</div>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <h3 style="text-align: center">Here are some useful links</h3>
    </div>
</div>

<div class="row">
    <div class="col-md-2 col-md-offset-5" style="text-align: center">
        <a class="btn btn-default" href="/mvc/products">Products page</a>
    </div>
</div>

</body>
</html>