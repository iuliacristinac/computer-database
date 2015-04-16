<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:import url="head.jsp" />
<c:import url="header.jsp" />
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">
					id: <c:out value="${computer.id}"/>
			</div>
			<h1><spring:message code="edit.EditComputer" /></h1>
			<c:if test="${!empty message}">
				<div class="has-error">${message}</div>
			</c:if>	
						
			<form:form commandName="computer" action="editComputer" method="POST">
				<form:input type="hidden" path="id" name="id" value="${computer.id}"/>
				<fieldset>
					<div class="form-group">
						<form:label path="name" for="name"><spring:message code="form.ComputerName" /></form:label>
						<form:input path="name" type="text" class="form-control" id="name" name="name" placeholder="Computer name" value="${computer.name}" /> 
						<form:errors path="name" cssClass="has-error" />
					</div>
					<div class="form-group">
						<form:label path="introduced" for="introduced"><spring:message code="form.Introduced" /></form:label>
						<form:input path="introduced" type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date"
							 value="${introducedDate}" />
						<form:errors path="introduced" cssClass="has-error" />
					</div>
					<div class="form-group">
						<form:label path="discontinued" for="discontinued"><spring:message code="form.Discontinued" /></form:label>
						<form:input path="discontinued" type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date"
							value="${discontinuedDate}"/>
						<form:errors path="discontinued" cssClass="has-error" />
					</div>
					<div class="form-group">
						<form:label path="companyId" for="companyId"><spring:message code="form.Company" /></form:label>
						<form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
							<spring:message code="form.NoCompany" var="noCompany" />
							<form:option value="0" label="${noCompany}"/>
							<form:options items="${companies}" itemValue="id" itemLabel="name" />
						</form:select>								
						<form:errors path="companyId" cssClass="has-error" />
					</div>
				</fieldset>
				<div class="actions pull-right">
					<input type="submit" value="<spring:message code="common.Edit" />" class="btn btn-primary">
					<a href="<c:url value="/dashboard" />" class="btn btn-default"><spring:message code="common.Cancel" /></a>
				</div>
			</form:form>
			</div>
		</div>
	</div>
</section>
<c:import url="footer.jsp" />