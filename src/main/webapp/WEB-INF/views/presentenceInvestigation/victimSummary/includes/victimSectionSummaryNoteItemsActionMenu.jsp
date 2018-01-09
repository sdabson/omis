<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
	<ul>
		<sec:authorize access="hasRole('VICTIM_SECTION_SUMMARY_CREATE') or hasRole('VICTIM_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createVictimSectionSummaryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/createVictimSectionSummaryNoteItem.html?victimSectionSummaryNoteItemIndex=${victimSectionSummaryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addVictimSectionSummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>