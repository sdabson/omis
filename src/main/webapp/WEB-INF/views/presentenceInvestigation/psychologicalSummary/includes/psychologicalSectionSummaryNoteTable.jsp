<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
<table id="psychologicalSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="psychologicalSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/psychologicalSummary/psychologicalSectionSummaryNoteItemsActionMenu.html?psychologicalSectionSummaryNoteItemIndex=${psychologicalSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
		</tr>
	</thead>
	<tbody id="psychologicalSectionSummaryNoteTableBody">
		<jsp:include page="psychologicalSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>