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
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="index"> Application - Computer Database </a>
            <a class="pull-right" href="?language=fr"><img src="img/france.png" width=20px height=20px/></a>
            <a class="pull-right" href="?language=en"><img src="img/en.png" width=20px height=20px/></a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                	<div> 
                		<c:if test="${param.error != null}">
		                    <spring:message code="error.login"/>
		                </c:if>
	                </div>
	                <div> 
	                	<c:if test="${param.logout != null}"> 
	                    	<spring:message code="label.logout"/>
	                    </c:if>
	                </div>
                    <h1><spring:message code="label.login"/></h1>
                    <form:form action="login" method="POST" id="loginForm">
                        <fieldset>
                            <div class="form-group">
                                <label for="username"><spring:message code="label.username"/></label>
                                <input type="text" class="form-control" id="username" name="username"/>
                            </div>
                            <div class="form-group">
                                <label for="password"><spring:message code="label.password"/></label>
                                <input type="password" class="form-control" id="password" name="password" />
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control" />
                            <Button type="submit"  class="btn"><spring:message code="label.validate"/></Button> 
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>