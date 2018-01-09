<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSectionSummary">
<table id="financialSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations">
				<a class="actionMenuItem" id="financialSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/financialSummary/financialSectionSummaryNoteItemsActionMenu.html?financialSectionSummaryNoteItemIndex=${financialSectionSummaryNoteItemIndex}">
				</a>
			</th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
		</tr>
	</thead>
	<tbody id="financialSectionSummaryNoteTableBody">
		<jsp:include page="financialSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>