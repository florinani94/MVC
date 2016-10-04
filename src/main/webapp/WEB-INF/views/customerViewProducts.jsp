<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:url var="cssUrl" value="/resources/style/PaginatorStyle.css"></c:url>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">

    <c:url var="cssUrlHead" value="/resources/style/detailViewStyle.css"></c:url>
    <link rel="stylesheet" type="text/css" href="${cssUrlHead}">


    <c:url var="bkgURL2" value="/resources/detailView/Background2.jpg"></c:url>

    <title>Products</title>
</head>

<body style="background-image:url(${bkgURL2});background-repeat: no-repeat; background-size: 130%; )">

    <jsp:include page="customerHeader.jsp" />
<div class="row">
    <div class="col-md-2">
            <div class="filter">
                <br><br><br>
                <b>Filter by category</b>

                <br><br>
                <c:if test="${not empty categories}">
                    <div id="allCategories">
                        <c:forEach var="category" items="${categories}">
                            <input type="checkbox" name="category" value=${category.id} id="${category.name}" onchange="applySelectedFilter(this)"/>
                            <label for="${category.name}">${category.name}</label>
                            <br>
                        </c:forEach>
                    </div>
                </c:if>

                <br><br>

                <c:url var="filterCss" value="/resources/style/FilterProductStyle.css"></c:url>
                <link rel="stylesheet" type="text/css" href="${filterCss}">

                <div id="selectedCategories"></div>
            </div>
        </div>
    <div class="col-md-8">
        <div class="container">
            <%--<div class="rigthDiv">--%>
                <div class = "sortBox">
                    <div class="row">
                        <div class="col-md-4">
                            <form>
                                <select name="sortValue" id="sort" class="form-control">
                                    <option value="none">Sort By</option>
                                    <option value="sortpriceupdown">Price Cheap to Expensive</option>
                                    <option value="sortpricedownup">Price Expensive to Cheap</option>
                                    <option value="sortnameaz">Name A to Z</option>
                                    <option value="sortnameza">Name Z to A</option>
                                </select>

                                 <input type="button" value="Sort" onclick="sortProduct()" class="btn btn-default sortButton"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">

                    <div class="squareContainer" id="products">
                    <c:if test="${not empty products}">
                        <c:forEach var="product" items="${products}">

                            <c:url var="productLink" value="/products/view?productId=${product.productId}"></c:url>

                            <div class="col-md-3">
                                <div class="square">
                                    <c:url var="img" value="${product.imageURL}" />

                                    <c:url var="image" value="${product.imageURL}"></c:url>
                                    <a href="${productLink}"><img class="frontImage" src="${image}"></a>
                                    <div><h3 class="productName">${product.name}</h3></div>
                                    <div><h4 class="productPrice">Price: ${product.price} $</h4></div>
                                    <div class="row">
                                        <div class="botT">
                                            <c:if test="${product.stockLevel gt 0}">
                                                <span class="labelStock label label-success">In stock!</span>
                                            </c:if>
                                            <c:if test="${product.stockLevel lt 1}">
                                                <span class="labelStock label label-warning">Out of stock!</span>
                                            </c:if>
                                        </div>
                                        <div class="inspectProductButton">
                                            <a href="${productLink}" class="btn btn-success" role="button">Details</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                </div>
                <div class="col-md-3">
                </div>
                <div class="row" style="margin-bottom: 10%">
                    <div class="col-md-12">
                        <c:set var="nrPages" value="${productSize div 12}"/>
                        <c:set var="dateParts" value="${fn:split(nrPages, '.')}" />
                        <c:set var="nrPagesInt" value="${dateParts[0]}" ></c:set>

                        <c:if test="${dateParts[1]!=\"0\"}">
                            <c:set var="nrPagesInt" value="${nrPagesInt+1}"/>
                        </c:if>

                        <div class="paginationView">
                            <c:if test="${not empty products}">
                                <c:if test="${currentPage > 1}">
                                    <a href="<c:url value='/products?page=1&sortValue=${sortValue}${selectedCategoriesUrl}'/>" methods="GET">1</a>
                                </c:if>
                                <c:if test="${currentPage-1 > 2}">
                                    ...
                                </c:if>
                                <c:if test="${currentPage != 1 && currentPage-1 != 1}">
                                    <a href="<c:url value='/products?page=${currentPage-1}&sortValue=${sortValue}${selectedCategoriesUrl}'/>" methods="GET">${currentPage-1}</a>
                                </c:if>
                                <a href="<c:url value='/products?page=${currentPage}&sortValue=${sortValue}${selectedCategoriesUrl}'/>" methods="GET">${currentPage}</a>

                                <c:if test="${currentPage < nrPagesInt-1}">
                                    <a href="<c:url value='/products?page=${currentPage+1}&sortValue=${sortValue}${selectedCategoriesUrl}'/>" methods="GET">${currentPage+1}</a>
                                </c:if>
                                <c:if test="${currentPage+1 < nrPagesInt-1}">
                                    ...
                                </c:if>
                                <c:if test="${currentPage != nrPagesInt}">
                                    <a href="<c:url value='/products?page=${nrPagesInt}&sortValue=${sortValue}${selectedCategoriesUrl}'/>" methods="GET">${nrPagesInt}</a>
                                </c:if>
                            </c:if>
                        </div>

                    </div>
                    </div>

            <%--</div>--%>
        </div>
    </div>
</div>
    <script type="text/javascript" src="/mvc/resources/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/mvc/resources/js/productSort.js"></script>
    <script type="text/javascript" src="/mvc/resources/js/productFilter.js"></script>
    <jsp:include page="customerFooter.jsp"/>

</body>
</html>
