<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="imageTileURL" value="${theProduct.imageURL}"/>
<c:url var="backgroundURL" value="/resources/detailView/Background2.jpg"/>
<c:url var="detailsCSSURL" value="/resources/style/detailViewStyle.css"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${detailsCSSURL}">

    <title>${theProduct.name}</title>
</head>

<body background="${backgroundURL}" style="background-size: 100%">

<jsp:include page="customerHeader.jsp" />

<input type="hidden" id="product-id" value="${theProduct.productId}"/>

<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="row" style="margin-top: 5%">
            <div class="col-md-7" >
                <img src="${imageTileURL}" class="img-responsive" alt="Picture not available" width="650" height="400" id="imageCell"/>
            </div>
            <div class="col-md-5" id="productInfo">
                <h1 class="pageText" id="productTitle">${theProduct.name}</h1>
                <div class="myHr"></div>
                    <table class="detailsText" id="productDetails">
                        <tr>
                            <td class="descriptionText">
                                <div class="categoryName">${theProduct.category.name}
                                </div>
                                ${theProduct.description}
                            </td>
                        </tr>
                        <tr>
                            <td class="priceText">${theProduct.price}$</td>
                        </tr>
                        <tr>
                            <td> <span class="qtyInput">
                                    Quantity: <input type="number" min="1" value="1" id="quantityFieldID" class="quantity">
                                </span> </td>
                        </tr>
                        <tr>
                            <td><input type="submit" class="addButton" id="addButtonID" value="Add to cart"> </td>
                        </tr>
                    </table>
            </div>
        </div>
    </div>

</div>

<jsp:include page="customerFooter.jsp"/>

</body>
</html>
