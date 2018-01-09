<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize var="editPresentenceInvestigationTask" access="hasRole('PRESENTENCE_INVESTIGATION_TASK_EDIT') or hasRole('ADMIN') or hasRole('PRESENTENCE_INVESTIGATION_TASK_CREATE')"/>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="taskBundle" basename="omis.task.msgs.task"/>
<fmt:setBundle var="presentenceInvestigationTaskBundle" basename="omis.presentenceinvestigation.msgs.presentenceInvestigationTask"/>
<form:form commandName="presentenceInvestigationTaskForm" class="editForm">
	<jsp:include page="/WEB-INF/views/presentenceInvestigation/home/includes/requestHeader.jsp"/>
	<fieldset>
		<legend><fmt:message key="taskLabel" bundle="${taskBundle}"/></legend>
		<c:set var="taskFields" value="${presentenceInvestigationTaskForm.taskFields}" scope="request"/>
		<jsp:include page="/WEB-INF/views/task/includes/taskFields.jsp"/>
	</fieldset>
	
	<c:if test="${editPresentenceInvestigationTask}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>