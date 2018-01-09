<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.jailAdjustmentSectionSummary">
	<ul>
		<sec:authorize access="hasRole('JAIL_ADJUSTMENT_SECTION_SUMMARY_CREATE') or hasRole('JAIL_ADJUSTMENT_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createJailAdjustmentSectionSummaryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/jailAdjustmentSummary/createJailAdjustmentSectionSummaryNoteItem.html?jailAdjustmentSectionSummaryNoteItemIndex=${jailAdjustmentSectionSummaryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addJailAdjustmentSectionSummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>