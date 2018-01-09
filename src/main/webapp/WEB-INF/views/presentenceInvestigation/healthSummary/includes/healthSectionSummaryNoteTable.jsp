<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
<table id="healthSectionSummaryNoteTable" class="editTable">
	<thead class="notes">
		<tr>
			<th class="operations"><a class="actionMenuItem" id="healthSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/healthSummary/healthSectionSummaryNoteItemsActionMenu.html?healthSectionSummaryNoteItemIndex=${healthSectionSummaryNoteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>	
			<th><fmt:message key="lastUpdatedByLabel"/></th>		
		</tr>
	</thead>
	<tbody id="healthSectionSummaryNoteTableBody">
		<jsp:include page="healthSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>