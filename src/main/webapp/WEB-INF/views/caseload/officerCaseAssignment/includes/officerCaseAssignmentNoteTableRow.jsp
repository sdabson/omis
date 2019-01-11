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
 - Version: 0.1.0 (Sep 10, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caseload.msgs.officerCaseAssignment">
	<tr id="officerCaseAssignmentNoteItemRow${officerCaseAssignmentNoteIndex}" class="officerCaseAssignmentNoteItemRow">
		<td>
			<a class="removeLink" id="removeOfficerCaseAssignmentNoteLink${officerCaseAssignmentNoteIndex}" href="${pageContext.request.contextPath}/officerCaseAssignment/removeOfficerCaseAssignmentNote.html"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="officerCaseAssignmentNoteId${officerCaseAssignmentNoteIndex}" name="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].officerCaseAssignmentNote" value="${officerCaseAssignmentNoteItem.officerCaseAssignmentNote.id}"/>
			<form:errors path="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].officerCaseAssignmentNote" cssClass="error"/>
			<input type="hidden" id="officerCaseAssignmentNoteOperation${officerCaseAssignmentNoteIndex}" name="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].operation" value="${operation}"/>
			<form:errors path="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].operation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="officerCaseAssignmentNoteDate" value="${officerCaseAssignmentNoteItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].date" id="officerCaseAssignmentNoteItemDate${officerCaseAssignmentNoteIndex}" value="${officerCaseAssignmentNoteDate}"/>
			<form:errors path="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].date" cssClass="error"/>
		</td>
		<td>
			<textarea rows="4" name="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].value" id="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].value" style="width: 95%"><c:out value="${officerCaseAssignmentNoteItem.value}"/></textarea>
			<form:errors path="officerCaseAssignmentNoteItems[${officerCaseAssignmentNoteIndex}].value" cssClass="error"/>
		</td> 
		<td>
			<c:if test="${not empty officerCaseAssignmentNoteItem.officerCaseAssignmentNote}">
				<c:set var="updatable" value="${officerCaseAssignmentNoteItem.officerCaseAssignmentNote}" scope="request"/>
				<jsp:include page="/WEB-INF/views/audit/includes/lastUpdatedBy.jsp"/>
			</c:if>
		</td>
	</tr>
</fmt:bundle> 