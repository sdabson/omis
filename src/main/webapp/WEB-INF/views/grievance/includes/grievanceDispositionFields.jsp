<%--
 - Fields for grievance disposition.
 -
 - Assumes variables dispositionItemName and dispositionItem
 - are set and in scope.
 - 
 - Grievance dispositions should also be provided as
 - dispositionStatuses.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<input type="hidden" name="${dispositionItemName}.disposition" id="${dispositionItemName}.disposition" value="${dispositionItem.disposition.id}"/>
<input type="hidden" name="${dispositionItemName}.edit" id="${dispositionItemName}.edit" value="${dispositionItem.edit}"/>
<input type="hidden" name="${dispositionItemName}.currentLevel" id="${dispositionItemName}.currentLevel" value="${dispositionItem.currentLevel}"/>
<input type="hidden" name="${dispositionItemName}.required" id="${dispositionItemName}.required" value="${dispositionItem.required}"/>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.status"><fmt:message key="grievanceDispositionStatusLabel" bundle="${grievanceBundle}"/></label>
	<select name="${dispositionItemName}.status" id="${dispositionItemName}.status">
		<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
		<c:forEach var="dispositionStatus" items="${dispositionStatuses}">
			<c:choose>
				<c:when test="${dispositionStatus eq dispositionItem.status}">
					<option value="${dispositionStatus.id}" selected="selected"><c:out value="${dispositionStatus.name}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${dispositionStatus.id}"><c:out value="${dispositionStatus.name}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="${dispositionItemName}.status" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.reason"><fmt:message key="grievanceDispositionReasonLabel" bundle="${grievanceBundle}"/></label>
	<select name="${dispositionItemName}.reason" id="${dispositionItemName}.reason">
		<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
		<c:forEach var="dispositionReason" items="${dispositionReasons}">
			<c:choose>
				<c:when test="${dispositionReason eq dispositionItem.reason}">
					<option value="${dispositionReason.id}" selected="selected"><c:out value="${dispositionReason.name}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${dispositionReason.id}"><c:out value="${dispositionReason.name}"/></option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	<form:errors path="${dispositionItemName}.reason" cssClass="error"/>
</span>
<form:hidden path="${dispositionItemName}.closedDateAllowed"/>
<c:if test="${dispositionItem.closedDateAllowed}">
	<span class="fieldGroup">
		<label class="fieldLabel" for="${dispositionItemName}.closedDate"><fmt:message key="grievanceClosedDateLabel" bundle="${grievanceBundle}"/></label>
		<form:input path="${dispositionItemName}.closedDate" class="date" disabled="${not dispositionItem.status.closed}"/>
		<form:errors path="${dispositionItemName}.closedDate" cssClass="error"/>
	</span>
</c:if>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.receivedDate"><fmt:message key="grievanceDispositionReceivedDateLabel" bundle="${grievanceBundle}"/></label>
	<input class="date" type="text" name="${dispositionItemName}.receivedDate" id="${dispositionItemName}.receivedDate" value="<fmt:formatDate value='${dispositionItem.receivedDate}' pattern='MM/dd/yyyy'/>"/>
	<form:errors path="${dispositionItemName}.receivedDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.responseDueDate"><fmt:message key="grievanceDispositionResponseDueDateLabel" bundle="${grievanceBundle}"/></label>
	<input class="date" type="text" name="${dispositionItemName}.responseDueDate" id="${dispositionItemName}.responseDueDate" value="<fmt:formatDate value='${dispositionItem.responseDueDate}' pattern='MM/dd/yyyy'/>"/>
	<form:errors path="${dispositionItemName}.responseDueDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.responseBy"><fmt:message key="grievanceDispositionResponseByLabel" bundle="${grievanceBundle}"/></label>
	<c:choose>
		<c:when test="${not empty dispositionItem.responseByQuery}"><c:set var="responseByQuery" value="${dispositionItem.responseByQuery}"/></c:when>
		<c:when test="${not empty dispositionItem.responseBy}"><c:set var="responseByQuery"><c:set var="userAccount" value="${dispositionItem.responseBy}" scope="request"/><jsp:include page="/WEB-INF/views/user/includes/userAccount.jsp"/></c:set></c:when>
	</c:choose>
	<input type="text" name="${dispositionItemName}.responseByQuery" id="${dispositionItemName}.responseByQuery" value="<c:out value='${responseByQuery}'/>"/>
	<input type="hidden" name="${dispositionItemName}.responseBy" id="${dispositionItemName}.responseBy" value="<c:out value='${dispositionItem.responseBy.id}'/>"/>
	<form:errors path="${dispositionItemName}.responseBy" cssClass="error"/>
	<a id="${dispositionItemName}.clearResponseByLink" class="clearLink" title="<fmt:message key='clearGrievanceDispositionResponseByLink' bundle='${grievanceBundle}'/>" href="${pageContext.request.contextPath}/grievance/clearDispositionResponseBy.html?grievance=${grievance.id}"></a>
	<a id="${dispositionItemName}.useCurrentUserAccountLink" class="currentUserAccountLink" title="<fmt:message key='useCurrentUserAccountAsDispositionResponseByLink' bundle='${grievanceBundle}'/>" href="${pageContext.request.contextPath}/grievance/userCurrentUserAccountAsDispositionResponseBy.html?grievance=${grievance.id}"></a>
</span>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.responseToOffenderDate"><fmt:message key="grievanceDispositionResponseToOffenderDateLabel" bundle="${grievanceBundle}"/></label>
	<input class="date" type="text" name="${dispositionItemName}.responseToOffenderDate" id="${dispositionItemName}.responseToOffenderDate" value="<fmt:formatDate value='${dispositionItem.responseToOffenderDate}' pattern='MM/dd/yyyy'/>"/>
	<form:errors path="${dispositionItemName}.responseToOffenderDate" cssClass="error"/> 
</span>
<form:hidden path="${dispositionItemName}.allowAppealDate"/>
<c:if test="${dispositionItem.allowAppealDate}">
	<span class="fieldGroup">
		<label class="fieldLabel" for="${dispositionItemName}.appealDate"><fmt:message key="grievanceDispositionAppealDateLabel" bundle="${grievanceBundle}"/></label>
		<input class="date" type="text" name="${dispositionItemName}.appealDate" id="${dispositionItemName}.appealDate" value="<fmt:formatDate value='${dispositionItem.appealDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="${dispositionItemName}.appealDate" cssClass="error"/>
	</span>
</c:if>
<span class="fieldGroup">
	<label class="fieldLabel" for="${dispositionItemName}.notes"><fmt:message key="grievanceDispositionNotesLabel" bundle="${grievanceBundle}"/></label>
	<textarea id="${dispositionItemName}.notes" name="${dispositionItemName}.notes"><c:out value="${dispositionItem.notes}"/></textarea>
	<form:errors path="${dispositionItemName}.notes" cssClass="error"/>
</span>