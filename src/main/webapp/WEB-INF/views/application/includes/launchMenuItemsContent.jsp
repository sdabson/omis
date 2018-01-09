<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<li class="launchMenuItem">
	<a class="launchMenuItemLink" href="/some/url.html">Legal</a>
	<sec:authorize access="hasRole('APP_DEV')">
		<a class="launchMenuVisitationLink" href="${pageContext.request.contextPath}/visitation/index.html">Visitation</a>
	</sec:authorize>
</li>