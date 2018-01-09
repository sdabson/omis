<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.task.msgs.task">
	<ul>
		<sec:authorize access="hasRole('TASK_CREATE') or hasRole('TASK_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createTaskAssignmentItemLink" class="createLink" 
					href="${pageContext.request.contextPath}/task/createTaskAssignmentItem.html?taskAssignmentItemIndex=${taskAssignmentItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addTaskAssignmentLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>