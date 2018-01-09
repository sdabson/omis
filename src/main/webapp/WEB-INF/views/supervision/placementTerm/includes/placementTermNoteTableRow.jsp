<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
  - Table row for notes for placement terms.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="auditBundle" basename="omis.audit.msgs.audit"/>
<fmt:setBundle var="placementTermBundle" basename="omis.supervision.msgs.placementTerm"/>
<c:if test="${placementTermNoteItem.operation.name eq 'REMOVE'}">
	<c:set var="placementTermNoteItemTableRowClass" value="removeRow"/>
</c:if>
<tr id="placementTermNoteItemTableRow${placementTermNoteIndex}" class="${placementTermNoteItemTableRowClass}">
	<td>
		<a id="placementTermNoteItemRemoveLink${placementTermNoteIndex}" href="${pageContext.request.contextPath}/supervision/placementTerm/removeNote.html?itemIndex=${placementTermNoteIndex}" class="removeLink" title="<fmt:message key='removePlacementTermNoteLink' bundle='${placementTermBundle}'/>"><span class="linkLabel"><fmt:message key="removePlacementTermNoteLink" bundle="${placementTermBundle}"/></span></a>
		<input id="placementTermNoteItemNote${placementTermNoteIndex}" name="noteItems[${placementTermNoteIndex}].note" type="hidden" value="${placementTermNoteItem.note.id}"/>
		<input id="placementTermNoteItemOperation${placementTermNoteIndex}" name="noteItems[${placementTermNoteIndex}].operation" type="hidden" value="${placementTermNoteItem.operation}"/>
	</td>
	<td>
		<input id="placementTermNoteItemDate${placementTermNoteIndex}" class="date" name="noteItems[${placementTermNoteIndex}].fields.date" type="text" value="<fmt:formatDate value='${placementTermNoteItem.fields.date}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="noteItems[${placementTermNoteIndex}].fields.date" cssClass="error"/>
	</td>
	<td>
		<textarea name="noteItems[${placementTermNoteIndex}].fields.value"><c:out value="${placementTermNoteItem.fields.value}"/></textarea>
		<form:errors path="noteItems[${placementTermNoteIndex}].fields.value" cssClass="error"/>
	</td>
	<td>
		<c:if test="${not empty placementTermNoteItem.note}">
			<fmt:message key="updateUserAccountFormat" bundle="${auditBundle}">
				<fmt:param>${placementTermNoteItem.note.updateSignature.userAccount.user.name.lastName}</fmt:param>
				<fmt:param>${placementTermNoteItem.note.updateSignature.userAccount.user.name.firstName}</fmt:param>
				<fmt:param>${placementTermNoteItem.note.updateSignature.userAccount.username}</fmt:param>
			</fmt:message>
		</c:if>
	</td>
</tr>