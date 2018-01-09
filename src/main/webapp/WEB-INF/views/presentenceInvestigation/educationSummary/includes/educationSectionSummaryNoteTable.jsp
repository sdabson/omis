<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
<table id="educationSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="educationSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/educationSummary/educationSectionSummaryNoteItemsActionMenu.html?educationSectionSummaryNoteItemIndex=${educationSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="educationSectionSummaryNoteTableBody">
		<jsp:include page="educationSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>