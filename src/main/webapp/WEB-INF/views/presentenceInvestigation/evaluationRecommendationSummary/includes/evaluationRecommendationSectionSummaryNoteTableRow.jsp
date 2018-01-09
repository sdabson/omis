<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.evaluationRecommendationSectionSummary">
	<c:choose>
		<c:when test="${evaluationRecommendationSectionSummaryNoteItem.itemOperation eq 'REMOVE'}">
			<c:set var="removeClass" value="removeRow" />
		</c:when>
	</c:choose>
	<tr id="evaluationRecommendationSectionSummaryNoteItemRow${evaluationRecommendationSectionSummaryNoteItemIndex}" class="evaluationRecommendationSectionSummaryNoteItemRow ${removeClass}">
		<td>
			<a class="removeLink" id="removeEvaluationRecommendationSectionSummaryNoteLink${evaluationRecommendationSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/evaluationRecommendationSectionSummary/removeEvaluationRecommendationSectionSummaryNote.html?evaluationRecommendationSectionSummary=${evaluationRecommendationSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="evaluationRecommendationSectionSummaryNoteId${evaluationRecommendationSectionSummaryNoteItemIndex}" name="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].evaluationRecommendationSectionSummaryNote" value="${evaluationRecommendationSectionSummaryNoteItem.evaluationRecommendationSectionSummaryNote.id}"/>
			<form:errors path="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].evaluationRecommendationSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="evaluationRecommendationSectionSummaryNoteOperation${evaluationRecommendationSectionSummaryNoteItemIndex}" name="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].itemOperation" value="${evaluationRecommendationSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="evaluationRecommendationSectionSummaryNoteDate" value="${evaluationRecommendationSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].date" id="evaluationRecommendationSectionSummaryNoteItemDate${evaluationRecommendationSectionSummaryNoteItemIndex}" value="${evaluationRecommendationSectionSummaryNoteDate}"/>
			<form:errors path="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].description" id="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${evaluationRecommendationSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="evaluationRecommendationSectionSummaryNoteItems[${evaluationRecommendationSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 