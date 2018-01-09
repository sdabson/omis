<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
	<tr id="chemicalUseSectionSummaryNoteItemRow${chemicalUseSectionSummaryNoteItemIndex}" class="chemicalUseSectionSummaryNoteItemRow">
		<td>
			<a class="removeLink" id="removeChemicalUseSectionSummaryNoteLink${chemicalUseSectionSummaryNoteItemIndex}" href="${pageContext.request.contextPath}/chemicalUseSectionSummary/removeChemicalUseSectionSummaryNote.html?chemicalUseSectionSummary=${chemicalUseSectionSummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="chemicalUseSectionSummaryNoteId${chemicalUseSectionSummaryNoteItemIndex}" name="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].chemicalUseSectionSummaryNote" value="${chemicalUseSectionSummaryNoteItem.chemicalUseSectionSummaryNote.id}"/>
			<form:errors path="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].chemicalUseSectionSummaryNote" cssClass="error"/>
			<input type="hidden" id="chemicalUseSectionSummaryNoteOperation${chemicalUseSectionSummaryNoteItemIndex}" name="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].itemOperation" value="${chemicalUseSectionSummaryNoteItem.itemOperation}"/>
			<form:errors path="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>		
		<td>
			<fmt:formatDate var="chemicalUseSectionSummaryNoteDate" value="${chemicalUseSectionSummaryNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].date" id="chemicalUseSectionSummaryNoteItemDate${chemicalUseSectionSummaryNoteItemIndex}" value="${chemicalUseSectionSummaryNoteDate}"/>
			<form:errors path="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].description" id="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].description" style="width: 95%"><c:out value="${chemicalUseSectionSummaryNoteItem.description}"/></textarea>
			<form:errors path="chemicalUseSectionSummaryNoteItems[${chemicalUseSectionSummaryNoteItemIndex}].description" cssClass="error"/>
		</td>
		<td>
			<c:if test="${not empty chemicalUseSectionSummaryNoteItem.chemicalUseSectionSummaryNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${chemicalUseSectionSummaryNoteItem.chemicalUseSectionSummaryNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${chemicalUseSectionSummaryNoteItem.chemicalUseSectionSummaryNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 