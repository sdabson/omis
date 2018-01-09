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
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.paroleeligibility.msgs.paroleEligibility">
	<c:forEach var="paroleEligibilityNoteItem" items="${paroleEligibilityNoteItems}" varStatus="status">
		<c:set var="paroleEligibilityNoteItem" value="${paroleEligibilityNoteItem}" scope="request"/>
		<c:set var="paroleEligibilityNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty paroleEligibilityNoteItem.operation}">
			<jsp:include page="paroleEligibilityNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>