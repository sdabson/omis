<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
	<ul id="psiLinks">
	<c:forEach var="taskItem" items="${taskItems}" varStatus="status">
		<li class="psiLink">
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
					name="${taskItemFieldPropertyName}[${status.index}].itemOperation" class="moduleCheckBox psiTaskCheckBox"
					value="${taskItem.itemOperation}"/>
				</c:otherwise>
			</c:choose>
			<sec:authorize access="hasRole('PRESENTENCE_INVESTIGATION_TASK_VIEW') or hasRole('PRESENTENCE_INVESTIGATION_TASK_EDIT') or hasRole('ADMIN')">
				<a href="${pageContext.request.contextPath}/presentenceInvestigation/task/edit.html?presentenceInvestigationTaskAssociation=${taskItem.taskAssociation.id}" class="newTab tabs2 viewEditLink"></a>
			</sec:authorize>
		</li>
	</c:forEach>
	</ul>
</fmt:bundle>