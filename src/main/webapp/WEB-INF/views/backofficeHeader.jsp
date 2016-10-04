<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false" %>
<html>
<head>
    <c:url var="cssUrl" value="/resources/style/ProjectStyle.css"/>
    <c:url var="listURL" value="/backoffice/product/"/>
    <c:url var="importURL" value="/backoffice/product/import"/>
    <c:url var="addURL" value="/backoffice/product/add"/>
    <c:url var="listCategoryURL" value="/backoffice/category/"/>
    <c:url var="createCategoryURL" value="/backoffice/category/add"/>
    <c:url var="sortProductsByPriceUpDown" value="/backoffice/product/sortpriceupdown"/>
    <c:url var="productsURL" value="/products"/>

    <c:url var="logoutUrl" value="/j_spring_security_logout"/>
    <c:url var="runJob" value="/backoffice/product/job"/>


    <link rel="stylesheet" type="text/css" href="${cssUrl}">

    <jsp:include page="bootstrapImports.jsp" />

    <title>Home</title>
</head>
<body>
<header>

    <div class="row back">
        <div class = "col-md-6">
            <h2 class = "bigTitle">Tea Shop Back Office</h2>
            <h4 style="color: #FFFFFF; padding-left: 1%; font-family: 'Calibri';"> Logged in as: <sec:authentication property="name"/> | <a href="${logoutUrl}">Logout</a> </h4>
        </div>

        <div class = "col-md-6">
            <ul>
                <li><a href="${runJob}">Start/Stop Job</a></li>
                <li><a href="${createCategoryURL}">Create category</a></li>
                <li><a href="${listCategoryURL}">View categories</a></li>
                <li><a href="${addURL}">Create product</a></li>
                <li><a href="${listURL}">View products</a></li>
                <li><a href="${productsURL}">Customer view</a></li>
            </ul>



        </div>
    </div>

</header>
</body>
</html>
