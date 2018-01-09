<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.task.msgs.task">
	<tr id="taskAssignmentItemRow${taskAssignmentItemIndex}" class="taskAssignmentItemRow">
		<td>
			<a class="removeLink" id="removeTaskAssignmentLink${taskAssignmentItemIndex}" href="${pageContext.request.contextPath}/hearing/removeTaskAssignment.html?taskAssignment=${taskAssignment.id}"><span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			<input type="hidden" id="taskAssignmentId${taskAssignmentItemIndex}" name="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].taskAssignment" value="${taskAssignmentItem.taskAssignment.id}"/>
			<form:errors path="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].taskAssignment" cssClass="error"/>
			<input type="hidden" id="taskAssignmentOperation${taskAssignmentItemIndex}" name="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].taskItemOperation" value="${taskAssignmentItem.taskItemOperation}"/>
			<form:errors path="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].taskItemOperation" cssClass="error"/>
		</td>
		<td>
			<fmt:formatDate var="assignedDate" value="${taskAssignmentItem.assignedDate}" pattern="MM/dd/yyyy"/>
			<input type="text" class="date" name="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].assignedDate" id="taskAssignmentItemAssignedDate${taskAssignmentItemIndex}" value="${assignedDate}"/>
			<form:errors path="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].assignedDate" cssClass="error"/>
		</td>
		<td>
			<input id="taskAssignmentInput${taskAssignmentItemIndex}"/>
				<input type="hidden" id="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].assigneeAccount" name="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].assigneeAccount" value="${taskFields.taskAssignmentItems[taskAssignmentItemIndex].assigneeAccount.id}"/>
				<a id="clearAssignedUser${taskAssignmentItemIndex}" class="clearLink"></a>
			<span id="taskAssignmentDisplay${taskAssignmentItemIndex}">
				<c:if test="${not empty taskFields.taskAssignmentItems[taskAssignmentItemIndex].assigneeAccount}" >
					<c:out value="${taskFields.taskAssignmentItems[taskAssignmentItemIndex].assigneeAccount.user.name.lastName}, ${taskFields.taskAssignmentItems[taskAssignmentItemIndex].assigneeAccount.user.name.firstName}"/>
				</c:if>
			</span>
			<form:errors path="${taskFieldsPropertyName}.taskAssignmentItems[${taskAssignmentItemIndex}].assigneeAccount" cssClass="error"/>
		</td>
	</tr>
</fmt:bundle>