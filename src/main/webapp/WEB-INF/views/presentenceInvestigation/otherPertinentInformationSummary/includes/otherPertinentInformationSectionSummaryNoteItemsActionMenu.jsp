<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.otherPertinentInformationSectionSummary">
	<ul>
		<sec:authorize access="hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_CREATE') or hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createOtherPertinentInformationSectionSummaryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/otherPertinentInformationSummary/createOtherPertinentInformationSectionSummaryNoteItem.html?otherPertinentInformationSectionSummaryNoteItemIndex=${otherPertinentInformationSectionSummaryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addOtherPertinentInformationSectionSummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>