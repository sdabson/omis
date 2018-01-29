<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.hearingparticipant.msgs.hearingParticipant">
	<ul>
		<sec:authorize access="hasRole('HEARING_PARTICIPANT_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/hearingParticipant/create.html?boardHearing=${boardHearing.id}"><span class="visibleLinkLabel"><fmt:message key="createHearingParticipantLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>