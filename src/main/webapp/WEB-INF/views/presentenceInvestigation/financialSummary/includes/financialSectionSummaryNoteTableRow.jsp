<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSectionSummary">
	<tr id="financialSectionSummaryNoteItemRow${financialSectionSummaryNoteItemIndex}" class="financialSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeFinancialSectionSummaryNoteLink${financialSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/financialSectionSummary/removeFinancialSectionSummaryNote.html?financialSectionSummary=${financialSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="financialSectionSummaryNoteId${financialSectionSummaryNoteItemIndex}" name="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].financialSectionSummaryNote" value="${financialSectionSummaryNoteItem.financialSectionSummaryNote.id}"/>
			<form:errors path="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].financialSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="financialSectionSummaryNoteOperation${financialSectionSummaryNoteItemIndex}" name="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].itemOperation" value="${financialSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].description" id="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${financialSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="financialSectionSummaryNoteDate" value="${financialSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].date" id="financialSectionSummaryNoteItemDate${financialSectionSummaryNoteItemIndex}" value="${financialSectionSummaryNoteDate}"/>
			<form:errors path="financialSectionSummaryNoteItems[${financialSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 