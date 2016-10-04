<%--
  Created by IntelliJ IDEA.
  User: iuliacodau
  Date: 25/07/2016
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>

    <c:url var="cssUrl" value="/resources/style/PaginatorStyle.css"></c:url>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <c:url var="cssUrlHead" value="/resources/style/detailViewStyle.css"></c:url>
    <link rel="stylesheet" href="${cssUrlHead}">
    <c:url var="bkgURL2" value="/resources/detailView/Background2.jpg"></c:url>

    <title>Order Placed</title>
</head>

<body style="background-image:url(${bkgURL2});background-repeat: repeat; background-size: 100%; )">

<jsp:include page="customerHeader.jsp"/>
<c:url var="backUrl" value="/products"></c:url>
<c:url var="orderPlacedUrl" value="/products/customerOrderPlaced"></c:url>

<br>

<div class="col-md-3"></div>

<div class="col-md-4">

    <div class="row">
        <h1 id="productTitle"> Submit order </h1>
        <h3 style="font-family: Calibri; color: rgb(114, 114, 114);"> You're delivery, billing and payment
            information have been saved. Please click the below button to finalize your order! After that, you'll
            receive an email summarizing your order. </h3>
    </div>

    <div class="row">
        <input type="submit" class="checkoutButton" id="goToFinalizeOrder" value="COMPLETE ORDER">
    </div>
</div>

<div class="col-md-5"></div>

<script type="text/javascript">
    $("#goToFinalizeOrder").click(function () {
        window.location.href = contextURL + "products/customerOrderPlaced/?cartId=" + idCart;
    });
</script>

<c:url var="jqueyUrl" value="/resources/jquery-1.8.3.js"/>
<script type="text/javascript" src="${jqueyUrl}"></script>

<c:url var="jsUrl" value="/resources/js/myscript.js"/>
<script type="text/javascript" src="${jsUrl}"></script>

<c:url var="jsUrl" value="/resources/js/myscript.js"/>
<script type="text/javascript" src="${jsUrl}"></script>

<jsp:include page="customerFooter.jsp"/>

</body>
</html>
