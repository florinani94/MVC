

<%@ page session="false" %>
<html>
<head>
    <title>Create product</title>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    <style type="text/css">

        .form-control {
            width: 100px;

        }

        .page-header {
            text-align: left;
            margin: 40px;
        }
    </style>


    <style>
        .error
        {
            color: #ff0000;
            font-weight: bold;
        }
    </style>

</head>
<body>

<jsp:include page="backofficeHeader.jsp" />

<c:url var="newProdUrl" value="/backoffice/product/add"></c:url>
<c:url var="viewProdUrl" value="/backoffice/product"></c:url>
<c:url var="javaScriptUrl" value="/resources/js/myscript.js"></c:url>

<div class="col-md-3 col-md-offset-5"><h1> Create Product </h1></div>

<div class="layout-edit">

    <form:form commandName="product" method="POST" action="${editUrl}" cssClass="form-horizontal registrationForm" enctype="multipart/form-data">
        <div class="form-group">
            <label for="code" class="control-label col-xs-2" style = "margin-left: 400px">Code: </label>
            <div class="col-xs-3">
                <form:input  path="code" maxlength="8" cssClass="form-control"/>
                <form:errors path="code" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="control-label col-xs-2" style = "margin-left: 400px">Name: </label>
            <div class="col-xs-3">
                <form:input  path="name" cssClass="form-control"/>
                <form:errors path="name"  cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="control-label col-xs-2" style = "margin-left: 400px">Description: </label>
            <div class="col-xs-3">
                <form:input  path="description" cssClass="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="price" class="control-label col-xs-2" style = "margin-left: 400px">Price: </label>
            <div class="col-xs-3">
                <form:input type="text" path="price" cssClass="form-control"/>
                <form:errors path="price"  cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label for="stockLevel" class="col-sm-2 control-label" style = "margin-left: 400px">Stock: </label>
            <div class="col-xs-3">
                <form:input type="text" path="stockLevel" cssClass="form-control"/>
                <form:errors path="stockLevel" cssClass="error" />
            </div>
        </div>
        <div class="form-group" style = "margin-left: 460px">
            <div class="col-xs-offset-2 col-xs-10">
                <b> Choose a category: </b> <br><br>
                <select name="categoryId" id="dropDown">
                    <c:forEach var="categoryItem" items="${allCategories}">
                        <option value="${categoryItem.id}">${categoryItem.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group" style = "margin-left: 460px">
            <div class="col-xs-offset-2 col-xs-10">
                <b>Select a image (.jpg)</b> <br><br>
                <input  name="image" type="file" accept=".jpg"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <input type="submit" value="Save" class="btn btn-lg btn-primary" style = "margin-left: 400px"/>
            </div>
        </div>
    </form:form>
</div>

<script type="application/javascript" src="${javaScriptUrl}"></script>

</body>
</html>

