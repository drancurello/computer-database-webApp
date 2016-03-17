<%@ tag body-content="empty" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="currentPage" required="true"%>
<%@ attribute name="nbComputersPage" required="true"%>
<%@ attribute name="nbPage" required="true"%>

<div class="container text-center">
            <ul class="pagination">
               <li>
	                    <a href="?page=${currentPage - 1}&nbComputersPage=${nbComputersPage}" aria-label="Previous">
	                      <span aria-hidden="true">&laquo;</span>
	                    </a>
              </li>
              <c:forEach var="i" begin="1" end="${nbPage}" step="1">
              	<c:choose>
              		<c:when test="${currentPage eq i}">
	              		<li><a style="color:red">${i}</a></li>
	            	</c:when>
	            	<c:when test="${nbComputersPage eq 10}">
	            		<c:if test="${i eq currentPage}">
	            			<li><a>${i}</a></li>
	            		</c:if>
	            	</c:when>
	            	<c:otherwise>
	              		<li><a href="?page=${i}&nbComputersPage=${nbComputersPage}">${i}</a></li>
	            	</c:otherwise>
              	</c:choose>
              </c:forEach>
              <li>
	                <a href="?page=${currentPage + 1}&nbComputersPage=${nbComputersPage}" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>
            </li>
        </ul>
        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a href="?nbComputersPage=10&page=1" type="button" class="btn btn-default">10</a>
            <a href="?nbComputersPage=50&page=1" type="button" class="btn btn-default">50</a>
            <a href="?nbComputersPage=100&page=1" type="button" class="btn btn-default">100</a>
        </div>