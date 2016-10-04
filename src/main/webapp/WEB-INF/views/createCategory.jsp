<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create category</title>

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

<div class="row">
    <div class="col-md-2 col-md-offset-5">
        <h1>Create Category</h1>
    </div>
</div>

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form:form commandName="category" action="add" method="post" id="addCat" class="form-inline">
                Id: <br>
                <form:input type="text" path="id" name="id" class="form-control"/>
                <br>
                <form:errors path="id" cssClass="error" />
                <br><br>
                Name: <br>
                <form:input type="text" path="name" class="form-control"/>
                <br>
                <form:errors path="name" cssClass="error" />
                <br><br>
                Description: <br>
                <form:textarea rows="4" cols="50" path="description" form="addCat" class="form-control"/>
                <br>
                <form:errors path="description" cssClass="error" />
                <br><br>
                <input type="submit" value="Save" class="btn btn-success"/>
            </form:form>
        </div>
    </div>

<br> <br>
<c:if test="${result == true}">
    <h3>Category with the id ${category.id} was added!</h3>
</c:if>
<c:if test="${result == false}">
    <h3>You have errors! The category was not added!</h3>
</c:if>

</body>
</html>