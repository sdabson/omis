<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<ul>
		<sec:authorize access="hasRole('VIOLATION_EVENT_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/violationEvent/edit.html?violationEvent=${violationEvent.id}&category=${violationEvent.category}"><span class="visibleLinkLabel"><fmt:message key="viewEditViolationEventLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>