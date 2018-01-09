<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.stg.msgs.stg">
<table id="affiliationNotesTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="affiliationNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/stg/affiliationNoteItemsActionMenu.html?affiliationNoteItemIndex=${affiliationNoteItemIndex}"></a></th>
			<th><fmt:message key="noteLabel"/></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="affiliationNotesTableBody">
		<jsp:include page="affiliationNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>