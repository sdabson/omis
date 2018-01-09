<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
<table id="presentenceInvestigationRequestNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="presentenceInvestigationRequestNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/presentenceInvestigationRequestNoteItemsActionMenu.html?presentenceInvestigationRequestNoteItemIndex=${presentenceInvestigationRequestNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody id="presentenceInvestigationRequestNoteTableBody">
		<jsp:include page="presentenceInvestigationRequestNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>