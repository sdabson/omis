<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.boardhearing.msgs.boardHearing">
	<tr id="boardHearingNoteItemRow${boardHearingNoteItemIndex}" class="boardHearingNoteItemRow">
		<td>
			<a class="removeLink" id="removeBoardHearingNoteLink${boardHearingNoteItemIndex}" href="${pageContext.request.contextPath}/boardHearing/removeBoardHearingNote.html?boardHearing=${boardHearing.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="boardHearingNoteId${boardHearingNoteItemIndex}" name="boardHearingNoteItems[${boardHearingNoteItemIndex}].boardHearingNote" value="${boardHearingNoteItem.boardHearingNote.id}"/>
			<form:errors path="boardHearingNoteItems[${boardHearingNoteItemIndex}].boardHearingNote" cssClass="error"/>
			<input type="hidden" id="boardHearingNoteOperation${boardHearingNoteItemIndex}" name="boardHearingNoteItems[${boardHearingNoteItemIndex}].itemOperation" value="${boardHearingNoteItem.itemOperation}"/>
			<form:errors path="boardHearingNoteItems[${boardHearingNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="boardHearingNoteDate" value="${boardHearingNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="boardHearingNoteItems[${boardHearingNoteItemIndex}].date" id="boardHearingNoteItemDate${boardHearingNoteItemIndex}" value="${boardHearingNoteDate}"/>
			<form:errors path="boardHearingNoteItems[${boardHearingNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="boardHearingNoteItems[${boardHearingNoteItemIndex}].description" id="boardHearingNoteItems[${boardHearingNoteItemIndex}].description" style="width: 95%"><c:out value="${boardHearingNoteItem.description}"/></textarea>
			<form:errors path="boardHearingNoteItems[${boardHearingNoteItemIndex}].description" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty boardHearingNoteItem.boardHearingNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${boardHearingNoteItem.boardHearingNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${boardHearingNoteItem.boardHearingNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 