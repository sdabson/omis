<%--
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
	<tr id="workAssignmentNoteItems${workAssignmentNoteIndex}" <c:if test="${workAssignmentNoteItem.operation == 'REMOVE'}">class="removeRow"</c:if>>
		<td class="operations">
  			<input type="hidden" name="workAssignmentNoteItems[${workAssignmentNoteIndex}].operation" id="workAssignmentNoteItemsOperation${workAssignmentNoteIndex}" value="${workAssignmentNoteItem.operation}"/> 
			<a href="#" id="deleteNoteItem${workAssignmentNoteIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deleteWorkAssociationNoteLink"/></span></a>
		</td>
		<td class="twoColumn">
			<fmt:formatDate value="${workAssignmentNoteItems[workAssignmentNoteIndex].date}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
			<input type="text" class="date" id="noteDate${workAssignmentNoteIndex}" name="workAssignmentNoteItems[${workAssignmentNoteIndex}].date" value="${formattedResultsDate}"/>
			<form:errors path="workAssignmentNoteItems[${workAssignmentNoteIndex}].date" cssClass="error"/>
		</td>
		<td class="twoColumn">
 			<textarea name="workAssignmentNoteItems[${workAssignmentNoteIndex}].note"><c:out value="${workAssignmentNoteItems[workAssignmentNoteIndex].note}"/></textarea>
 			<form:errors path="workAssignmentNoteItems[${workAssignmentNoteIndex}].note" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>