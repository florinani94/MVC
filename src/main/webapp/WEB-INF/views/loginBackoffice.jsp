<%--
  Created by IntelliJ IDEA.
  User: dianamohanu
  Date: 19/07/2016
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <c:url var="backgroundURL" value="/resources/detailView/Background2.jpg"/>

    <c:url var="detailsCSSURL" value="/resources/style/detailViewStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${detailsCSSURL}">

    <c:url var="registerNow" value="/register"/>
<head>
    <title>Login</title>
    <style type="text/css">
        .errorblock {
            color: #ff0000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>

<body  background="${backgroundURL}" style="background-size: 100%" onload='document.f.j_username.focus();'>
<jsp:include page="customerHeader.jsp" />

<br>

<div class="row-fluid">
    <div class="col-sm-4"></div>

    <div class="col-sm-4">
        <h1>Login as back-office user</h1>

        <c:if test="${not empty error}">
            <div class="errorblock">
                Your login was unsuccessful. <br/>
                Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
            </div>
        </c:if>

        <form action="j_spring_security_check" name="f" method="post">
            <div class="row">
                <div class="form-group col-lg-5">
                    <label>Username:</label>
                    <input type="text" name="j_username" value="" class="form-control">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-lg-5">
                    <label>Password:</label>
                    <input input type="password" name="j_password" class="form-control">
                </div>
            </div>

            <input type="submit" class="btn btn-success" name="Submit" value="Submit">
            <input type="reset" class="btn btn-success" name="reset">
        </form>

        <br>
        <p>
            Not a back-office user? <a href="${registerNow}">Create account now!</a>
        </p>
    </div>

    <div class="col-sm-4"></div>
</div>

<jsp:include page="customerFooter.jsp"/>

</body>


</html>