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
 - Table body of listed chronological notes 
 - Author: Yidong Li
 - Version: 0.1.1 (Feb 1, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.chronologicalnote.msgs.chronologicalNote">
	<c:forEach var="chronologicalNoteSummary" items="${chronologicalNoteSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem chronologicalNoteListRowActionMenuItem" id="chronologicalNoteActionMenuLink${status.index}" href="${pageContext.request.contextPath}/chronologicalNoteReport/chronologicalNoteListRowActionMenu.html?note=${chronologicalNoteSummary.id}"></a>	
		</td>
		<td>
			<c:out value="${trackedDocumentSummaryItem.docketValue}"/> - <c:out value="${trackedDocumentSummaryItem.courtName}"/>
		</td>
		<td>
			<c:out value="${trackedDocumentSummaryItem.count}"/>
		</td>
	</tr>
	</c:forEach>
</fmt:bundle>