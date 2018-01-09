<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<ul>
		<c:choose>
			<c:when test="${not empty infraction.disciplinaryCodeViolation.id}">
				<c:set value="${infraction.disciplinaryCodeViolation.violationEvent.id}" var="violationEventId"/>
				<c:set value="DISCIPLINARY" var="category"/>
			</c:when>
			<c:when test="${not empty infraction.conditionViolation.id}">
				<c:set value="${infraction.conditionViolation.violationEvent.id}" var="violationEventId"/>
				<c:set value="SUPERVISION" var="category"/>
			</c:when>
		</c:choose>
		<sec:authorize access="hasRole('VIOLATION_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/hearing/resolution/edit.html?infraction=${infraction.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditDecisionLink"/></span></a>
			</li>
		</sec:authorize>
		<c:if test="${not empty infraction.hearing}">
			<sec:authorize access="hasRole('HEARING_VIEW') or hasRole('ADMIN')">
				<li>
					<a class="viewEditLink" href="${pageContext.request.contextPath}/hearing/edit.html?hearing=${infraction.hearing.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditHearingLink"/></span></a>
				</li>
			</sec:authorize>
		</c:if>
		<sec:authorize access="hasRole('VIOLATION_EVENT_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/violationEvent/edit.html?violationEvent=${violationEventId}&category=${category}"><span class="visibleLinkLabel"><fmt:message key="viewEditViolationEventLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('VIOLATION_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/hearing/resolution/remove.html?infraction=${infraction.id}"><span class="visibleLinkLabel"><fmt:message key="removeResolutionLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('VIOLATION_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty infraction}">
			<li>
				<a href="${pageContext.request.contextPath}/hearing/violations/resolvedViolationDetailsReport.html?infraction=${infraction.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="resolvedViolationDetailsReportLinkLabel"/></a>
			</li>
		</c:if>
		</sec:authorize>
	</ul>
</fmt:bundle>