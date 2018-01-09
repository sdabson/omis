<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form commandName="scheduleAuthorizedExternalReferralForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="externalReferralLabel" bundle="${healthBundle}"/></legend>
		<form:hidden path="statusReasonRequired"/>
		<c:if test="${scheduleAuthorizedExternalReferralForm.statusReasonRequired}">
		<span class="fieldGroup">
			<form:label path="statusReason" class="fieldLabel">
				<fmt:message key="externalReferralStatusReasonLabel" bundle="${healthBundle}"/></form:label>
			<form:select path="statusReason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${rescheduleStatusReasons}"/>
			</form:select>
			<form:errors path="statusReason" cssClass="error"/>
		</span>
		</c:if>
		<span class="fieldGroup">
			<form:label path="date" class="fieldLabel">
				<fmt:message key="appointmentDateLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="date" class="date"/>
			<form:errors path="date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="time" class="fieldLabel">
				<fmt:message key="appointmentTimeLabel" bundle="${healthBundle}"/></form:label>
			<form:input path="time" class="time"/>
			<form:errors path="time" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="providerAssignment" class="fieldLabel"><fmt:message key="externalProviderAssignmentLabel" bundle="${healthBundle}"/></form:label>
				<form:select path="providerAssignment">
						<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
						<c:forEach var="providerAssignment" items="${providerAssignments}">
							<c:choose>
								<c:when test="${providerAssignment eq scheduleAuthorizedExternalReferralForm.providerAssignment}">
									<option value="${providerAssignment.id}" selected="selected">
										<c:out value="${providerAssignment.provider.name.lastName}"/>,
										<c:out value="${providerAssignment.provider.name.firstName}"/>
										<c:out value="${providerAssignment.title.abbreviation}"/>
									</option>
								</c:when>
								<c:otherwise>
									<option value="${providerAssignment.id}">
										<c:out value="${providerAssignment.provider.name.lastName}"/>,
										<c:out value="${providerAssignment.provider.name.firstName}"/>
										<c:out value="${providerAssignment.title.abbreviation}"/>
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</form:select>
			<form:errors path="providerAssignment" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="schedulingNotes" class="fieldLabel">
				<fmt:message key="schedulingNotesLabel" bundle="${healthBundle}"/></form:label>
			<form:textarea path="schedulingNotes"/>
			<form:errors path="schedulingNotes" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>