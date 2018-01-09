<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">
	<ul>
		<sec:authorize access="hasRole('WORK_RESTRICTION_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/workRestriction/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listWorkRestrictionsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>