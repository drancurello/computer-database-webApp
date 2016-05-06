<%@ tag body-content="empty" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true"%>
<%@ attribute name="nbComputersPage" required="true"%>
<%@ attribute name="order" required="true"%>
<%@ attribute name="column" required="true"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="value" required="false" %>

<c:set var="withSearch" value="page=${page}&nbComputersPage=${nbComputersPage}&column=${column}&order=${order}&search=${search}"/>
<c:set var="noSearch" value="page=${page}&nbComputersPage=${nbComputersPage}&column=${column}&order=${order}" />

<a href="?${not empty search ? withSearch : noSearch}">${value}</a>