<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.stg.msgs.stg">
<table id="activityNotesTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="activityNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/stg/activityNoteItemsActionMenu.html?activityNotesItemIndex=${activityNotesItemIndex}"></a></th>
			<th class="date"><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody id="activityNotesTableBody">
		<jsp:include page="activityNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>