<%--
  Created by IntelliJ IDEA.
  User: mateimihai
  Date: 7/13/2016
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View categories</title>

</head>
<body>

<jsp:include page="backofficeHeader.jsp" />

<c:if test="${not empty allCategories}">

    <div class="row">
        <div class="col-md-3 col-md-offset-5">
            <h1>Categories</h1>
        </div>
    </div>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <table id="categoriesTable" class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="category" items="${allCategories}">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.description}</td>
                    <td><a href="<c:url value="/backoffice/category/edit/${category.id}" />" methods="get"><input type="submit" value="Edit" class="btn btn-primary"></a></td>
                    <td><a href="<c:url value="/backoffice/category/delete?id=${category.id}" />" methods="get"><input type="submit" value="Delete" class="btn btn-danger"></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</c:if>

<c:url var="addCategory" value="/backoffice/category/add"/>

<c:if test="${empty allCategories}">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h1>No category yet! Try adding one.</h1>
            <br><br>
            <a href="${addCategory}" methods="GET"><input type="submit" value="Create new category" class="btn btn-default"></a>
        </div>
    </div>

</c:if>

<c:if test="${not empty allCategories}">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <br><br>
            <a href="${addCategory}" methods="GET"><input type="submit" value="Create new category" class="btn btn-default"></a>
        </div>
    </div>
</c:if>


</body>
</html>
