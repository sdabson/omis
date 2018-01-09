<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<table class="editTable" id="incidentStatementNoteItemsTable">
		<thead>
			<tr>
				<th class="operations"><a class="actionMenuItem" id="incidentStatementNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/incident/statement/incidentStatementNoteItemsActionMenu.html"></a></th>
				<th><label><fmt:message key="noteLabel"/></label></th>
				<th class="date"><label><fmt:message key="dateLabel"/></label></th>
				<th><label><fmt:message key="lastUpdatedByLabel"/></label></th>
			</tr>
		</thead>
		<tbody id="incidentStatementNoteItemsTableBody">
			<jsp:include page="incidentStatementNoteItemsTableBody.jsp"/>
		</tbody>
	</table>
</fmt:bundle>