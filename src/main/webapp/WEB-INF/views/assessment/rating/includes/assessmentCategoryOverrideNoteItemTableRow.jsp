<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.assessment.msgs.assessmentRating">
	<tr id="assessmentCategoryOverrideNoteItemRow${assessmentCategoryOverrideNoteItemIndex}" class="assessmentCategoryOverrideNoteItemRow">
		<td>
			<a class="removeLink" id="removeAssessmentCategoryOverrideNoteLink${assessmentCategoryOverrideNoteItemIndex}" href="${pageContext.request.contextPath}/assessment/rating/removeAssessmentCategoryOverrideNote.html?assessment=${assessment.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="assessmentCategoryOverrideNoteId${assessmentCategoryOverrideNoteItemIndex}" name="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].assessmentCategoryOverrideNote" value="${assessmentCategoryOverrideNoteItem.assessmentCategoryOverrideNote.id}"/>
			<form:errors path="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].assessmentCategoryOverrideNote" cssClass="error"/>
			<input type="hidden" id="assessmentCategoryOverrideNoteOperation${assessmentCategoryOverrideNoteItemIndex}" name="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].itemOperation" value="${assessmentCategoryOverrideNoteItem.itemOperation}"/>
			<form:errors path="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="assessmentCategoryOverrideNoteDate" value="${assessmentCategoryOverrideNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].date" id="assessmentCategoryOverrideNoteItemDate${assessmentCategoryOverrideNoteItemIndex}" value="${assessmentCategoryOverrideNoteDate}"/>
			<form:errors path="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].description" id="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].description" style="width: 95%"><c:out value="${assessmentCategoryOverrideNoteItem.description}"/></textarea>
			<form:errors path="assessmentCategoryOverrideNoteItems[${assessmentCategoryOverrideNoteItemIndex}].description" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty assessmentCategoryOverrideNoteItem.assessmentCategoryOverrideNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${assessmentCategoryOverrideNoteItem.assessmentCategoryOverrideNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${assessmentCategoryOverrideNoteItem.assessmentCategoryOverrideNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 