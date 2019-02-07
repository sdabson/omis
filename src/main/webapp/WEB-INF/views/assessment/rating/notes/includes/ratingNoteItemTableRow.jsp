<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.assessment.msgs.ratingNotes">
	<tr id="ratingNoteItemRow${ratingNoteItemIndex}" class="ratingNoteItemRow">
		<td>
			<a class="removeLink" id="removeRatingNoteLink${ratingNoteItemIndex}" href="${pageContext.request.contextPath}/assessment/rating/notes/removeRatingNote.html?assessment=${assessment.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="ratingNoteId${ratingNoteItemIndex}" name="ratingNoteItems[${ratingNoteItemIndex}].ratingNote" value="${ratingNoteItem.ratingNote.id}"/>
			<form:errors path="ratingNoteItems[${ratingNoteItemIndex}].ratingNote" cssClass="error"/>
			<input type="hidden" id="ratingNoteOperation${ratingNoteItemIndex}" name="ratingNoteItems[${ratingNoteItemIndex}].itemOperation" value="${ratingNoteItem.itemOperation}"/>
			<form:errors path="ratingNoteItems[${ratingNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="ratingNoteDate" value="${ratingNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="ratingNoteItems[${ratingNoteItemIndex}].date" id="ratingNoteItemDate${ratingNoteItemIndex}" value="${ratingNoteDate}"/>
			<form:errors path="ratingNoteItems[${ratingNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="ratingNoteItems[${ratingNoteItemIndex}].description" id="ratingNoteItems[${ratingNoteItemIndex}].description" style="width: 95%"><c:out value="${ratingNoteItem.description}"/></textarea>
			<form:errors path="ratingNoteItems[${ratingNoteItemIndex}].description" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty ratingNoteItem.ratingNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${ratingNoteItem.ratingNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${ratingNoteItem.ratingNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 