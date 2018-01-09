<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.warrant.msgs.warrant">
	<tr id="warrantNoteItemRow${warrantNoteItemIndex}" class="warrantNoteItemRow">
		<td>
			<a class="removeLink" id="removeWarrantNoteLink${warrantNoteItemIndex}" href="${pageContext.request.contextPath}/warrant/removeWarrantNote.html?warrant=${warrant.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="warrantNoteId${warrantNoteItemIndex}" name="warrantNoteItems[${warrantNoteItemIndex}].warrantNote" value="${warrantNoteItem.warrantNote.id}"/>
			<form:errors path="warrantNoteItems[${warrantNoteItemIndex}].warrantNote" cssClass="error"/>
			<input type="hidden" id="warrantNoteOperation${warrantNoteItemIndex}" name="warrantNoteItems[${warrantNoteItemIndex}].itemOperation" value="${warrantNoteItem.itemOperation}"/>
			<form:errors path="warrantNoteItems[${warrantNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="warrantNoteDate" value="${warrantNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="warrantNoteItems[${warrantNoteItemIndex}].date" id="warrantNoteItemDate${warrantNoteItemIndex}" value="${warrantNoteDate}"/>
			<form:errors path="warrantNoteItems[${warrantNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="warrantNoteItems[${warrantNoteItemIndex}].note" id="warrantNoteItems[${warrantNoteItemIndex}].note" style="width: 95%"><c:out value="${warrantNoteItem.note}"/></textarea>
			<form:errors path="warrantNoteItems[${warrantNoteItemIndex}].note" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle> 