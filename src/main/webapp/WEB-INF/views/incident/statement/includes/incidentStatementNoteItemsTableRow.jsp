<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.incident.msgs.incident">
	<tr id="incidentStatementNoteItemRow${incidentStatementNoteItemIndex}" class="involvedPartyItemRow">
		<td>
			<a class="removeLink" id="incidentStatementNoteItemRemoveLink${incidentStatementNoteItemIndex}" href="${pageContext.request.contextPath}/incident/statement/removeInvolvedParty.html?incidentStatementNote=${incidentStatementNoteItem.note.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].operation" name="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].operation" value="${incidentStatementNoteItem.operation}"/>
			<form:errors path="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].operation" cssClass="error"/>
			<input type="hidden" id="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].note" name="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].note" value="${incidentStatementNoteItem.note.id}"/>
			<form:errors path="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].note" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].value" id="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].value"><c:out value="${incidentStatementNoteItem.value}"/></textarea>
			<form:errors path="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].value" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="incidentStatementNoteDate" value="${incidentStatementNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" id="noteDate${incidentStatementNoteItemIndex}" name="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].date" id="incidentStatementNoteItemDate${incidentStatementNoteItemIndex}" value="${incidentStatementNoteDate}"/>
			<form:errors path="incidentStatementNoteItems[${incidentStatementNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty incidentStatementNoteItem.note.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${incidentStatementNoteItem.note.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${incidentStatementNoteItem.note.updateSignature.userAccount.user.name.firstName}"/>
						<fmt:param value="${incidentStatementNoteItem.note.updateSignature.userAccount.username}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>