<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.assessment.msgs.assessmentRating">
<table id="assessmentCategoryOverrideNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="assessmentCategoryOverrideNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/assessment/rating/assessmentCategoryOverrideNoteItemsActionMenu.html?assessmentCategoryOverrideNoteItemIndex=${assessmentCategoryOverrideNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
			<th><fmt:message key="updatedByLabel"/></th>
		</tr>
	</thead>
	<tbody id="assessmentCategoryOverrideNoteTableBody">
		<jsp:include page="assessmentCategoryOverrideNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>