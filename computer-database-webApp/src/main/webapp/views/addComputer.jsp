<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="js/validation.js"></script>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="index"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="label.add"/></h1>
                    <form:form action="addComputer" method="POST" id="formComputer" modelAttribute="computerDTO">
                        <fieldset>
                            <div class="form-group">
                            	<spring:message code="label.name" var="mess_name"/>
                                <label for="computerName">${mess_name}</label>
                                <form:input type="text" class="form-control" id="computerName" name="computerName" path="name" placeholder="${mess_name}"/>
                                <form:errors path="name" cssClass="error"/>
                            </div>
                            <div class="form-group">
                            	<spring:message code="label.introduced" var="mess_introduced"/>
                                <label for="introduced">${mess_introduced}</label>
                                <form:input type="text" class="form-control" id="introduced" name="introduced" path="introduced" placeholder="${mess_introduced}"/>
                                <form:errors path="introduced" cssClass="error"/>
                            </div>
                            <div class="form-group">
                            	<spring:message code="label.discontinued" var="mess_discontinued"/>
                                <label for="discontinued">${mess_discontinued}</label>
                                <form:input type="text" class="form-control" id="discontinued" name="discontinued" path="discontinued" placeholder="${mess_discontinued}"/>
                                <form:errors path="discontinued" cssClass="error"/>
                                <form:errors path="" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="label.company"/></label>
                                <form:select class="form-control" id="companyId" name="companyId" path="companyId" >
                                	<c:forEach items="${companies}" var="company">
                                		<form:option value="${company.id}">${company.name}</form:option>
                                	</c:forEach>
                                </form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value=<spring:message code="label.add"/> class="btn btn-primary" >
                            or
                            <a href="index" class="btn btn-default"><spring:message code="label.cancel"/></a>
                        </div>
                    </form:form>
                    <script type="text/javascript">
						var translation = new Array();
						translation['error.dateBefore'] = "<spring:message code='error.dateBefore' javaScriptEscape='true' />";
						translation['error.dateBefore1970'] = "<spring:message code='error.dateBefore1970' javaScriptEscape='true' />";
						translation['error.dateFormat'] = "<spring:message code='error.dateFormat' javaScriptEscape='true' />";
						translation['error.name'] = "<spring:message code='error.name' javaScriptEscape='true' />";
						translation['error.required'] = "<spring:message code='error.required' javaScriptEscape='true' />";
					</script>
                </div>
            </div>
        </div>
    </section>
</body>
</html>