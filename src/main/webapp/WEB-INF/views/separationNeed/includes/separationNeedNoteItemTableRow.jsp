<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<tr id="separationNeedNoteItemRow${separationNeedNoteItemIndex}" class="separationNeedNoteItemRow">
		<td>
			<a class="removeLink" id="noteRemoveLink${separationNeedNoteItemIndex}" href="${pageContext.request.contextPath}/separationNeed/removeSeparationNeedNote.html?separationNeedNote=${separationNeedNoteItem.separationNeedNote.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="separationNeedNoteItems[${separationNeedNoteItemIndex}].separationNeedNote" name="separationNeedNoteItems[${separationNeedNoteItemIndex}].separationNeedNote" value="${separationNeedNoteItem.separationNeedNote.id}"/>
			<form:errors path="separationNeedNoteItems[${separationNeedNoteItemIndex}].separationNeedNote" cssClass="error"/>
			<input type="hidden" id="separationNeedNoteItems[${separationNeedNoteItemIndex}].operation" name="separationNeedNoteItems[${separationNeedNoteItemIndex}].operation" value="${separationNeedNoteItem.operation}"/>
			<form:errors path="separationNeedNoteItems[${separationNeedNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="separationNeedNoteDate" value="${separationNeedNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" id="noteDate${separationNeedNoteItemIndex}" name="separationNeedNoteItems[${separationNeedNoteItemIndex}].date" id="separationNeedNoteItemDate${separationNeedNoteItemIndex}" value="${separationNeedNoteDate}"/>
			<form:errors path="separationNeedNoteItems[${separationNeedNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty separationNeedNoteItem.separationNeedNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${separationNeedNoteItem.separationNeedNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${separationNeedNoteItem.separationNeedNote.updateSignature.userAccount.user.name.firstName}"/>
						<fmt:param value="${separationNeedNoteItem.separationNeedNote.updateSignature.userAccount.username}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
		<td>
			<textarea rows="4" maxlength="2048" name="separationNeedNoteItems[${separationNeedNoteItemIndex}].note" id="separationNeedNoteItems[${separationNeedNoteItemIndex}].note"><c:out value="${separationNeedNoteItem.note}"/></textarea>
			<span class="characterCounter" id="noteItem${separationNeedNoteItemIndex}CharacterCounter">
			</span>
			<form:errors path="separationNeedNoteItems[${separationNeedNoteItemIndex}].note" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>