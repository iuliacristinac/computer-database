<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
<c:import url="head.jsp" />

<c:import url="header.jsp" />
<section id="main">
	<div class="container">
		<h1 id="homeTitle"> ${fn:length(computers)} <spring:message code="dashboard.ComputersFound" /></h1>
		<div id="actions" class="form-horizontal">
<!-- 				<div class="pull-left"> -->
<!-- 					<form id="searchForm" action="#" method="GET" class="form-inline"> -->
<!-- 						<input type="search" id="searchbox" name="search" -->
<!-- 							class="form-control" placeholder="Search name" /> <input -->
<!-- 							type="submit" id="searchsubmit" value="Filter by name" -->
<!-- 							class="btn btn-primary" /> -->
<!-- 					</form> -->
<!-- 				</div> -->
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer"
					href="<c:url value="/addComputer" />"><spring:message code="common.AddComputer" /></a>
			</div>

		</div>
	</div>
	<form id="deleteForm" action="#" method="POST">
		<input type="hidden" name="selection" value="">
	</form>
	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th><spring:message code="form.ComputerName" /></th>
					<th><spring:message code="form.Introduced" /></th>
					<th><spring:message code="form.Discontinued" /></th>
					<th><spring:message code="form.Company" /></th>
					<th>
						<spring:message code="common.Edit" />
						 / 
						<spring:message code="common.Delete" />
					</th>
				</tr>
			</thead>
			<tbody id="results">
				<c:forEach items="${computers}" var="computer">
					<tbody>
						<tr>
							<td><a href="<c:url value="/showComputer?id=${computer.id}" />"><c:out value="${computer.name}" /></a></td>
							<td>
								<fmt:parseDate value="${computer.introduced}" var="introducedDate" pattern="yyyy-MM-dd HH:mm" /> 
								<fmt:formatDate value="${introducedDate}" type="Date" dateStyle="full" /></td>
							<td>
								<fmt:parseDate value="${computer.discontinued}" var="discontinuedDate" pattern="yyyy-MM-dd HH:mm" /> 
								<fmt:formatDate value="${discontinuedDate}" type="Date" dateStyle="full" />
							</td>
							<td><c:if test="${!empty computer.companyId}">
									<c:out value="${computer.companyName}" />
								</c:if>
							</td>
							<td>
           						<a href="<c:url value="/editComputer?id=${computer.id}" />" class="btn btn-sm btn-info">
           							<spring:message code="common.Edit" />
           						</a>
          						<a href="<c:url value="/deleteComputer?id=${computer.id}" />" class="btn btn-sm btn-danger" onclick=" return confirmMessage()">
          							<spring:message code="common.Delete" />
          						</a>
         					</td>

						</tr>
					</tbody>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>
<!-- 	<footer class="navbar-fixed-bottom"> -->
<%-- 		<p:paginator totalPages="${totalPages}" page="${page.page}" --%>
<%-- 			pageCount="${maxPages}" pageSize="${page.size}" url="/dashboard" --%>
<%-- 			previous="${page.previous}" /> --%>
<!-- 	</footer> -->

<spring:message code="delete.DeleteMessage" var="deleteMessage" />
<script>
 function confirmMessage() {
 	return confirm("${deleteMessage}");
 }
</script>
	<c:import url="footer.jsp" />