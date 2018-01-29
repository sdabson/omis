<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.hearingparticipant.msgs.hearingparticipant">
	<ul>
		<sec:authorize access="hasRole('HEARING_PARTICIPANT_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/hearingParticipant/edit.html?hearingParticipant=${hearingParticipant.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('HEARING_PARTICIPANT_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/hearingParticipant/remove.html?hearingParticipant=${hearingParticipant.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>