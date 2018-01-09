<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
<table id="chemicalUseSectionSummaryNoteTable" class="editTable">
	<thead class="notes">
		<tr>
			<th class="operations"><a class="actionMenuItem" id="chemicalUseSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/chemicalUseSummary/chemicalUseSectionSummaryNoteItemsActionMenu.html?chemicalUseSectionSummaryNoteItemIndex=${chemicalUseSectionSummaryNoteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>	
		</tr>
	</thead>
	<tbody id="chemicalUseSectionSummaryNoteTableBody">
		<jsp:include page="chemicalUseSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>