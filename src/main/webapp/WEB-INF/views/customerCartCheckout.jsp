<%--
  Created by IntelliJ IDEA.
  User: iuliacodau
  Date: 19/07/2016
  Time: 09:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>

    <c:url var="paymentUrl" value="/resources/secure.png"></c:url>
    <c:url var="cssUrl" value="/resources/style/PaginatorStyle.css"></c:url>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
    <c:url var="cssUrlHead" value="/resources/style/detailViewStyle.css"></c:url>
    <link rel="stylesheet" href="${cssUrlHead}">
    <c:url var="bkgURL2" value="/resources/detailView/Background2.jpg"></c:url>

    <title>Checkout</title>

    <style>
        .informationTitle {
            font-family: Calibri;
            color: rgb(114, 114, 114);
            font-size: 22px;
            text-transform: uppercase;
        }
    </style>

</head>

<body style="background-image:url(${bkgURL2});background-repeat: repeat; background-size: 100%; )">

<jsp:include page="customerHeader.jsp"/>
<c:url var="addressUrl" value="/products/checkout/"></c:url>
<c:url var="backUrl" value="/products"></c:url>

<br>

<div class="col-md-3"></div>

<div class="col-md-4">
    <div class="row">
        <h1 id="productTitle"> Customer Information </h1>
    </div>

    <c:if test="${data == null || data==false}">

        <form:form commandName="order" method="POST" action="${addressUrl}" id="checkoutAddress">

            <div class="row">
                <p class="informationTitle">1. Contact Details </p>
                <label for="email">E-mail Address :</label>
                <form:input path="email" id="email" cssClass="form-control" maxlength="30" required="true"/>
                <form:errors path="email" cssClass="error"/>
                <br>
            </div>

            <div class="row">
                <p class="informationTitle">2. Delivery Address </p>

                <form:input type="hidden" readonly="true" path="cartId" cssClass="form-control" maxlength="10"
                            required="true"/>

                <label for="deliveryStreet">Street : </label>
                <br>
                <form:input path="deliveryStreet" id="deliveryStreet" cssClass="form-control" maxlength="30"
                            required="true"/>
                <form:errors path="deliveryStreet" cssClass="error"/>
                <br>
                <label for="deliveryNumber">Number : </label>
                <br>
                <form:input path="deliveryNumber" id="deliveryNumber" cssClass="form-control" maxlength="5"
                            required="true"/>
                <form:errors path="deliveryNumber" cssClass="error"/>
                <br>
                <label for="deliveryCity">City : </label>
                <br>
                <form:input path="deliveryCity" id="deliveryCity" cssClass="form-control" maxlength="30"
                            required="true"/>
                <form:errors path="deliveryCity" cssClass="error"/>
                <br>
                <label for="deliveryPhone">Phone : </label>
                <br>
                <form:input path="deliveryPhone" id="deliveryPhone" cssClass="form-control" maxlength="30"
                            required="true"/>
                <form:errors path="deliveryPhone" cssClass="error"/>
                <br>
            </div>


            <div class="row">
                <p class="informationTitle">3. Billing Address:</p>
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapseAddress">
                            <input type="checkbox" id="same" name="same" onchange="billingFunction()"> Use the same
                            address
                        </a>
                    </h4>
                </div>
                <div class="collapse" id="collapseAddress">
                    Street: <br>
                    <form:input path="billingStreet" id="billingStreet" cssClass="form-control" maxlength="30"
                                required="true"/>
                    <form:errors path="billingStreet" cssClass="error"/>
                    <br>
                    Number: <br>
                    <form:input path="billingNumber" id="billingNumber" cssClass="form-control" maxlength="5"
                                required="true"/>
                    <form:errors path="billingNumber" cssClass="error"/>
                    <br>
                    City: <br>
                    <form:input path="billingCity" id="billingCity" cssClass="form-control" maxlength="30"
                                required="true"/>
                    <form:errors path="billingCity" cssClass="error"/>
                    <br>
                    Phone <br>
                    <form:input path="billingPhone" id="billingPhone" cssClass="form-control" maxlength="30"
                                required="true"/>
                    <form:errors path="billingPhone" cssClass="error"/>
                    <br>

                </div>
            </div>


            <div class="row">
                <p class="informationTitle">4. Payment Details</p>

                <label for="paymentMethod">Payment Method : </label>
                <br>
                <form:select path="paymentMethod" id="paymentMethod" cssClass="form-control">
                    <option value="CARD">CARD</option>
                    <option value="RAMBURS">RAMBURS</option>
                </form:select>
                <div id="cardDetails">
                    <br>
                    <label for="cardNumber">Card Number : </label>
                    <br>
                    <form:input path="cardNumber" id="deliveryStreet" cssClass="form-control" maxlength="10"/>
                    <form:errors path="cardNumber" cssClass="error"/>
                    <br>
                    <label for="cardCode">Card Code : </label>
                    <br>
                    <form:input path="cardCode" id="cardCode" cssClass="form-control" maxlength="3"/>
                    <form:errors path="cardCode" cssClass="error"/>
                    <br>
                </div>
            </div>


            <div class="row">
                <input type="submit" value="SUBMIT INFORMATION" class="checkoutButton"/>
            </div>

        </form:form>

    </c:if>
</div>

<div class="col-md-5"></div>

<c:url var="jqueyUrl" value="/resources/jquery-1.8.3.js"/>
<script type="text/javascript" src="${jqueyUrl}"></script>

<c:url var="jsUrl" value="/resources/js/myscript.js"/>
<script type="text/javascript" src="${jsUrl}"></script>

<c:url var="jsUrl2" value="/resources/js/checkout.js"/>
<script type="text/javascript" src="${jsUrl2}"></script>

<jsp:include page="customerFooter.jsp"/>

</body>
</html>
