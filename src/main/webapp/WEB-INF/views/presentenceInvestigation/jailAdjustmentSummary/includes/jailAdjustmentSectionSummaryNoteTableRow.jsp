<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.jailAdjustmentSectionSummary">
	<tr id="jailAdjustmentSectionSummaryNoteItemRow${jailAdjustmentSectionSummaryNoteItemIndex}" class="jailAdjustmentSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeJailAdjustmentSectionSummaryNoteLink${jailAdjustmentSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/jailAdjustmentSectionSummary/removeJailAdjustmentSectionSummaryNote.html?jailAdjustmentSectionSummary=${jailAdjustmentSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="jailAdjustmentSectionSummaryNoteId${jailAdjustmentSectionSummaryNoteItemIndex}" name="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].jailAdjustmentSectionSummaryNote" value="${jailAdjustmentSectionSummaryNoteItem.jailAdjustmentSectionSummaryNote.id}"/>
			<form:errors path="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].jailAdjustmentSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="jailAdjustmentSectionSummaryNoteOperation${jailAdjustmentSectionSummaryNoteItemIndex}" name="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].itemOperation" value="${jailAdjustmentSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].description" id="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${jailAdjustmentSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="jailAdjustmentSectionSummaryNoteDate" value="${jailAdjustmentSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].date" id="jailAdjustmentSectionSummaryNoteItemDate${jailAdjustmentSectionSummaryNoteItemIndex}" value="${jailAdjustmentSectionSummaryNoteDate}"/>
			<form:errors path="jailAdjustmentSectionSummaryNoteItems[${jailAdjustmentSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 