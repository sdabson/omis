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
 - Version: 0.1.0 (Dec 20, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
<c:forEach var="boardMeetingSite" items="${boardMeetingSites}">
	<c:choose>
		<c:when test="${meetingSite eq boardMeetingSite}">
			<option value="${boardMeetingSite.id}" selected="selected"><c:out value="${boardMeetingSite.location.organization.name} - "/><fmt:formatDate value="${boardMeetingSite.date}" pattern="MM/dd/yyyy"/></option>
		</c:when>
		<c:otherwise>
			<option value="${boardMeetingSite.id}"><c:out value="${boardMeetingSite.location.organization.name} - "/><fmt:formatDate value="${boardMeetingSite.date}" pattern="MM/dd/yyyy"/></option>
		</c:otherwise>
	</c:choose>
</c:forEach>