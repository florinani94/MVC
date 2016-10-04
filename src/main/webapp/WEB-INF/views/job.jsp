<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:url var="jobCss" value="/resources/style/JobStyle.css" />
    <link rel="stylesheet" type="text/css" href="${jobCss}">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quartz Job</title>
</head>
<body>

<jsp:include page="backofficeHeader.jsp" />

<div class = "row">
    <div class="col-md-2 col-md-offset-5">
        <h2>${msg}</h2>
    </div>
</div>

<div class="row">
    <div class="col-md-2 col-md-offset-5">
        <form:form action="job" method="post">
            <input type="submit" value="Start/Stop job" class="btn btn-info buttonStartStop">
        </form:form>
        <a href="<c:url value='/backoffice/product/' />" ><input type="submit" value="Back to Products" class="btn btn-info backToProducts"></a>
    </div>
</div>

</body>
</html>