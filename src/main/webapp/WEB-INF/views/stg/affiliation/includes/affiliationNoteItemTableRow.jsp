<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<tr id="affiliationNoteItemRow${affiliationNoteItemIndex}" class="affiliationNoteItemRow">
		<td>
			<a class="removeLink" id="removeLink${affiliationNoteItemIndex}" href="${pageContext.request.contextPath}/stg/affiliation/removeAffiliationNote.html?affiliation=${affiliationSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="affiliationNoteId${affiliationNoteItemIndex}" name="affiliationNoteItems[${affiliationNoteItemIndex}].affiliationNote" value="${affiliationNoteItem.affiliationNote.id}"/>
			<form:errors path="affiliationNoteItems[${affiliationNoteItemIndex}].affiliationNote" cssClass="error"/>
			<input type="hidden" id="affiliationNoteOperation${affiliationNoteItemIndex}" name="affiliationNoteItems[${affiliationNoteItemIndex}].operation" value="${affiliationNoteItem.operation}"/>
			<form:errors path="affiliationNoteItems[${affiliationNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="affiliationNoteItems[${affiliationNoteItemIndex}].note" id="affiliationNoteItems[${affiliationNoteItemIndex}].note" style="width: 95%"><c:out value="${affiliationNoteItem.note}"/></textarea>
			<form:errors path="affiliationNoteItems[${affiliationNoteItemIndex}].note" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="affiliationNoteDate" value="${affiliationNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="affiliationNoteItems[${affiliationNoteItemIndex}].date" id="affiliationNoteItemDate${affiliationNoteItemIndex}" value="${affiliationNoteDate}"/>
			<form:errors path="affiliationNoteItems[${affiliationNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty affiliationNoteItem.affiliationNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${affiliationNoteItem.affiliationNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${affiliationNoteItem.affiliationNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>