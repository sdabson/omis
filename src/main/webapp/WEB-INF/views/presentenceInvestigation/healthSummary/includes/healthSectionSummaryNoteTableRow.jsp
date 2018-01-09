<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
	<tr id="healthSectionSummaryNoteItemRow${healthSectionSummaryNoteItemIndex}" class="healthSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeHealthSectionSummaryNoteLink${healthSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/healthSectionSummary/removeHealthSectionSummaryNote.html?healthSectionSummary=${healthSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="healthSectionSummaryNoteId${healthSectionSummaryNoteItemIndex}" name="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].healthSectionSummaryNote" value="${healthSectionSummaryNoteItem.healthSectionSummaryNote.id}"/>
			<form:errors path="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].healthSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="healthSectionSummaryNoteOperation${healthSectionSummaryNoteItemIndex}" name="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].itemOperation" value="${healthSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>		
		<td>
			<fmt:formatDate var="healthSectionSummaryNoteDate" value="${healthSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].date" id="healthSectionSummaryNoteItemDate${healthSectionSummaryNoteItemIndex}" value="${healthSectionSummaryNoteDate}"/>
			<form:errors path="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].description" id="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${healthSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="healthSectionSummaryNoteItems[${healthSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty healthSectionSummaryNoteItem.healthSectionSummaryNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${healthSectionSummaryNoteItem.healthSectionSummaryNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${healthSectionSummaryNoteItem.healthSectionSummaryNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 