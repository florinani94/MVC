<%--
  Created by IntelliJ IDEA.
  User: dianamohanu
  Date: 18/07/2016
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<c:url var="detailsCSSURL" value="/resources/style/detailViewStyle.css"/>
<link rel="stylesheet" type="text/css" href="${detailsCSSURL}">

<c:url var="signIn" value="/login"/>
<c:url var="backgroundURL" value="/resources/detailView/Background2.jpg"/>

<head>
    <title>Register</title>

    <style>
        .error {
            color: #ff0000;
        }
    </style>

</head>

<body background="${backgroundURL}" style="background-size: 100%">
<jsp:include page="customerHeader.jsp" />

<br>

<div class="col-md-6 col-md-offset-4">
    <h1>REGISTER</h1>
    <h3>Create back-office user account</h3>
    <br>

    <p class="error">${passwordError}</p>

    <form:form commandName="user" action="register" method="post">
        <div class="row">
            <div class="form-group col-lg-5">
                <label for="email">Email:</label>
                <form:input type="email" class="form-control" path="email" id="email" placeholder="myemail@example.com"/>
                <form:errors path="email" cssClass="error" />
                <p class="error">${emailError}</p>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-lg-5">
                <label for="username">Username:</label>
                <form:input type="text" class="form-control" path="username" id="username" placeholder="user1"/>
                <form:errors path="username" cssClass="error" />
                <p class="error">${usernameError}</p>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-lg-5">
                <label for="pass1">Password:</label>
                <form:input type="password" class="form-control" path="password" id="pass1" placeholder="Enter password"/>
                <form:errors path="password" cssClass="error" />
                <span class="help-block">Password must be at least 6 characters.</span>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-lg-5">
                <label for="pass2">Confirm Password:</label>
                <input type="password" class="form-control" name="pass2" id="pass2" placeholder="Confirm password">
            </div>
        </div>

        <c:url var="path" value="/confirm"/>
        <input type="hidden" name="path" value=${path}>

        <button type="submit" class="btn btn-success">Create Account</button>
    </form:form>

    <br>
    <p>
        Already a back-office user? <a href="${signIn}">Sign In.</a>
    </p>

    <h4 style="color: #32CD32;">${message1}</h4>
    <h4 style="color: #32CD32;">${message2}</h4>
</div>

<c:url var="jqueyUrl" value="/resources/jquery-1.8.3.js"/>
<script type="text/javascript" src="${jqueyUrl}"></script>

<c:url var="jsUrl" value="/resources/js/myscript.js"/>
<script type="text/javascript" src="${jsUrl}"></script>

<jsp:include page="customerFooter.jsp"/>

</body>
</html>

