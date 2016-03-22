<%@ tag body-content="empty" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true"%>
<%@ attribute name="nbComputersPage" required="true"%>
<%@ attribute name="ariaLabel" required="false"%>
<%@ attribute name="type" required="false"%>
<%@ attribute name="classes" required="false"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="valueSpan" required="false" %>
<%@ attribute name="value" required="false" %>

<c:set var="span" value="<span aria-hidden='true'>${valueSpan}</span>" />
<c:set var="noSpan" value="${value}" />
<c:set var="forSearch" value="page=${page}&nbComputersPage=${nbComputersPage}&search=${search}"/>
<c:set var="notSearch" value="page=${page}&nbComputersPage=${nbComputersPage}" />

<a href="?${not empty search ? forSearch : notSearch}"
	type="${not empty type ? type : ''}"
	class="${not empty classes ? classes : ''}"
	aria-label="${not empty ariaLabel ? ariaLabel : ''}">
	${not empty valueSpan ? span : noSpan}
	</a>