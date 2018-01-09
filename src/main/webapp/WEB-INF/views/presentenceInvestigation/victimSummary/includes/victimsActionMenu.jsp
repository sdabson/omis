<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
	<ul>
		<sec:authorize access="hasRole('VICTIM_PROFILE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/victim/association/listByOffender.html?offender=${offender.id}">
					<span class="visibleLinklabel"><fmt:message key="listVictimsLink"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>