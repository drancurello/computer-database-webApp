<%@ tag body-content="empty" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@ attribute name="page" required="true"%>
<%@ attribute name="nbComputersPage" required="true"%>
<%@ attribute name="nbPage" required="true"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="order" required="false"%>
<%@ attribute name="column" required="false"%>

<div class="container text-center">
            <ul class="pagination">
              <li>
              		<p:link nbComputersPage="${nbComputersPage}" page="${page - 1}" ariaLabel="Previous" valueSpan="&laquo;" search="${search}" column="${column}" order="${order}"/> 
              </li>
              <c:forEach var="i" begin="1" end="${nbPage}" step="1">
              	<c:choose>
              		<c:when test="${page eq i}">
	              		<li><a style="color:red">${i}</a></li>
	            	</c:when>
	            	<c:when test="${nbPage > 20}">
	            		<c:if test="${i eq page}">
	            			<li><a>${i}</a></li>
	            		</c:if>
	            	</c:when>
	            	<c:otherwise>
	              		<li><p:link nbComputersPage="${nbComputersPage}" page="${i}" value="${i}" search="${search}" column="${column}" order="${order}"/></li>
	            	</c:otherwise>
              	</c:choose>
              </c:forEach>
              <li>
              	<p:link nbComputersPage="${nbComputersPage}" page="${page + 1}" ariaLabel="Next" valueSpan="&raquo;" search="${search}" column="${column}" order="${order}"/>
            </li>
        </ul>
        <div class="btn-group btn-group-sm pull-right" role="group" >
         	<p:link nbComputersPage="10" page="1" type="button" classes="btn btn-default" value="10" search="${search}" column="${column}" order="${order}"/> 
            <p:link nbComputersPage="50" page="1" type="button" classes="btn btn-default" value="50" search="${search}" column="${column}" order="${order}"/> 
            <p:link nbComputersPage="100" page="1" type="button" classes="btn btn-default" value="100" search="${search}" column="${column}" order="${order}"/> 
        </div>