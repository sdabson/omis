<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.assessment.msgs.assessmentNotes">
<table id="ratingNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="ratingNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/assessment/rating/notes/ratingNoteItemsActionMenu.html?ratingNoteItemIndex=${ratingNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="descriptionLabel"/></th>
			<th><fmt:message key="updatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="ratingNoteTableBody">
		<jsp:include page="ratingNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>