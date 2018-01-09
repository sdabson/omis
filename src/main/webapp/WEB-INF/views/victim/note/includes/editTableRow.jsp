<%--
 - Table row to edit victim note.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<tr id="noteItems[${victimNoteItemIndex}].row">
	<td>
		<a id="noteItems[${victimNoteItemIndex}].removeLink" class="removeLink" href="${pageContext.request.contextPath}/victim/association/removeNote.html"><span class="linkLabel" title="<fmt:message key='removeLink' bundle='${commonBundle}'/>"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		<c:if test="${'UPDATE' eq victimNoteItem.operation.name}">
			<a id="noteItems[${victimNoteItemIndex}].disassociateLink" class="linkLink" href="${pageContext.request.contextPath}/victim/association/disassociateNote.html"><span class="linkLabel" title="<fmt:message key='disassociateLink' bundle='${commonBundle}'/>"><fmt:message key="disassociateLink" bundle="${commonBundle}"/></span></a>
		</c:if>
		<input id="noteItems[${victimNoteItemIndex}].operation" type="hidden" name="noteItems[${victimNoteItemIndex}].operation" value="${victimNoteItem.operation.name}"/>
		<input type="hidden" name="noteItems[${victimNoteItemIndex}].note" value="${victimNoteItem.note.id}"/>
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
		<input id="noteItems[${victimNoteItemIndex}].date" name="noteItems[${victimNoteItemIndex}].date" type="text" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${victimNoteItem.date}'/>"/>
		<form:errors path="noteItems[${victimNoteItemIndex}].date" cssClass="error"/>
	</td>
	<td>
		<textarea name="noteItems[${victimNoteItemIndex}].value"><c:out value="${victimNoteItem.value}"/></textarea>
		<form:errors path="noteItems[${victimNoteItemIndex}].value" cssClass="error"/>
	</td>
</tr>