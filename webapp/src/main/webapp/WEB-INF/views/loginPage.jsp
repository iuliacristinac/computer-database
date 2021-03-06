<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="mylib" %>
<%@ page session="false"%>

<c:import url="head.jsp" />

<c:import url="header.jsp" />
	
<section id="main">
 <h3>Login Iulia</h3>
<%--     <c:url var="loginUrl" value="/j_spring_security_check"></c:url> --%>
     <form:form method="post" action="login"  >
        <div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
            <tr>
                <td>User ID:</td>
                <td><input type='text' name='username' /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                    value="Login" /></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
        </div>
    </form:form>

</section>
<c:import url="footer.jsp" />