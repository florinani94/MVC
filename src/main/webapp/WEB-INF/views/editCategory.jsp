<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>

    <c:url var="cssUrlBootstrapStyleModified" value="/resources/style/bootstrapStyleModified.css"></c:url>
    <link rel="stylesheet" type="text/css" href="${cssUrlBootstrapStyleModified}">

    <title>Update category</title>

</head>
<body>

<jsp:include page="backofficeHeader.jsp" />

<c:url var="editUrl" value="/backoffice/category/edit"/>

<div class="row">
    <div class="col-md-3 col-md-offset-5">
        <h1>Update Category</h1>
    </div>
</div>

<div class="layout-edit">

    <c:url var="editUrl" value="/backoffice/category/edit"></c:url>

    <form:form commandName="category" method="POST" action="${editUrl}" cssClass="form-horizontal registrationForm">
        <div class="form-group">
            <label for="id" class="control-label col-xs-2" style = "margin-left: 400px">ID: </label>
            <div class="col-sm-3 ">
                <form:input readonly="true" path="id" cssClass="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label for="name" class="control-label col-xs-2" style = "margin-left: 400px">Name: </label>
            <div class="col-sm-3">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name"  cssClass="error" />
            </div>
        </div>

        <div class="form-group">
            <label for="description" class="control-label col-xs-2" style = "margin-left: 400px">Description: </label>
            <div class="col-sm-3">
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description"  cssClass="error" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <input type="submit" value="Save" class="btn btn-lg btn-primary" style = "margin-left: 400px"/>
            </div>
        </div>
    </form:form>

</div>


</body>
</html>