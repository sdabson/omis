<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.military.msgs.military">
	<tr id="serviceTermNoteItemRow${serviceTermNoteItemIndex}" class="serviceTermNoteItemRow">
		<td>
			<a class="removeLink" id="removeLink${serviceTermNoteItemIndex}" href="${pageContext.request.contextPath}/military/removeServiceTermNote.html?serviceTerm=${serviceTermSummary.militaryServiceTermId}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="serviceTermNoteId${serviceTermNoteItemIndex}" name="serviceTermNoteItems[${serviceTermNoteItemIndex}].serviceTermNote" value="${serviceTermNoteItem.serviceTermNote.id}"/>
			<form:errors path="serviceTermNoteItems[${serviceTermNoteItemIndex}].serviceTermNote" cssClass="error"/>
			<input type="hidden" id="serviceTermNoteOperation${serviceTermNoteItemIndex}" name="serviceTermNoteItems[${serviceTermNoteItemIndex}].operation" value="${serviceTermNoteItem.operation}"/>
			<form:errors path="serviceTermNoteItems[${serviceTermNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="serviceTermNoteDate" value="${serviceTermNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="serviceTermNoteItems[${serviceTermNoteItemIndex}].date" id="serviceTermNoteItemDate${serviceTermNoteItemIndex}" value="${serviceTermNoteDate}"/>
			<form:errors path="serviceTermNoteItems[${serviceTermNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="serviceTermNoteItems[${serviceTermNoteItemIndex}].note" id="serviceTermNoteItems[${serviceTermNoteItemIndex}].note" style="width: 95%"><c:out value="${serviceTermNoteItem.note}"/></textarea>
			<form:errors path="serviceTermNoteItems[${serviceTermNoteItemIndex}].note" cssClass="error"/>
		</td>		
		<td>
			<c:if test="${not empty serviceTermNoteItem.serviceTermNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${serviceTermNoteItem.serviceTermNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${serviceTermNoteItem.serviceTermNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>