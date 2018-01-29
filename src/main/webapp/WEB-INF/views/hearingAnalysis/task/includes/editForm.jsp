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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize var="editHearingAnalysisTask" access="hasRole('HEARING_ANALYSIS_TASK_EDIT') or hasRole('ADMIN') or hasRole('HEARING_ANALYSIS_TASK_CREATE')"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="taskBundle" basename="omis.task.msgs.task"/>
<form:form commandName="hearingAnalysisTaskForm" class="editForm">
	<jsp:include page="/WEB-INF/views/hearingAnalysis/home/includes/summaryHeader.jsp"/>
	<fieldset>
		<legend><fmt:message key="taskLabel" bundle="${taskBundle}"/></legend>
		<c:set var="taskFields" value="${hearingAnalysisTaskForm.taskFields}" scope="request"/>
		<jsp:include page="/WEB-INF/views/task/includes/taskFields.jsp"/>
	</fieldset>
	
	<c:if test="${editHearingAnalysisTask}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>