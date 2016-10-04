<%--
  Created by IntelliJ IDEA.
  User: dianamohanu
  Date: 25/07/2016
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<c:url var="detailsCSSURL" value="/resources/style/detailViewStyle.css"/>
<link rel="stylesheet" type="text/css" href="${detailsCSSURL}">

<c:url var="backgroundURL" value="/resources/detailView/Background2.jpg"/>

<head>
    <title>My Cart</title>

    <style>
        #entryTitle {
            font-family: Calibri;
            color: rgb(114, 114, 114);
            font-size: 22px;
            text-decoration: underline;
            margin-bottom: 13px;
        }

        a:not([href]) {
            font-family: Calibri;
            color: rgb(204, 102, 0);
            text-decoration: underline;
            font-size: 20px;
        }

        a:not([href]):hover {
            font-family: Calibri;
            cursor: pointer;
            color: rgb(153, 77, 0);
            font-size: 20px;
        }

        #stockLabel {
            font-family: Calibri;
            color: rgb(102, 153, 0);
        }

        #outOfStockLabel {
            font-family: Calibri;
            color: rgb(153, 0, 0);
        }

        th, td {
            color: rgb(114, 114, 114);
            font-family: Calibri;
            font-size: 18px;
        }

        .row-buffer {
            margin-top: 15px;
            background-color: #FFFFFF;
            height: 22%;
        }

        .row-bottom {
            margin-top: 15px;
            height: 6%;
            font-family: Impact;
            text-align: right;
            padding-right: 5%;
            background-color: rgb(242, 242, 242);
        }

        .productsButton {
            background-color: rgb(255, 133, 51);
            border: none;
            color: white;
            padding: 10px 45px;
            text-decoration: none;
            font-size: 20px;
            margin-top: 5px;
            margin-bottom: 15%;
        }
    </style>

</head>
<body background="${backgroundURL}" style="background-size: 100%;">
<jsp:include page="customerHeader.jsp"/>

<br>
<div class="row-fluid">
    <div class="col-md-3"></div>

    <div class="col-md-4">
        <h1 id="productTitle"> My Cart </h1>

        <c:forEach var="entry" items="${entries}">
            <div class="row row-buffer">
                <h4 id="entryTitle" style="margin-left: 5%">${entry.productName}</h4>

                <div class="col-md-3">
                    <c:url var="img" value="${entry.product.imageURL}"/>
                    <img src="${img}" style="height: 140px; width: 204px;">
                </div>

                <div class="col-md-9">
                    <table style="width: 70%; margin-left: 15%;">
                        <tr>
                            <th>CODE</th>
                            <th>PRICE</th>
                            <th>QUANTITY</th>
                            <th>ITEM TOTAL</th>
                        </tr>
                        <tr>
                            <td>${entry.productCode}</td>
                            <td>${entry.productPrice}</td>

                            <td><select id="quantityOptions${entry.entryId}">
                                <c:forEach begin="1" end="${entry.product.stockLevel}" var="val">
                                    <c:if test="${val == entry.quantity}">
                                        <option selected>${val}</option>
                                    </c:if>

                                    <c:if test="${val != entry.quantity}">
                                        <option>${val}</option>
                                    </c:if>
                                </c:forEach>
                            </select></td>

                            <td id="subTotal${entry.entryId}">${entry.subTotal}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><a id="editEntryBtn${entry.entryId}" onclick="editfunction(${entry.entryId})">Update</a>
                            </td>
                        </tr>
                        <tr>
                            <c:if test="${entry.product.stockLevel != 0}">
                                <td colspan="4"><h4 id="stockLabel"><strong>In Stock & Ready To Ship</strong></h4></td>
                            </c:if>

                            <c:if test="${entry.product.stockLevel == 0}">
                                <td colspan="4"><h4 id="outOfStockLabel"><strong>Out Of Stock</strong></h4></td>
                            </c:if>
                        </tr>
                        <tr>
                            <td><a id="removeEntryBtn${entry.entryId}"
                                   onclick="deletefunction(${entry.entryId})">Remove</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </c:forEach>

        <div class="row row-bottom">
            <h2 id="totalCartPrice">Cart total: ${total}$</h2>
        </div>

        <div class="row">
            <c:forEach var="message" items="${stockErrorMessage}">
                <h4 style="color: rgb(153, 0, 0);"><strong>${message}</strong></h4>
            </c:forEach>
        </div>

        <div class="row">
            <div id="buttonsDiv">
                <c:if test="${total != 0}">
                    <input type="submit" class="checkoutButton" id="goToCheckout" value="CHECKOUT" onclick="window.location.href = contextURL + 'products/checkout?cartId=' + idCart;">
                </c:if>
                <c:if test="${total == 0}">
                    <h3 style="font-family: Impact; color: rgb(114, 114, 114);"> Your shopping cart is currently
                        empty. </h3>
                    <input type="submit" class="productsButton" id="goToProducts" value="SHOP NOW" onclick="window.location.href = contextURL + 'products/';">
                </c:if>
            </div>
        </div>
    </div>

    <div class="col-md-5"></div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#cartIcon, #prodNr').css('pointer-events','none');
    })

    function deletefunction(val) {
        $.ajax({
            type: "POST",
            url: contextURL + "cart/removeFromCart",
            data: {
                entryId: val,
                cartId: idCart
            },
            success: function (response) {
                $("#removeEntryBtn" + val).parent().parent().parent().parent().parent().parent().remove();
                $("#totalCartPrice").load(contextURL + "cart/?cartId=" + idCart +" #totalCartPrice");

                $("#buttonsDiv").load(contextURL + "cart/?cartId=" + idCart +" #buttonsDiv");

                refreshCartProductsNumber(idCart);
                console.log("success");
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

    function editfunction(val) {
        var quantity = $('#quantityOptions' + val).find(":selected").text();

        $.ajax({
            type: "POST",
            url: contextURL + "cart/edit",
            data: {
                entryId: val,
                cartId: idCart,
                newQuantity: quantity
            },
            success: function (response) {
                $("#subTotal" + val).load(contextURL + "cart/?cartId=" + idCart + " #subTotal" + val);
                $("#totalCartPrice").load(contextURL + "cart/?cartId=" + idCart + " #totalCartPrice");
                refreshCartProductsNumber(idCart);
                console.log("success");
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }

</script>

<jsp:include page="customerFooter.jsp"/>

</body>
</html>
