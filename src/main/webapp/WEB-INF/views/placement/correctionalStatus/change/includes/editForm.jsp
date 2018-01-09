<%--
 - Form to change correctional status.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<c:choose>
	<c:when test="${not empty fromCorrectionalStatus}">
		<c:set var="fromCorrectionalStatusName" value="${fromCorrectionalStatus.name}"/>
	</c:when>
	<c:otherwise>
		<fmt:message var="fromCorrectionalStatusName" key="startCorrectionalStatusLabel" bundle="${placementBundle}"/>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty toCorrectionalStatus}">
		<c:set var="toCorrectionalStatusName" value="${toCorrectionalStatus.name}"/>
	</c:when>
	<c:otherwise>
		<fmt:message var="toCorrectionalStatusName" key="endCorrectionalStatusLabel" bundle="${placementBundle}"/>
	</c:otherwise>
</c:choose>
<fmt:message var="actionMessage" key="correctionalStatusChangeActionMessage" bundle="${placementBundle}">
	<fmt:param><c:out value="${fromCorrectionalStatusName}"/></fmt:param>
	<fmt:param><c:out value="${toCorrectionalStatusName}"/></fmt:param>
</fmt:message>
<form:form commandName="correctionalStatusChangeForm" class="editForm">
	<fieldset>
		<legend><c:out value="${action.name}"/></legend>
		<p>
			<c:out value="${actionMessage}"/>
		</p>
		<form:hidden path="supervisoryOrganizationRequired"/>
		<form:hidden path="endDateAllowed"/>
		<form:hidden path="locationRequired"/>
		<form:hidden path="endLocationAllowed"/>
		<form:hidden path="programAllowed"/>
		<c:if test="${correctionalStatusChangeForm.supervisoryOrganizationRequired}">
			<span class="fieldGroup">
				<form:label path="supervisoryOrganization" class="fieldLabel"><fmt:message key="supervisoryOrganizationLabel" bundle="${placementBundle}"/></form:label>
				<form:select path="supervisoryOrganization">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options itemValue="id" itemLabel="name" items="${supervisoryOrganizations}"/>
				</form:select>
				<form:errors path="supervisoryOrganization" class="error"/>
			</span>
		</c:if>
		<c:if test="${correctionalStatusChangeForm.locationRequired}">
			<span class="fieldGroup">
				<form:label path="location" class="fieldLabel"><fmt:message key="locationLabel" bundle="${placementBundle}"/></form:label>
				<span>
					<form:select path="location">
						<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
						<form:options itemValue="id" itemLabel="organization.name" items="${locations}"/>
					</form:select>
				</span>
				<form:errors path="location" class="error"/>
			</span>
		</c:if>
		<span class="fieldGroup">
			<form:label path="changeReason" class="fieldLabel"><fmt:message key="changeReasonLabel" bundle="${placementBundle}"/></form:label>
			<form:select path="changeReason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options item="id" itemLabel="name" items="${changeReasons}"/>
			</form:select>
			<form:errors path="changeReason" class="error"/>
		</span>
		<c:if test="${correctionalStatusChangeForm.programAllowed}">
			<span class="fieldGroup">
				<form:label path="program" class="fieldLabel"><fmt:message key="programLabel" bundle="${programBundle}"/></form:label>
				<span>
					<form:select path="program">
						<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
						<form:options item="id" itemLabel="name" items="${programs}"/>
					</form:select>
				</span>
				<form:errors path="program" class="error"/>
			</span>
		</c:if>
		<span class="fieldGroup">
			<form:label path="effectiveDate" class="fieldLabel"><fmt:message key="effectiveDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="effectiveDate" id="effectiveDate" class="date"/>
			<form:errors path="effectiveDate" class="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="effectiveTime" class="fieldLabel"><fmt:message key="effectiveTimeLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="effectiveTime" id="effectiveTime" class="time"/>
			<form:errors path="effectiveTime" class="error"/>
		</span>
		<c:if test="${correctionalStatusChangeForm.endDateAllowed}">
			<span class="fieldGroup">
				<form:label path="endDate" class="fieldLabel"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
				<form:input id="endDate" path="endDate" class="time"/>
				<form:errors path="endDate" class="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="endTime" class="fieldLabel"><fmt:message key="endTimeLabel" bundle="${commonBundle}"/></form:label>
				<form:input id="endTime" path="endTime" class="time"/>
				<form:errors path="endTime" class="error"/>
			</span>
		</c:if>
		<c:if test="${correctionalStatusChangeForm.endLocationAllowed}">
			<span class="fieldGroup">
				<form:label path="endLocation" class="fieldLabel"><fmt:message key="endLocationLabel" bundle="${placementBundle}"/></form:label>
				<form:checkbox path="endLocation"/>
				<form:errors path="endLocation" class="error"/>
			</span>
		</c:if>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form> 