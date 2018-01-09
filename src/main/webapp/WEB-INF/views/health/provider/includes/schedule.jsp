<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.facility.msgs.facility" var="facilityBundle"/>
<table class="providerSchedule">
	<thead>
		<tr>
			<td><fmt:message key="dateLabel" bundle="${commonBundle}"/></td>
			<td><fmt:message key="startTimeLabel" bundle="${commonBundle}"/></td>
			<td><fmt:message key="endTimeLabel" bundle="${commonBundle}"/></td>
			<td><fmt:message key="providerLabel" bundle="${healthBundle}"/></td>
			<td><fmt:message key="providerAssignmentCategoryLabel" bundle="${healthBundle}"/></td>
			<td><fmt:message key="facilityLabel" bundle="${facilityBundle}"/></td>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="dailyProviderScheduleSummary" items="${dailyProviderScheduleSummaries}">
		<tr>
			<td><fmt:formatDate value="${dailyProviderScheduleSummary.date}" pattern="MM/dd/yyyy"/></td>
			<td><fmt:formatDate value="${dailyProviderScheduleSummary.startTime}" pattern="h:mm a"/></td>
			<td><fmt:formatDate value="${dailyProviderScheduleSummary.endTime}" pattern="h:mm a"/></td>
			<td>
				<c:out value="${dailyProviderScheduleSummary.providerLastName}"/>,
				<c:out value="${dailyProviderScheduleSummary.providerFirstName}"/>
			</td>
			<td><fmt:message key="providerAssignmentCategoryLabel.${dailyProviderScheduleSummary.assignmentCategory.name}" bundle="${healthBundle}"/></td>
			<td><c:out value="${dailyProviderScheduleSummary.facilityName}"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>