<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
<table id="pleaAgreementSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="pleaAgreementSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/pleaAgreementSummary/pleaAgreementSectionSummaryNoteItemsActionMenu.html?pleaAgreementSectionSummaryNoteItemIndex=${pleaAgreementSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
		</tr>
	</thead>
	<tbody id="pleaAgreementSectionSummaryNoteTableBody">
		<jsp:include page="pleaAgreementSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>