<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<tr id="detainerNoteItemRow${detainerNoteItemIndex}" class="detainerNoteItemRow">
		<td>
			<a class="removeLink removeNotItemRowLink" id="removeLink${detainerNoteItemIndex}" href="${pageContext.request.contextPath}/detainerNotification/removeDetainerNote.html?detainerNote=${detainerNoteItem.note.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="detainerNoteId${detainerNoteItemIndex}" name="noteItems[${detainerNoteItemIndex}].note" value="${detainerNoteItem.note.id}"/>
			<form:errors path="noteItems[${detainerNoteItemIndex}].note" cssClass="error"/>
			<input type="hidden" id="detainerNoteItemOperation${detainerNoteItemIndex}" name="noteItems[${detainerNoteItemIndex}].operation" value="${detainerNoteItem.operation}"/>
			<form:errors path="noteItems[${detainerNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="noteItems[${detainerNoteItemIndex}].value" id="noteItems[${detainerNoteItemIndex}].value" maxlength="2048" class="countableCharacters" style="width: 95%"><c:out value="${detainerNoteItem.value}"/></textarea>
			
			<form:errors path="noteItems[${detainerNoteItemIndex}].value" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="detainerNoteDate" value="${detainerNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="noteItems[${detainerNoteItemIndex}].date" id="detainerNoteItemDate${detainerNoteItemIndex}" value="${detainerNoteDate}"/>
			<form:errors path="noteItems[${detainerNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty detainerNoteItem.note.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${detainerNoteItem.note.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${detainerNoteItem.note.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>