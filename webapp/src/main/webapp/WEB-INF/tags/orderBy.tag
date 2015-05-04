<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mylib" %>
<%@ attribute name="page" required="true" type="com.excilys.util.Page" %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="property" required="true" %>
<%@ attribute name="label" required="true" %>

<c:if test="${page.properties.contains(property)}">
	<c:choose>
		<c:when test="${page.sort == 'ASC'}">
			<mylib:link url="${url}" sort="DESC" size="${page.size}" column="${property}"
				page="${page.page}">
				<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>${label}
			</mylib:link>
		</c:when>
	<c:otherwise>
		<mylib:link url="${url}" sort="ASC" size="${page.size}" column="${property}"
			page="${page.page}">
			<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>${label}
		</mylib:link>
	</c:otherwise>
	</c:choose>
</c:if>

<c:if test="${!page.properties.contains(property)}">
	<mylib:link url="${url}" sort="ASC" size="${page.size}" column="${property}"
		page="${page.page}">
		<span aria-hidden="true">${label}</span>
	</mylib:link>
</c:if>