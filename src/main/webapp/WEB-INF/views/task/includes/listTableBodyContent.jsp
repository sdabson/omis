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
  - List table body content of tasks.
  -
  - Author: Stephen Abson
  - Author: Josh Divine
  - Version: 0.1.1 (Sep 19, 2018)
  - Since: OMIS 3.0
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach var="taskSummary" items="${taskSummaries}">
	<c:choose>
		<c:when test="${!taskSummary.invoked}">
			<c:set var="taskClass" value="active"/>
		</c:when>
		<c:otherwise>
			<c:set var="taskClass" value=""/>
		</c:otherwise>
	</c:choose>
	<tr class="${taskClass}">
		<td><a href="${pageContext.request.contextPath}/task/tasksActionMenu.html?task=${taskSummary.id}" class="actionMenuItem" id="taskActionMenu${task.id}"></a></td>
		<td><c:out value="${taskSummary.description}"/></td>
		<td><c:out value="${taskSummary.sourceUserLastName}"/>, <c:out value="${taskSummary.sourceUserFirstName}"/> <c:if test="${not empty taskSummary.sourceUserMiddleName}"><c:out value="${fn:substring(taskSummary.sourceUserMiddleName, 0, 1)}"/>. </c:if><c:if test="${not empty taskSummary.sourceUserSuffix}"><c:out value="${taskSummary.sourceUserSuffix}"/> </c:if><c:out value="${taskSummary.sourceUsername}"/></td>
		<td><fmt:formatDate value="${taskSummary.originationDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${taskSummary.completionDate}" pattern="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>