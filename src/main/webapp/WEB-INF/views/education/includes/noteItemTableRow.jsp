<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.education.msgs.education">
	<tr id="noteItemRow${noteItemIndex}" class="noteItemRow">
		<td>
			<a class="removeLink" id="removeNoteLink${noteItemIndex}" href="${pageContext.request.contextPath}/education/removeNote.html?educationTerm=${educationSummary.educationTermId}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="noteId${noteItemIndex}" name="noteItems[${noteItemIndex}].note" value="${noteItem.note.id}"/>
			<form:errors path="noteItems[${noteItemIndex}].note" cssClass="error"/>
			<input type="hidden" id="noteOperation${noteItemIndex}" name="noteItems[${noteItemIndex}].operation" value="${noteItem.operation}"/>
			<form:errors path="noteItems[${noteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="noteDate" value="${noteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="noteItems[${noteItemIndex}].date" id="noteItemDate${noteItemIndex}" value="${noteDate}"/>
			<form:errors path="noteItems[${noteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="noteItems[${noteItemIndex}].description" id="noteItems[${noteItemIndex}].description" style="width: 95%"><c:out value="${noteItem.description}"/></textarea>
			<form:errors path="noteItems[${noteItemIndex}].description" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty noteItem.note.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${noteItem.note.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${noteItem.note.updateSignature.userAccount.user.name.firstName}"/>
						<fmt:param value="${noteItem.note.updateSignature.userAccount.username}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>