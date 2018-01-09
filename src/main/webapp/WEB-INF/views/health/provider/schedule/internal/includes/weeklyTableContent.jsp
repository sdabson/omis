<%--
 - Displays content of weekly schedule for provider table.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
	<thead>
		<tr>
			<th class="inputColumn">
				<input id="scheduleDateRange" type="hidden" class="date" value="<fmt:formatDate value="${scheduleDate}" pattern="MM/dd/yyyy"/>" />
			</th>
			<th><fmt:message key="numberOfScheduledAppointmentsHeader" bundle="${healthBundle}"/></th>
			<th><fmt:message key="startTimeHeader" bundle="${healthBundle}"/></th>
			<th><fmt:message key="endTimeHeader" bundle="${healthBundle}"/></th>
		</tr>
	</thead>
	<tbody id="internalWeeklyProviderScheduleBody">
		<jsp:include page="weeklyTableBodyContent.jsp"/>
	</tbody>