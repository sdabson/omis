<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.supervisionHistorySectionSummary">
	<tr id="supervisionHistoryNoteItemRow${supervisionHistoryNoteItemIndex}" class="supervisionHistoryNoteItemRow">
		<td>
			<a class="removeLink" id="removeSupervisionHistoryNoteLink${supervisionHistoryNoteItemIndex}" href="${pageContext.request.contextPath}/supervisionHistorySectionSummary/removeSupervisionHistoryNote.html?supervisionHistorySectionSummary=${supervisionHistorySectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="supervisionHistoryNoteId${supervisionHistoryNoteItemIndex}" name="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].supervisionHistoryNote" value="${supervisionHistoryNoteItem.supervisionHistoryNote.id}"/>
			<form:errors path="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].supervisionHistoryNote" cssClass="error"/>
			<input type="hidden" id="supervisionHistoryNoteOperation${supervisionHistoryNoteItemIndex}" name="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].itemOperation" value="${supervisionHistoryNoteItem.itemOperation}"/>
			<form:errors path="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="supervisionHistoryNoteDate" value="${supervisionHistoryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].date" id="supervisionHistoryNoteItemDate${supervisionHistoryNoteItemIndex}" value="${supervisionHistoryNoteDate}"/>
			<form:errors path="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td class="note">
			<textarea rows="4" name="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].description" id="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].description" style="width: 95%"><c:out value="${supervisionHistoryNoteItem.description}"/></textarea>
			<form:errors path="supervisionHistoryNoteItems[${supervisionHistoryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty supervisionHistoryNoteItem.supervisionHistoryNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${supervisionHistoryNoteItem.supervisionHistoryNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${supervisionHistoryNoteItem.supervisionHistoryNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle>