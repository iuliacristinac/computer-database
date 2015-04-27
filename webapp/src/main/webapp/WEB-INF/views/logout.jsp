<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mylib" %>
<%@ page session="false" %>
<c:import url="head.jsp" />

<c:import url="header.jsp" />
<section id="main">
	<h2>
	    Logout Successful!
	</h2>
	<a href="<c:url value="/login" />">Login</a>
</section>
<c:import url="footer.jsp" />