<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.otherPertinentInformationSectionSummary">
<table id="otherPertinentInformationSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="otherPertinentInformationSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/otherPertinentInformationSummary/otherPertinentInformationSectionSummaryNoteItemsActionMenu.html?otherPertinentInformationSectionSummaryNoteItemIndex=${otherPertinentInformationSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="dateLabel"/></th>
		</tr>
	</thead>
	<tbody id="otherPertinentInformationSectionSummaryNoteTableBody">
		<jsp:include page="otherPertinentInformationSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>