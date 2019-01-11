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
 - Task completion form.
 - 
 - Author: Josh Divine
 - Version: 0.1.0 (Sep 18, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.task.msgs.task">
<h1 class="taskTitle">
	<c:out value="${task.description}"/>
</h1>
<span id="toggleTaskFormButton" class="taskMenuButtonInactive"></span>
<div id="completionForm" class="accentDark">
	<form:form commandName="taskCompletionForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<label class="fieldLabel"><fmt:message key="originationDateLabel"/></label>
			<fmt:formatDate value="${task.originationDate}" pattern="MM/dd/yyyy"/>
		</span>
		<span class="fieldGroup">
			<form:label path="completed" class="fieldLabel">
				<fmt:message key="completedLabel"/></form:label>
			<input type="checkbox" name="completed" id="completed" ${taskCompletionForm.completed ? 'checked="checked"' : ''} />
			<form:errors path="completed" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
	</form:form>
</div>
<iframe src="${pageContext.request.contextPath}/${taskUrl}" id="taskFrame"></iframe>
</fmt:bundle>