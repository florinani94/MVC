<%--
  Created by IntelliJ IDEA.
  User: dianamohanu
  Date: 21/07/2016
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:url var="detailsCSSURL" value="/resources/style/detailViewStyle.css"/>
<link rel="stylesheet" type="text/css" href="${detailsCSSURL}">

<c:url var="backgroundURL" value="/resources/detailView/Background2.jpg"/>
<head>
    <title>Logout</title>
</head>

<body background="${backgroundURL}" style="background-size: 100%">
<jsp:include page="customerHeader.jsp" />

<div class="row-fluid">
    <div class="col-sm-4"></div>

    <div class="col-sm-4" id="confirmInfo">
        <div class="row-fluid" style="margin-top: 10%">
            <h1 class="pageText" id="productTitle">Good bye!</h1>
            <div class="myHr"></div>
            <table class="detailsText" id="confirmDetails">
                <tr>
                    <td><h2 style="color: #808080; font-family: 'Calibri';">You have been logged out! Thanks for using our app.</h2></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="col-sm-4"></div>
</div>

<jsp:include page="customerFooter.jsp"/>

</body>
</html>
