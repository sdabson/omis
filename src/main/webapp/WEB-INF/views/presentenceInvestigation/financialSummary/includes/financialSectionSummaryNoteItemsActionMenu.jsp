<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSectionSummary">
	<ul>
		<sec:authorize access="hasRole('FINANCIAL_SECTION_SUMMARY_CREATE') or hasRole('FINANCIAL_SECTION_SUMMARY_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createFinancialSectionSummaryNoteItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/presentenceInvestigation/financialSummary/createFinancialSectionSummaryNoteItem.html?financialSectionSummaryNoteItemIndex=${financialSectionSummaryNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addFinancialSectionSummaryNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>