<%--
 - Table row to edit victim note.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<c:set var="removeRow" value="${'REMOVE' eq operation.name ? 'removeRow' : ''}"/>
<tr id="noteItems[${victimNoteItemIndex}].row" class="${removeRow}">
	<td>	
		<input type="hidden" id="noteItemOperation${victimNoteItemIndex}" name="noteItems[${victimNoteItemIndex}].operation" value="${victimNoteItem.operation.name}"/>
		<input type="hidden"  id="noteItem${victimNoteItemIndex}Note" name="noteItems[${victimNoteItemIndex}].note" value="${victimNoteItem.note.id}"/> 
		<input type="hidden" name="noteItems[${victimNoteItemIndex}].association" value="${victimNoteItem.association.id}"/> 
		<a class="actionMenuItem rowActionMenuLinks"
				id="noteItem${victimNoteItemIndex}SummaryActionMenuLink"
				href="${pageContext.request.contextPath}/victim/association/associationNotesRowActionMenu.html?note=${victimNoteItem.note.id}&amp;operation=${victimNoteItem.operation.name}&amp;itemIndex=${victimNoteItemIndex}"></a>
	</td>
	<td>
		<select name="noteItems[${victimNoteItemIndex}].category">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			<c:forEach var="victimNoteCategory" items="${victimNoteCategories}">
				<c:choose>
					<c:when test="${victimNoteItem.category eq victimNoteCategory}">
						<option value="${victimNoteCategory.id}" selected="selected"><c:out value="${victimNoteCategory.name}"/></option>
					</c:when>
					<c:otherwise>
						<option value="${victimNoteCategory.id}"><c:out value="${victimNoteCategory.name}"/></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<form:errors path="noteItems[${victimNoteItemIndex}].category" cssClass="error"/>
	</td>
	<c:if test="${allowVictimNoteAssociationEdit}">
		<td>
			EDIT ASSOCIATION
		</td>
	</c:if>
	<td>
		<input id="noteItemsDate${victimNoteItemIndex}" name="noteItems[${victimNoteItemIndex}].date" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${victimNoteItem.date}'/>"/>
		<form:errors path="noteItems[${victimNoteItemIndex}].date" cssClass="error"/>
	</td>
	<td>
		<textarea name="noteItems[${victimNoteItemIndex}].value"><c:out value="${victimNoteItem.value}"/></textarea>
		<form:errors path="noteItems[${victimNoteItemIndex}].value" cssClass="error"/>
	</td>
</tr>