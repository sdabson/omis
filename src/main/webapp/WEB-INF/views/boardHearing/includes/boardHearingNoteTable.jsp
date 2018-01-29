<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
<table id="boardHearingNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="boardHearingNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/boardHearing/boardHearingNoteItemsActionMenu.html?boardHearingNoteItemIndex=${boardHearingNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="descriptionLabel"/></th>
			<th><fmt:message key="updatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="boardHearingNoteTableBody">
		<jsp:include page="boardHearingNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>