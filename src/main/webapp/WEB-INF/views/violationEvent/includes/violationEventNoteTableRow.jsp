<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<tr id="violationEventNoteItemRow${violationEventNoteItemIndex}" class="violationEventNoteItemRow">
		<td>
			<a class="removeLink" id="removeViolationEventNoteLink${violationEventNoteItemIndex}" href="${pageContext.request.contextPath}/violationEvent/removeViolationEventNote.html?violationEvent=${violationEvent.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="violationEventNoteId${violationEventNoteItemIndex}" name="violationEventNoteItems[${violationEventNoteItemIndex}].violationEventNote" value="${violationEventNoteItem.violationEventNote.id}"/>
			<form:errors path="violationEventNoteItems[${violationEventNoteItemIndex}].violationEventNote" cssClass="error"/>
			<input type="hidden" id="violationEventNoteOperation${violationEventNoteItemIndex}" name="violationEventNoteItems[${violationEventNoteItemIndex}].itemOperation" value="${violationEventNoteItem.itemOperation}"/>
			<form:errors path="violationEventNoteItems[${violationEventNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="violationEventNoteDate" value="${violationEventNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="violationEventNoteItems[${violationEventNoteItemIndex}].date" id="violationEventNoteItemDate${violationEventNoteItemIndex}" value="${violationEventNoteDate}"/>
			<form:errors path="violationEventNoteItems[${violationEventNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="violationEventNoteItems[${violationEventNoteItemIndex}].description" id="violationEventNoteItems[${violationEventNoteItemIndex}].description" style="width: 95%"><c:out value="${violationEventNoteItem.description}"/></textarea>
			<form:errors path="violationEventNoteItems[${violationEventNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty violationEventNoteItems[violationEventNoteItemIndex].violationEventNote}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${violationEventNoteItem.violationEventNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${violationEventNoteItem.violationEventNote.updateSignature.userAccount.user.name.firstName}"/>
						<fmt:param value="${violationEventNoteItem.violationEventNote.updateSignature.userAccount.username}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 