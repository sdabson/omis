<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderphoto.msgs.offenderPhoto">
	<tr id="offenderPhotoAssociationNoteItemRow${offenderPhotoAssociationNoteItemIndex}" class="offenderPhotoAssociationNoteItemRow">
		<td>
			<a class="removeLink" id="removeLink${offenderPhotoAssociationNoteItemIndex}" href="${pageContext.request.contextPath}/offenderPhoto/removeOffenderPhotoAssociationNote.html?offenderPhoto=${offenderPhotoAssociationNoteItem.note.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="offenderPhotoAssociationNoteId${offenderPhotoAssociationNoteItemIndex}" name="noteItems[${offenderPhotoAssociationNoteItemIndex}].note" value="${offenderPhotoAssociationNoteItem.note.id}"/>
			<form:errors path="noteItems[${offenderPhotoAssociationNoteItemIndex}].note" cssClass="error"/>
			<input type="hidden" id="offenderPhotoAssociationNoteOperation${offenderPhotoAssociationNoteItemIndex}" name="noteItems[${offenderPhotoAssociationNoteItemIndex}].operation" value="${offenderPhotoAssociationNoteItem.operation}"/>
			<form:errors path="noteItems[${offenderPhotoAssociationNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="offenderPhotoAssociationNoteDate" value="${offenderPhotoAssociationNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="noteItems[${offenderPhotoAssociationNoteItemIndex}].date" id="offenderPhotoAssociationNoteItemDate${offenderPhotoAssociationNoteItemIndex}" value="${offenderPhotoAssociationNoteDate}"/>
			<form:errors path="noteItems[${offenderPhotoAssociationNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" maxlength="4000" placeholder='<fmt:message key="noteValueDescription"/>' name="noteItems[${offenderPhotoAssociationNoteItemIndex}].value" id="offenderPhotoAssociationNoteItems[${offenderPhotoAssociationNoteItemIndex}].value" style="width: 95%"><c:out value="${offenderPhotoAssociationNoteItem.value}"/></textarea>
			<span class="characterCounter" id="valueCharacterCounter${offenderPhotoAssociationNoteItemIndex}">
			</span>
			<form:errors path="noteItems[${offenderPhotoAssociationNoteItemIndex}].value" cssClass="error"/>
		</td>		
		<td>
			<c:if test="${not empty offenderPhotoAssociationNoteItem.note.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${offenderPhotoAssociationNoteItem.note.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${offenderPhotoAssociationNoteItem.note.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>