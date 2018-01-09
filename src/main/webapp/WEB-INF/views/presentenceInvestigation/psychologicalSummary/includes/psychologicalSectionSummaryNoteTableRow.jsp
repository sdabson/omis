<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
	<tr id="psychologicalSectionSummaryNoteItemRow${psychologicalSectionSummaryNoteItemIndex}" class="psychologicalSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removePsychologicalSectionSummaryNoteLink${psychologicalSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/psychologicalSectionSummary/removePsychologicalSectionSummaryNote.html?psychologicalSectionSummary=${psychologicalSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="psychologicalSectionSummaryNoteId${psychologicalSectionSummaryNoteItemIndex}" name="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].psychologicalSectionSummaryNote" value="${psychologicalSectionSummaryNoteItem.psychologicalSectionSummaryNote.id}"/>
			<form:errors path="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].psychologicalSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="psychologicalSectionSummaryNoteOperation${psychologicalSectionSummaryNoteItemIndex}" name="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].itemOperation" value="${psychologicalSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].description" id="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${psychologicalSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="psychologicalSectionSummaryNoteDate" value="${psychologicalSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].date" id="psychologicalSectionSummaryNoteItemDate${psychologicalSectionSummaryNoteItemIndex}" value="${psychologicalSectionSummaryNoteDate}"/>
			<form:errors path="psychologicalSectionSummaryNoteItems[${psychologicalSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 