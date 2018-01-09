<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.military.msgs.military">
<table id="serviceTermNotesTable" class="editTable">
	<thead class="notes">
		<tr>
			<th class="operations"><a class="actionMenuItem" id="serviceTermNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/military/militaryServiceTermNoteItemsActionMenu.html?serviceTermNoteItemIndex=${serviceTermNoteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="serviceTermNotesTableBody">
		<jsp:include page="serviceTermNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>