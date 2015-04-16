<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:import url="head.jsp" />
<c:import url="header.jsp" />
<section id="main">
    <div class="container">
        <div class="alert alert-danger">
          	<spring:message code="error.404Message" />
            <br/>
            <!-- stacktrace -->
        </div>
    </div>
</section>
<c:import url="footer.jsp" />