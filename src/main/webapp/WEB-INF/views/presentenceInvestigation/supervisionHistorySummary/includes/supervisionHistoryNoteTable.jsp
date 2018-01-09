<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.supervisionHistorySectionSummary">
<table id="supervisionHistoryNoteTable" class="editTable">
	<thead class="notes">
		<tr>
			<th class="operations"><a class="actionMenuItem" id="supervisionHistoryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/supervisionHistorySummary/supervisionHistoryNoteItemsActionMenu.html?supervisionHistoryNoteItemIndex=${supervisionHistoryNoteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="supervisionHistoryNoteTableBody">
		<jsp:include page="supervisionHistoryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>