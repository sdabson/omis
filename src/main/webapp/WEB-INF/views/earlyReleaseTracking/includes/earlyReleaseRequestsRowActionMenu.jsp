<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<ul>
		<sec:authorize access="hasRole('EARLY_RELEASE_REQUEST_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/earlyReleaseTracking/edit.html?earlyReleaseRequest=${earlyReleaseRequest.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('EARLY_RELEASE_REQUEST_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/earlyReleaseTracking/remove.html?earlyReleaseRequest=${earlyReleaseRequest.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>