<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
	<tr id="educationSectionSummaryNoteItemRow${educationSectionSummaryNoteItemIndex}" class="educationSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeEducationSectionSummaryNoteLink${educationSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/educationSectionSummary/removeEducationSectionSummaryNote.html?educationSectionSummary=${educationSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="educationSectionSummaryNoteId${educationSectionSummaryNoteItemIndex}" name="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].educationSectionSummaryNote" value="${educationSectionSummaryNoteItem.educationSectionSummaryNote.id}"/>
			<form:errors path="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].educationSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="educationSectionSummaryNoteOperation${educationSectionSummaryNoteItemIndex}" name="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].itemOperation" value="${educationSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="educationSectionSummaryNoteDate" value="${educationSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].date" id="educationSectionSummaryNoteItemDate${educationSectionSummaryNoteItemIndex}" value="${educationSectionSummaryNoteDate}"/>
			<form:errors path="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].description" id="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${educationSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="educationSectionSummaryNoteItems[${educationSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty educationSectionSummaryNoteItem.educationSectionSummaryNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${educationSectionSummaryNoteItem.educationSectionSummaryNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${educationSectionSummaryNoteItem.educationSectionSummaryNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 