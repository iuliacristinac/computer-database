<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mylib" %>
<%@ page session="true"%>
<c:import url="head.jsp" />

<c:import url="header.jsp" />

<section id="main">
	<div class="container">
		<h1 id="homeTitle"> ${totalEntities} <spring:message code="dashboard.ComputersFound" /> </h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">
					<spring:message code="form.filterByName" var="filterByNameLabel"/>
					<spring:message code="form.placeholder.searchName" var="searchNamePlaceholder"/>
					<input type="search" id="searchbox" name="search" class="form-control"
					placeholder="${searchNamePlaceholder}"/>
					<input type="submit" id="searchsubmit" value="${filterByNameLabel}"
					class="btn btn-primary"/>
				</form>
			</div>
			
			<div class="pull-right">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a class="btn btn-success" id="addComputer" href="<c:url value="/addComputer" />"><spring:message 
					code="common.AddComputer"/></a>
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message 
					code="common.Edit"/></a>
				</sec:authorize>
			</div>
		</div>
	</div>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</sec:authorize>
	
	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="editMode" style="width: 60px; height: 22px;">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<input type="checkbox" id="selectall"/>
								<span style="vertical-align: top;"> -
									<a href="#" id="deleteSelected" onclick="return confirmMessage();$.fn.deleteSelected();">
										<i class="fa fa-trash-o fa-lg"></i>
									</a>
								</span>
						</sec:authorize>
					</th>
					<th>
					<th>
						<spring:message code="form.ComputerName" var="computerNameLabel" />
						<mylib:orderBy page="${page}" url="dashboard" property="computer.name" label="${computerNameLabel}" />
					</th>
					<th>
						<spring:message code="form.Introduced" var="introducedLabel" />
						<mylib:orderBy page="${page}" url="dashboard" property="computer.introduced" label="${introducedLabel}" />
					</th>
					<th>
						<spring:message code="form.Discontinued" var="discontinuedLabel" />
						<mylib:orderBy page="${page}" url="dashboard" property="computer.discontinued" label="${discontinuedLabel}" />
					</th>
					<th>
						<spring:message code="form.Company" var="companyLabel" />
						<mylib:orderBy page="${page}" url="dashboard" property="company.name" label="${companyLabel}" />
					</th>
			</tr>
		</thead>

			<tbody id="results">
				<c:forEach items="${computers}" var="computer">
					<tbody>
						<tr>
							<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${computer.id}"/></td>
							<td><a href="<c:url value="/editComputer?id=${computer.id}" />"><c:out value="${computer.name}" /></a></td>
							<td><c:out value="${computer.introduced}" /></td>
							<td><c:out value="${computer.discontinued}" /></td>
							<td><c:if test="${!empty computer.companyId}">
									<c:out value="${computer.companyName}" />
								</c:if>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>

<footer class="navbar-fixed-bottom">
	<c:choose>
		<c:when test="${!empty lastSearch}">
			<c:set value="${'dashboard?'}search=${lastSearch}&" var="url" />
		</c:when>
		<c:otherwise>
			<c:set value="dashboard?" var="url" />
		</c:otherwise>
	</c:choose>

	<mylib:pageable numberOfPages="${page.numberOfPages}" page="${page.currentPage}" size="${page.size} }"
				previous="${page.previous}" next="${page.next}" 
				url="/dashboard" /> 
</footer>

<spring:message code="delete.DeleteMessage" var="deleteMessage" />
<script>
 function confirmMessage() {
 	return confirm("${deleteMessage}");
 }
</script>
<c:import url="footer.jsp" />