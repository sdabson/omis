<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
<table id="detainerNoteItemsTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="detainerNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/detainerNotification/detainerNoteItemsActionMenu.html?detainerNoteItemIndex=${detainerNoteItemIndex}"></a></th>
			<th><fmt:message key="detainerNoteTableHeading"/></th>
			<th class="date"><fmt:message key="dateTableHeading"/></th>
			<th><fmt:message key="lastUpdatedByTableHeading"/></th>
		</tr>
	</thead>
	<tbody id="detainerNoteItemsTableBody">
		<jsp:include page="noteItemsTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>