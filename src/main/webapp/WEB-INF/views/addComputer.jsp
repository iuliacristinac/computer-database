<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="head.jsp" />
<c:import url="header.jsp" />
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<c:if test="${!empty message}">
				<div class="has-error">${message}</div>
			</c:if>
			<form:form commandName="newComputer" action="addComputer" method="POST">
				<fieldset>
				
					<div class="form-group">
						<form:label path="name" for="name">Computer name</form:label>
						<form:input path="name" type="text" cssClass="form-control" id="name" name="name" placeholder="Computer name" />
						<form:errors path="name" cssClass="has-error" />
					</div>
				
					<div class="form-group">
						<form:label path="introduced" for="introduced">Introduced date</form:label>
						<form:input path="introduced" type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" />
						<form:errors path="introduced" cssClass="has-error" />
					</div>
				
					<div class="form-group">
						<form:label path="discontinued" for="discontinued">Discontinued date</form:label>
						<form:input path="discontinued" type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" />
						<form:errors path="discontinued" cssClass="has-error" />
					</div>
				
					<div class="form-group">
						<form:label path="companyId" for="companyId">Company</form:label>
						<form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
							<form:option value="0" label="None"/>
							<form:options items="${companies}" itemValue="id" itemLabel="name" />
						</form:select>								
						<form:errors path="companyId" cssClass="has-error" />
					</div>
				
				</fieldset>
				
				<div class="actions pull-right">
					<input type="submit" value="Add" class="btn btn-primary">
					<a class="btn btn-default" href="<c:url value="/dashboard" />">Cancel</a>
				</div>
				
			</form:form>
			</div>
		</div>
	</div>
</section>
<c:import url="footer.jsp" />