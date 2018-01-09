<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.otherPertinentInformationSectionSummary">
	<tr id="otherPertinentInformationSectionSummaryNoteItemRow${otherPertinentInformationSectionSummaryNoteItemIndex}" class="otherPertinentInformationSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeOtherPertinentInformationSectionSummaryNoteLink${otherPertinentInformationSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/otherPertinentInformationSectionSummary/removeOtherPertinentInformationSectionSummaryNote.html?otherPertinentInformationSectionSummary=${otherPertinentInformationSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="otherPertinentInformationSectionSummaryNoteId${otherPertinentInformationSectionSummaryNoteItemIndex}" name="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].otherPertinentInformationSectionSummaryNote" value="${otherPertinentInformationSectionSummaryNoteItem.otherPertinentInformationSectionSummaryNote.id}"/>
			<form:errors path="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].otherPertinentInformationSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="otherPertinentInformationSectionSummaryNoteOperation${otherPertinentInformationSectionSummaryNoteItemIndex}" name="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].itemOperation" value="${otherPertinentInformationSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].description" id="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${otherPertinentInformationSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="otherPertinentInformationSectionSummaryNoteDate" value="${otherPertinentInformationSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].date" id="otherPertinentInformationSectionSummaryNoteItemDate${otherPertinentInformationSectionSummaryNoteItemIndex}" value="${otherPertinentInformationSectionSummaryNoteDate}"/>
			<form:errors path="otherPertinentInformationSectionSummaryNoteItems[${otherPertinentInformationSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 