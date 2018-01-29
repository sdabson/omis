<%--
 - Author: Yidong Li
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.family.msgs.family">
	<tr id="noteRow[${familyAssociationNoteIndex}]" class="${cssClass}">
		<td class="operations">
  			<input type="hidden" name="familyAssociationNoteItems[${familyAssociationNoteIndex}].operation" id="familyAssociationNoteItemsOperation${familyAssociationNoteIndex}" value="${familyAssociationNoteItem.operation}"/> 
			<a href="#" id="removeNoteItem[${familyAssociationNoteIndex}]" class="removeLink"><span class="linkLabel"><fmt:message key="deletefamilyAssociationNoteLink"/></span></a>
		</td>
		<td class="twoColumn">
			<fmt:formatDate value="${familyAssociationNoteItems[familyAssociationNoteIndex].date}" type="date" pattern="MM/dd/yyyy" var="formattedResultsDate"/>
			<input type="text" class="date" id="noteDate${familyAssociationNoteIndex}" name="familyAssociationNoteItems[${familyAssociationNoteIndex}].date" value="${formattedResultsDate}"/>
			<form:errors path="familyAssociationNoteItems[${familyAssociationNoteIndex}].date" cssClass="error"/>
		</td>
		<td class="twoColumn">
 			<textarea name="familyAssociationNoteItems[${familyAssociationNoteIndex}].note"><c:out value="${familyAssociationNoteItems[familyAssociationNoteIndex].note}"/></textarea>
 			<form:errors path="familyAssociationNoteItems[${familyAssociationNoteIndex}].note" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>