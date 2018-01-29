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
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 23, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.boardhearingdecision.msgs.boardHearingDecision">
	<tr id="hearingDecisionNoteItemRow${hearingDecisionNoteIndex}" class="hearingDecisionNoteItemRow">
		<td>
			<a class="removeLink" id="removeHearingDecisionNoteLink${hearingDecisionNoteIndex}" href="${pageContext.request.contextPath}/boardHearing/removeHearingDecisionNote.html"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="hearingDecisionNoteId${hearingDecisionNoteIndex}" name="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].hearingDecisionNote" value="${hearingDecisionNoteItem.hearingDecisionNote.id}"/>
			<form:errors path="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].hearingDecisionNote" cssClass="error"/>
			<input type="hidden" id="hearingDecisionNoteOperation${hearingDecisionNoteIndex}" name="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].operation" value="${operation}"/>
			<form:errors path="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="hearingDecisionNoteDate" value="${hearingDecisionNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].date" id="hearingDecisionNoteItemDate${hearingDecisionNoteIndex}" value="${hearingDecisionNoteDate}"/>
			<form:errors path="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].value" id="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].value" style="width: 95%"><c:out value="${hearingDecisionNoteItem.value}"/></textarea>
			<form:errors path="hearingDecisionNoteItems[${hearingDecisionNoteIndex}].value" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty hearingDecisionNoteItem.hearingDecisionNote}">
				<c:set var="updatable" value="${hearingDecisionNoteItem.hearingDecisionNote}" scope="request"/>
				<jsp:include page="/WEB-INF/views/audit/includes/lastUpdatedBy.jsp"/>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 