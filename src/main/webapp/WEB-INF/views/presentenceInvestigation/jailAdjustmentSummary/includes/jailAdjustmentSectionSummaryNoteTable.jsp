<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.jailAdjustmentSectionSummary">
<table id="jailAdjustmentSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="jailAdjustmentSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/jailAdjustmentSummary/jailAdjustmentSectionSummaryNoteItemsActionMenu.html?jailAdjustmentSectionSummaryNoteItemIndex=${jailAdjustmentSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
		</tr>
	</thead>
	<tbody id="jailAdjustmentSectionSummaryNoteTableBody">
		<jsp:include page="jailAdjustmentSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>