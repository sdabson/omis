<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.warrant.msgs.warrant">
	<ul>
		<sec:authorize access="hasRole('WARRANT_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/warrant/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listWarrantsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>