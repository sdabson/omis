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
 - Version: 0.1.0 (Jan 17, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:bundle basename="omis.hearinganalysis.msgs.hearingAnalysisHome">
	<ul id="taskLinks">
	<c:forEach var="taskItem" items="${taskItems}" varStatus="status">
		<li class="taskLink">
			<form:hidden path="${taskItemFieldPropertyName}[${status.index}].taskAssociation" />
			<a class="newTab tabs2 button" href="${pageContext.request.contextPath}/task/invokeAndRedirect.html?task=${taskItem.taskAssociation.task.id}">
			<fmt:message key="${taskItem.taskAssociation.task.controllerName}Link"/>
			</a>
			<c:choose>
				<c:when test="${taskItem.itemOperation eq 'COMPLETE'}">
					<fmt:formatDate var="completionDate" value="${taskItem.taskAssociation.task.completionDate}" pattern="MM/dd/yyyy"/>
					<fmt:message var="completedBy" key="completedByTitle">
						<fmt:param value="${completionDate}" />
					</fmt:message>
					<input title="${completedBy}" disabled="disabled" type="checkbox" class="moduleCheckBox" checked="checked"/>
					<form:hidden path="${taskItemFieldPropertyName}[${status.index}].itemOperation" />
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="${taskItemFieldPropertyName}[${status.index}].itemOperation"
					name="${taskItemFieldPropertyName}[${status.index}].itemOperation" class="moduleCheckBox taskCheckBox"
					value="${taskItem.itemOperation}"/>
				</c:otherwise>
			</c:choose>
			<sec:authorize access="hasRole('HEARING_ANALYSIS_TASK_VIEW') or hasRole('HEARING_ANALYSIS_TASK_EDIT') or hasRole('ADMIN')">
				<a href="${pageContext.request.contextPath}/hearingAnalysis/task/edit.html?hearingAnalysisTaskAssociation=${taskItem.taskAssociation.id}" class="newTab tabs2 viewEditLink"></a>
			</sec:authorize>
		</li>
	</c:forEach>
	</ul>
</fmt:bundle>