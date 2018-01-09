<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<tr id="activityNoteItemRow${activityNoteItemIndex}" class="activityNoteItemRow">
		<td>
			<a class="removeLink" id="removeNoteLink${activityNoteItemIndex}" href="${pageContext.request.contextPath}/stg/activity/removeActivityNote.html?affiliation=${activitySummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="activityNoteId${activityNoteItemIndex}" name="noteItems[${activityNoteItemIndex}].activityNote" value="${activityNoteItem.activityNote.id}"/>
			<form:errors path="noteItems[${activityNoteItemIndex}].activityNote" cssClass="error"/>
			<input type="hidden" id="activityNoteOperation${activityNoteItemIndex}" name="noteItems[${activityNoteItemIndex}].operation" value="${activityNoteItem.operation}"/>
			<form:errors path="noteItems[${activityNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="activityNoteDate" value="${activityNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="noteItems[${activityNoteItemIndex}].date" id="activityNoteItemDate${activityNoteItemIndex}" value="${activityNoteDate}"/>
			<form:errors path="noteItems[${activityNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="noteItems[${activityNoteItemIndex}].value" id="activityNoteItems[${activityNoteItemIndex}].value" style="width: 95%"><c:out value="${activityNoteItem.value}"/></textarea>
			<form:errors path="noteItems[${activityNoteItemIndex}].value" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>