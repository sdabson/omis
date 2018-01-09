<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
	<tr id="presentenceInvestigationRequestNoteItemRow${presentenceInvestigationRequestNoteItemIndex}" class="presentenceInvestigationRequestNoteItemRow">
		<td>
			<a class="removeLink" id="removePresentenceInvestigationRequestNoteLink${presentenceInvestigationRequestNoteItemIndex}" href="${pageContext.request.contextPath}/presentenceInvestigationRequest/removePresentenceInvestigationRequestNote.html?presentenceInvestigationRequest=${presentenceInvestigationRequest.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="presentenceInvestigationRequestNoteId${presentenceInvestigationRequestNoteItemIndex}" name="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].presentenceInvestigationRequestNote" value="${presentenceInvestigationRequestNoteItem.presentenceInvestigationRequestNote.id}"/>
			<form:errors path="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].presentenceInvestigationRequestNote" cssClass="error"/>
			<input type="hidden" id="presentenceInvestigationRequestNoteOperation${presentenceInvestigationRequestNoteItemIndex}" name="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].itemOperation" value="${presentenceInvestigationRequestNoteItem.itemOperation}"/>
			<form:errors path="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="presentenceInvestigationRequestNoteDate" value="${presentenceInvestigationRequestNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].date" id="presentenceInvestigationRequestNoteItemDate${presentenceInvestigationRequestNoteItemIndex}" value="${presentenceInvestigationRequestNoteDate}"/>
			<form:errors path="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].description" id="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].description" style="width: 95%"><c:out value="${presentenceInvestigationRequestNoteItem.description}"/></textarea>
			<form:errors path="presentenceInvestigationRequestNoteItems[${presentenceInvestigationRequestNoteItemIndex}].description" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 