<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
	<tr id="physicalFeatureAssociationNoteItemRow${physicalFeatureAssociationNoteItemIndex}" class="physicalFeatureAssociationNoteItemRow">
		<td>
			<a class="removeLink" id="removeNoteLink${physicalFeatureAssociationNoteItemIndex}" href="${pageContext.request.contextPath}/physicalFeature/removePhysicalFeatureAssociationNote.html?physicalFeatureAssociationNote=${physicalFeatureAssociationNoteItem.physicalFeatureAssociationNote.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="physicalFeatureAssociationNoteId${physicalFeatureAssociationNoteItemIndex}" name="noteItems[${physicalFeatureAssociationNoteItemIndex}].physicalFeatureAssociationNote" value="${physicalFeatureAssociationNoteItem.physicalFeatureAssociationNote.id}"/>
			<form:errors path="noteItems[${physicalFeatureAssociationNoteItemIndex}].physicalFeatureAssociationNote" cssClass="error"/>
			<input type="hidden" id="physicalFeatureAssociationNoteOperation${physicalFeatureAssociationNoteItemIndex}" name="noteItems[${physicalFeatureAssociationNoteItemIndex}].operation" value="${physicalFeatureAssociationNoteItem.operation}"/>
			<form:errors path="noteItems[${physicalFeatureAssociationNoteItemIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="physicalFeatureAssociationNoteDate" value="${physicalFeatureAssociationNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="noteItems[${physicalFeatureAssociationNoteItemIndex}].date" id="physicalFeatureAssociationNoteItemDate${physicalFeatureAssociationNoteItemIndex}" value="${physicalFeatureAssociationNoteDate}"/>
			<form:errors path="noteItems[${physicalFeatureAssociationNoteItemIndex}].date" cssClass="error"/>
		</td>		
		<td>
			<textarea rows="4" name="noteItems[${physicalFeatureAssociationNoteItemIndex}].note" id="noteItems[${physicalFeatureAssociationNoteItemIndex}].note" style="width: 95%"><c:out value="${physicalFeatureAssociationNoteItem.note}"/></textarea>
			<form:errors path="noteItems[${physicalFeatureAssociationNoteItemIndex}].note" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty physicalFeatureAssociationNoteItem.physicalFeatureAssociationNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${physicalFeatureAssociationNoteItem.physicalFeatureAssociationNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${physicalFeatureAssociationNoteItem.physicalFeatureAssociationNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>