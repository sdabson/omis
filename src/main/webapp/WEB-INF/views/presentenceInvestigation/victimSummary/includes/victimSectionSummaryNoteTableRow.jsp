<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
	<tr id="victimSectionSummaryNoteItemRow${victimSectionSummaryNoteItemIndex}" class="victimSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeVictimSectionSummaryNoteLink${victimSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/victimSectionSummary/removeVictimSectionSummaryNote.html?victimSectionSummary=${victimSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="victimSectionSummaryNoteId${victimSectionSummaryNoteItemIndex}" name="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].victimSectionSummaryNote" value="${victimSectionSummaryNoteItem.victimSectionSummaryNote.id}"/>
			<form:errors path="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].victimSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="victimSectionSummaryNoteOperation${victimSectionSummaryNoteItemIndex}" name="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].itemOperation" value="${victimSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="victimSectionSummaryNoteDate" value="${victimSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].date" id="victimSectionSummaryNoteItemDate${victimSectionSummaryNoteItemIndex}" value="${victimSectionSummaryNoteDate}"/>
			<form:errors path="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].description" id="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${victimSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="victimSectionSummaryNoteItems[${victimSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 