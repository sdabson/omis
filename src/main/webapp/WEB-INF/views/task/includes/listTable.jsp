<%--
  - List table of tasks.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.task.msgs.task" var="taskBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="descriptionLabel" bundle="${taskBundle}"/></th>
			<th><fmt:message key="sourceAccountLabel" bundle="${taskBundle}"/></th>
			<th><fmt:message key="originationDateLabel" bundle="${taskBundle}"/></th>
			<th><fmt:message key="completionDateLabel" bundle="${taskBundle}"/></th>
		</tr>
	</thead>
	<tbody id="tasks">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>