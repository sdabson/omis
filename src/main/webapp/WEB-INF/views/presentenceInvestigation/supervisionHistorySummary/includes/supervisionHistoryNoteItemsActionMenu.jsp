<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.supervisionHistorySectionSummary">
	<ul>
		<sec:authorize access="hasRole('SUPERVISION_HISTORY_SECTION_SUMMARY_CREATE') or hasRole('SUPERVISION_HISTORY_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createSupervisionHistoryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/supervisionHistorySummary/createSupervisionHistoryNoteItem.html?supervisionHistoryNoteItemIndex=${supervisionHistoryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addSupervisionHistorySummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>