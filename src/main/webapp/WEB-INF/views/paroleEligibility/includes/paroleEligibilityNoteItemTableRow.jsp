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
 - Author: Trevor Isles
 - Date: Dec 15, 2017
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<tr id="paroleEligibilityNoteItemRow${paroleEligibilityNoteItemIndex}" class="paroleEligibilityNoteItemRow">
	<td>
		<a class="removeLink" id="removeLink${paroleEligibilityNoteItemIndex}" href="${pageContext.request.contextPath}?paroleEligibility=${paroleEligibilitySummary.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
		<input type="hidden" id="paroleEligibilityNoteId${paroleEligibilityNoteItemIndex}" name="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].note" value="${paroleEligibilityNoteItem.note.id}"/>
		<form:errors path="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].note" cssClass="error"/>
		<input type="hidden" id="paroleEligibilityNoteOperation${paroleEligibilityNoteItemIndex}" name="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].operation" value="${paroleEligibilityNoteItem.operation}"/>
		<form:errors path="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].operation" cssClass="error"/>
	</td>	
	<td>
		<fmt:formatDate var="paroleEligibilityNoteDate" value="${paroleEligibilityNoteItem.date}" pattern="MM/dd/yyyy"/>
		<input type="text" class="date" name="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].date" id="paroleEligibilityNoteItemDate${paroleEligibilityNoteItemIndex}" value="${paroleEligibilityNoteDate}"/>
		<form:errors path="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].date" cssClass="error"/>
	</td>
	<td>
		<textarea rows="4" name="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].description" id="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].description" style="width: 95%"><c:out value="${paroleEligibilityNoteItem.description}"/></textarea>
		<form:errors path="paroleEligibilityNoteItems[${paroleEligibilityNoteItemIndex}].description" cssClass="error"/>
	</td>
	<td>
		<c:if test="${not empty paroleEligibilityNoteItems[paroleEligibilityNoteItemIndex].note}">
			<c:set var="updatable" value="${paroleEligibilityNoteItems[paroleEligibilityNoteItemIndex].note}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/lastUpdatedBy.jsp"/>
		</c:if>
	</td>
</tr>