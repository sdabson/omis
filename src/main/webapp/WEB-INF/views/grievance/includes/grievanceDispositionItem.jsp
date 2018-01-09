<%--
 - Grievance disposition item.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<c:if test="${not empty dispositionItem.status}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionStatusLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><c:out value="${dispositionItem.status.name}"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.reason}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionReasonLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><c:out value="${dispositionItem.reason.name}"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.closedDate}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceClosedDateLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><fmt:formatDate value="${dispositionItem.closedDate}" pattern="MM/dd/yyyy"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.receivedDate}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionReceivedDateLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><fmt:formatDate value="${dispositionItem.receivedDate}" pattern="MM/dd/yyyy"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.responseDueDate}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionResponseDueDateLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><fmt:formatDate value="${dispositionItem.responseDueDate}" pattern="MM/dd/yyyy"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.responseBy}">
	<c:set var="userAccount" value="${dispositionItem.responseBy}" scope="request"/>
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionResponseByLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><jsp:include page="/WEB-INF/views/user/includes/userAccount.jsp"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.responseToOffenderDate}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionResponseToOffenderDateLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><fmt:formatDate value="${dispositionItem.responseToOffenderDate}" pattern="MM/dd/yyyy"/></span>
	</p>
</c:if>
<c:if test="${dispositionItem.allowAppealDate and not empty dispositionItem.appealDate}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionAppealDateLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><fmt:formatDate value="${dispositionItem.appealDate}" pattern="MM/dd/yyyy"/></span>
	</p>
</c:if>
<c:if test="${not empty dispositionItem.notes}">
	<p class="information">
		<span class="fieldLabel"><fmt:message key="grievanceDispositionNotesLabel" bundle="${grievanceBundle}"/></span>
		<span class="fieldValue"><c:out value="${dispositionItem.notes}"/></span>
	</p>
</c:if>