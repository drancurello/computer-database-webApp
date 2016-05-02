<%@ tag body-content="empty" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true"%>
<%@ attribute name="nbComputersPage" required="true"%>
<%@ attribute name="order" required="true"%>
<%@ attribute name="type" required="true"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="value" required="false" %>

<c:set var="withSearch" value="page=${page}&nbComputersPage=${nbComputersPage}&order=${order}&type=${type}&search=${search}"/>
<c:set var="noSearch" value="page=${page}&nbComputersPage=${nbComputersPage}&order=${order}&type=${type}" />

<a href="?${not empty search ? withSearch : noSearch}">${value}</a>