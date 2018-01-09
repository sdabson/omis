<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
<table id="separationNeedNoteItemsTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="separationNeedNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/separationNeed/separationNeedNoteItemsActionMenu.html?separationNeedNoteItemIndex=${separationNeedNoteItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="lastUpdatedByLabelLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody id="separationNeedNoteItemTableBody">
		<jsp:include page="separationNeedNoteItemsTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>