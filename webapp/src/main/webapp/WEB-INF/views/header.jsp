<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<c:url value="/dashboard" />">
				 <spring:message code="common.ApplicationName" />
			</a>
			<div id="lang">
			    <a href="?lang=en">
			        <strong>English</strong>
			    </a>
			    /
			    <a href="?lang=fr">
			        <strong>Français</strong>
			    </a>
			</div>
			<div id="logout">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<c:out value="Welcome : ${pageContext.request.userPrincipal.name} "></c:out>
					<form action="logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        				<input type="submit" value="Logout" />
    				</form>
				</c:if>
				  
			</div>
		</div>
	</header>