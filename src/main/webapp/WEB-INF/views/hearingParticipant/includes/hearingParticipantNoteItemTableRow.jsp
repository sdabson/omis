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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearingparticipant.msgs.hearingParticipant">
	<tr id="hearingParticipantNoteItemRow${hearingParticipantNoteItemIndex}" class="hearingParticipantNoteItemRow">
		<td>
			<a class="removeLink" id="removeHearingParticipantNoteLink${hearingParticipantNoteItemIndex}" href="${pageContext.request.contextPath}/hearingParticipant/removehearingParticipantNote.html?hearingParticipant=${hearingParticipant.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="hearingParticipantNoteId${hearingParticipantNoteItemIndex}" name="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].hearingParticipantNote" value="${hearingParticipantNoteItem.hearingParticipantNote.id}"/>
			<form:errors path="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].hearingParticipantNote" cssClass="error"/>
			<input type="hidden" id="hearingParticipantNoteOperation${hearingParticipantNoteItemIndex}" name="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].itemOperation" value="${hearingParticipantNoteItem.itemOperation}"/>
			<form:errors path="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].itemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="hearingParticipantNoteDate" value="${hearingParticipantNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].date" id="hearingParticipantNoteItemDate${hearingParticipantNoteItemIndex}" value="${hearingParticipantNoteDate}"/>
			<form:errors path="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].description" id="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].description" style="width: 95%"><c:out value="${hearingParticipantNoteItem.description}"/></textarea>
			<form:errors path="hearingParticipantNoteItems[${hearingParticipantNoteItemIndex}].description" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty hearingParticipantNoteItem.hearingParticipantNote.updateSignature}">
				<label>
					<fmt:message key="lastUpdatedUserName">
						<fmt:param value="${hearingParticipantNoteItem.hearingParticipantNote.updateSignature.userAccount.user.name.lastName}"/>
						<fmt:param value="${hearingParticipantNoteItem.hearingParticipantNote.updateSignature.userAccount.user.name.firstName}"/>
					</fmt:message>
				</label>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 