<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p"%>
<c:import url="head.jsp" />

<c:import url="header.jsp" />
<section id="main">
	<div class="container">
		<h1 id="homeTitle"> ${fn:length(computers)} Computers found </h1>
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
					href="<c:url value="/addComputer" />">Add Computer</a>
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
					<th>Computer name</th>
					<th>Introduced date</th>
					<th>Discontinued date</th>
					<th>Company</th>
					<th>Edit/Delete</th>
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
           						<a href="<c:url value="/editComputer?id=${computer.id}" />" class="btn btn-sm btn-info">Edit</a>
          							<a href="<c:url value="/deleteComputer?id=${computer.id}" />" class="btn btn-sm btn-danger" onclick="confirmMessage()">Delete</a>
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
<script>
 function confirmMessage() {
   if (confirm("Are you sure you want to delete this computer?")) {
     window.location.href = "/deleteComputer?id=${computer.id}";
   }
 }
</script>
	<c:import url="footer.jsp" />