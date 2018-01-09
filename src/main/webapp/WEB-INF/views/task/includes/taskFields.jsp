<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="taskBundle" basename="omis.task.msgs.task"/>
<c:if test="${empty taskFieldsPropertyName}">
	<c:set var="taskFieldsPropertyName" value="taskFields"/>
</c:if>
<form:hidden path="${taskFieldsPropertyName}.allowGroup" />
<form:hidden path="${taskFieldsPropertyName}.allowTask" />
<input type="hidden" name="taskFieldsPropertyName" id="taskFieldsPropertyName" value="${taskFieldsPropertyName}" />
<c:if test="${taskFields.allowGroup}">
	<span class="fieldGroup">
		<form:label path="${taskFieldsPropertyName}.group" class="fieldLabel">
			<fmt:message key="groupLabel" bundle="${taskBundle}" />
		</form:label>
		<form:select path="${taskFieldsPropertyName}.group">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach items="${taskTemplateGroups}" var="grp">
				<option value="${grp.id}" ${grp.id == taskFields.group ? 'selected="selected"' : ''}>
					<c:out value="${grp.name}"/>
				</option>
			</c:forEach>
		</form:select>
		<form:errors path="${taskFieldsPropertyName}.group" cssClass="error"/>
	</span>
</c:if>
<c:if test="${taskFields.allowTask}">
	<span class="fieldGroup">
		<form:label path="${taskFieldsPropertyName}.task" class="fieldLabel">
			<fmt:message key="taskLabel" bundle="${taskBundle}" />
		</form:label>
		<form:select path="${taskFieldsPropertyName}.task">
			<jsp:include page="../../includes/nullOption.jsp"/>
			<c:forEach items="${tasks}" var="tsk">
				<option value="${tsk.id}" ${tsk.id == taskFields.task ? 'selected="selected"' : ''}>
					<c:out value="${tsk.description}"/>
				</option>
			</c:forEach>
		</form:select>
		<form:errors path="${taskFieldsPropertyName}.task" cssClass="error"/>
	</span>
</c:if>
<span class="fieldGroup">
	<form:label path="${taskFieldsPropertyName}.description" class="fieldLabel">
		<fmt:message key="descriptionLabel" bundle="${taskBundle}" />
	</form:label>
	<form:input path="${taskFieldsPropertyName}.description"/>
	<form:errors path="${taskFieldsPropertyName}.description" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${taskFieldsPropertyName}.originationDate" class="fieldLabel">
		<fmt:message key="originationDateLabel" bundle="${taskBundle}" />
	</form:label>
	<form:input path="${taskFieldsPropertyName}.originationDate" class="date"/>
	<form:errors path="${taskFieldsPropertyName}.originationDate" cssClass="error"/>
	
	<form:label path="${taskFieldsPropertyName}.originationTime" class="fieldLabel">
		<fmt:message key="originationTimeLabel" bundle="${taskBundle}" />
	</form:label>
	<form:input path="${taskFieldsPropertyName}.originationTime" class="date"/>
	<form:errors cssClass="error" path="${taskFieldsPropertyName}.originationTime"/>
</span>
<span class="fieldGroup">
	<form:label path="${taskFieldsPropertyName}.completionDate" class="fieldLabel">
		<fmt:message key="completionDateLabel" bundle="${taskBundle}" />
	</form:label>
	<form:input path="${taskFieldsPropertyName}.completionDate" class="time"/>
	<form:errors path="${taskFieldsPropertyName}.completionDate" cssClass="error"/>
</span>

<fieldset>
		<legend>
			<fmt:message key="assignUsersLabel" bundle="${taskBundle}" />
		</legend>
		<span class="fieldGroup">
			<form:errors path="${taskFieldsPropertyName}.taskAssignmentItems" cssClass="error"/>
			<c:set var="taskAssignmentItems" value="${taskFields.taskAssignmentItems}" scope="request"/>
			<jsp:include page="taskAssignmentItemsTable.jsp"/>
		</span>
</fieldset>
