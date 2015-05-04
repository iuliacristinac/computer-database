<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="numberOfPages" required="true" type="java.lang.Integer"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"%>
<%@ attribute name="size" required="true" type="java.lang.Integer"%>
<%@ attribute name="previous" required="true"%>
<%@ attribute name="next" required="true"%>
<%@ attribute name="url" required="true"%>
<div class="container text-center">
	<ul class="pagination">
	
		<li>
			<a href="<c:url value="${url}?page=1&size=${size}" />" aria-label="Next">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
		
		<li>
			<c:choose>
				<c:when test="${previous}">
					<a href="<c:url value="${url}?page=${page - 1}&size=${size}" />" aria-label="Next">
						<span aria-hidden="true">&lsaquo;</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="#" aria-label="Previous"> 
						<span aria-hidden="true">&lsaquo;</span>
					</a>
				</c:otherwise>
			</c:choose>
		</li>
		
		<li>
			<a href="<c:url value="${url}?page=${page}&size=${size}" />" aria-label="Current">
				<span aria-hidden="true"><c:out value="${page}" /></span>
			</a>
		</li>
		
		<li>
			<c:choose>
				<c:when test="${next}">
					<a href="<c:url value="${url}?page=${page + 1}&size=${size}" />" aria-label="Next"> 
						<span aria-hidden="true">&rsaquo;</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="#" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span>
					</a>
				</c:otherwise>
			</c:choose>
		</li>
		
		<li>
			<a href="<c:url value="${url}?page=${numberOfPages}" />" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
		
	</ul>
	
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='${url}page=1&size=10'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='${url}page=1&size=50'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='${url}page=1&size=100'">100</button>
	</div>
</div>
