<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
	<ul>
		<sec:authorize access="hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_CREATE') or hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createPleaAgreementSectionSummaryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/pleaAgreementSummary/createPleaAgreementSectionSummaryNoteItem.html?pleaAgreementSectionSummaryNoteItemIndex=${pleaAgreementSectionSummaryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addPleaAgreementSectionSummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>