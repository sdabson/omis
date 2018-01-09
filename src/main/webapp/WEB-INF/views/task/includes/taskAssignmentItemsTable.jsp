<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.task.msgs.task">
<table id="taskAssignmentItemTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="taskAssignmentItemsActionMenuLink" href="${pageContext.request.contextPath}/task/taskAssignmentItemsActionMenu.html?taskAssignmentItemIndex=${taskAssignmentItemIndex}"></a></th>
			<th><fmt:message key="dateLabel"/></th>
			<th><fmt:message key="userLabel"/></th>
		</tr>
	</thead>
	<tbody id="taskAssignmentItemTableBody">
		<jsp:include page="taskAssignmentItemTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>