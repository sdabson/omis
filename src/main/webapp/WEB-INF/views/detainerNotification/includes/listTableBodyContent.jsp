<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
<c:forEach var="summary" items="${summaries}" varStatus="status">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" id="actionMenuLink${status.index}" href="${pageContext.request.contextPath}/detainerNotification/detainerNotificationsRowActionMenu.html?detainer=${summary.detainerId}&offender=${offender.id}"></a></td>
	<td><fmt:formatDate value="${summary.warrantReceivedDate}" pattern="MM/dd/yyyy"/></td>
	<td><fmt:formatDate value="${summary.notificationDesignationDate}" pattern="MM/dd/yyyy" /></td>
	<td><c:out value="${summary.detainerTypeName}"/></td>
	<td><c:out value="${summary.requestingAgencyLocationName}"/></td>
	<td><c:out value="${summary.courtCaseNumber}" /></td>
	<td><c:out value="${summary.warrantNumber}" /></td>
	<td><fmt:formatDate value="${summary.cancellationDate}" pattern="MM/dd/yyyy"/></td>
</tr>
</c:forEach>
</fmt:bundle>