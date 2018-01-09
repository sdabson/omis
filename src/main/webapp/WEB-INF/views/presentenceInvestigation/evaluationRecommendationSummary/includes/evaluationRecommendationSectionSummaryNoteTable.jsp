<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.evaluationRecommendationSectionSummary">
<table id="evaluationRecommendationSectionSummaryNoteTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="evaluationRecommendationSectionSummaryNoteItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/evaluationRecommendationSummary/evaluationRecommendationSectionSummaryNoteItemsActionMenu.html?evaluationRecommendationSectionSummaryNoteItemIndex=${evaluationRecommendationSectionSummaryNoteItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="noteLabel"/></th>
		</tr>
	</thead>
	<tbody id="evaluationRecommendationSectionSummaryNoteTableBody">
		<jsp:include page="evaluationRecommendationSectionSummaryNoteTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>