<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.hearingparticipant.msgs.hearingParticipant">
	<ul>
		<sec:authorize access="hasRole('HEARING_PARTICIPANT_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/hearingParticipant/list.html?boardHearing=${boardHearing.id}"><span class="visibleLinkLabel"><fmt:message key="viewHearingParticipantsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>