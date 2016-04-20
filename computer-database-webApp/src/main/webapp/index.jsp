<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="index"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${page.nbComputers} <spring:message code="label.computers"/>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="index" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value=<spring:message code="label.filter"/>
                        class="btn btn-primary" />
                        <a href="?language=fr"><img src="img/france.png" width=20px height=20px/></a>
                        <a href="?language=en"><img src="img/en.png" width=20px height=20px/></a>
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="label.add"/></a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="deleteComputer" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <spring:message code="label.name"/>
                            <p:linkFilter order="name" nbComputersPage="${nbComputersPage}" type="ASC" page="${currentPage}" search="${search}" value="&laquo;"/>
                            <p:linkFilter order="name" nbComputersPage="${nbComputersPage}" type="DESC" page="${currentPage}" search="${search}" value="&raquo;"/>
                        </th>
                        <th>
                            <spring:message code="label.introduced"/>
                            <p:linkFilter order="introduced" nbComputersPage="${nbComputersPage}" type="ASC" page="${currentPage}" search="${search}" value="&laquo;"/>
                            <p:linkFilter order="introduced" nbComputersPage="${nbComputersPage}" type="DESC" page="${currentPage}" search="${search}" value="&raquo;"/>
                            		
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <spring:message code="label.discontinued"/>
                            <p:linkFilter order="discontinued" nbComputersPage="${nbComputersPage}" type="ASC" page="${currentPage}" search="${search}" value="&laquo;"/>
                            <p:linkFilter order="discontinued" nbComputersPage="${nbComputersPage}" type="DESC" page="${currentPage}" search="${search}" value="&raquo;"/>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <spring:message code="label.company"/>
                            <p:linkFilter order="company" nbComputersPage="${nbComputersPage}" type="ASC" page="${currentPage}" search="${search}" value="&laquo;"/>
                            <p:linkFilter order="company" nbComputersPage="${nbComputersPage}" type="DESC" page="${currentPage}" search="${search}" value="&raquo;"/>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                
                	<c:forEach items="${page.computersList}" var="computer" varStatus="status" >
	                	<tr>
	                        <td class="editMode">
	                            <input type="checkbox" name="cb" class="cb" value="${computer.id}">
	                        </td>
	                        <td>
	                            <a href="editComputer?id=${computer.id}" onclick=""><c:out value="${computer.name}"></c:out></a>
	                        </td>
	                        <td><c:out value="${computer.introduced}"></c:out></td>
	                        <td><c:out value="${computer.discontinued}"></c:out></td>
	                        <td><c:out value="${computer.companyName}"></c:out></td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
    	<p:pagination currentPage="${page.pageNumber}" nbComputersPage="${page.nbEntriesByPage}" nbPage="${page.nbPage}" search="${search}"/>
    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>
</body>
</html>