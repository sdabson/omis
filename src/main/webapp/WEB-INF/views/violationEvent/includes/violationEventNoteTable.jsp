<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<table id="violationEventNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="violationEventNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/violationEvent/violationEventNoteItemsActionMenu.html?violationEventNoteItemIndex=${violationEventNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="byLabel"/></th>
		</tr>
	</thead>
	<tbody id="violationEventNoteTableBody">
		<jsp:include page="violationEventNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>