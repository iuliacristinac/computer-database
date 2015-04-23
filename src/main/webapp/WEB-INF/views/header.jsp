<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<c:url value="/dashboard" />">
				 <spring:message code="common.ApplicationName" />
			</a>
			<div id="flags">
			    <a href="?lang=en">
			        <strong>English</strong>
			    </a>
			    /
			    <a href="?lang=fr">
			        <strong>Français</strong>
			    </a>
			</div>
		</div>
	</header>