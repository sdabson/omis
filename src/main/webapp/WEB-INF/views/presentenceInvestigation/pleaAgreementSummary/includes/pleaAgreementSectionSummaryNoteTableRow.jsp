<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
	<tr id="pleaAgreementSectionSummaryNoteItemRow${pleaAgreementSectionSummaryNoteItemIndex}" class="pleaAgreementSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removePleaAgreementSectionSummaryNoteLink${pleaAgreementSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/pleaAgreementSectionSummary/removePleaAgreementSectionSummaryNote.html?pleaAgreementSectionSummary=${pleaAgreementSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="pleaAgreementSectionSummaryNoteId${pleaAgreementSectionSummaryNoteItemIndex}" name="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].pleaAgreementSectionSummaryNote" value="${pleaAgreementSectionSummaryNoteItem.pleaAgreementSectionSummaryNote.id}"/>
			<form:errors path="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].pleaAgreementSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="pleaAgreementSectionSummaryNoteOperation${pleaAgreementSectionSummaryNoteItemIndex}" name="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].itemOperation" value="${pleaAgreementSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].description" id="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${pleaAgreementSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="pleaAgreementSectionSummaryNoteDate" value="${pleaAgreementSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].date" id="pleaAgreementSectionSummaryNoteItemDate${pleaAgreementSectionSummaryNoteItemIndex}" value="${pleaAgreementSectionSummaryNoteDate}"/>
			<form:errors path="pleaAgreementSectionSummaryNoteItems[${pleaAgreementSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 