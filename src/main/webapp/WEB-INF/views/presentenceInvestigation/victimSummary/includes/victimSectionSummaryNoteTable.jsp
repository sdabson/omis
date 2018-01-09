<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
<table id="victimSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="victimSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/victimSectionSummaryNoteItemsActionMenu.html?victimSectionSummaryNoteItemIndex=${victimSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody id="victimSectionSummaryNoteTableBody">
		<jsp:include page="victimSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>