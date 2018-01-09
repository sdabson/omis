<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<table id="hearingNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="hearingNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/hearing/hearingNoteItemsActionMenu.html?hearingNoteItemIndex=${hearingNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="descriptionLabel"/></th>
		</tr>
	</thead>
	<tbody id="hearingNoteTableBody">
		<jsp:include page="hearingNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>