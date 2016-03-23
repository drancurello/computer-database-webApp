<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                ${nbComputers} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="index" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
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
                            Computer name
                            <c:choose>
                            	<c:when test="${not empty search}">
                            		<a href="?page=1&nbComputersPage=50&order=name&type=ASC&search=${search}">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=name&type=DESC&search=${search}">&raquo;</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="?page=1&nbComputersPage=50&order=name&type=ASC">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=name&type=DESC">&raquo;</a>
                            	</c:otherwise>
                            </c:choose>
                        </th>
                        <th>
                            Introduced date
                            <c:choose>
                            	<c:when test="${not empty search}">
                            		<a href="?page=1&nbComputersPage=50&order=introduced&type=ASC&search=${search}">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=introduced&type=DESC&search=${search}">&raquo;</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="?page=1&nbComputersPage=50&order=introduced&type=ASC">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=introduced&type=DESC">&raquo;</a>
                            	</c:otherwise>
                            </c:choose>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                            <c:choose>
                            	<c:when test="${not empty search}">
                            		<a href="?page=1&nbComputersPage=50&order=discontinued&type=ASC&search=${search}">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=discontinued&type=DESC&search=${search}">&raquo;</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="?page=1&nbComputersPage=50&order=discontinued&type=ASC">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=discontinued&type=DESC">&raquo;</a>
                            	</c:otherwise>
                            </c:choose>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                            <c:choose>
                            	<c:when test="${not empty search}">
                            		<a href="?page=1&nbComputersPage=50&order=company&type=ASC&search=${search}">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=company&type=DESC&search=${search}">&raquo;</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="?page=1&nbComputersPage=50&order=company&type=ASC">&laquo;</a>
                            		<a href="?page=1&nbComputersPage=50&order=company&type=DESC">&raquo;</a>
                            	</c:otherwise>
                            </c:choose>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                
                	<c:forEach items="${computers}" var="computer" varStatus="status" >
	                	<tr>
	                        <td class="editMode">
	                            <input type="checkbox" name="cb" class="cb" value="${computer.id}">
	                        </td>
	                        <td>
	                            <a href="editComputer?id=${computer.id}" onclick=""><c:out value="${computer.name}"></c:out></a>
	                        </td>
	                        <td><c:out value="${computer.introducedTime}"></c:out></td>
	                        <td><c:out value="${computer.discontinuedTime}"></c:out></td>
	                        <td><c:out value="${computer.company.name}"></c:out></td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
    	<p:pagination currentPage="${currentPage}" nbComputersPage="${nbComputersPage}" nbPage="${nbPage}" search="${search}"/>
    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>
</body>
</html>