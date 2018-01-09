<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<form:form commandName="locationChangeForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="changeLocationLabel" bundle="${placementBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="location" class="fieldLabel"><fmt:message key="locationLabel" bundle="${placementBundle}"/></form:label>
			<form:select path="location">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="organization.name" itemValue="id" items="${locations}"/>
			</form:select>
			<form:errors path="location" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="reason" class="fieldLabel"><fmt:message key="locationReasonLabel" bundle="${placementBundle}"/></form:label>
			<form:select path="reason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="name" itemValue="id" items="${reasons}"/>
			</form:select>
			<form:errors path="reason" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="effectiveDate" class="fieldLabel"><fmt:message key="effectiveDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="effectiveDate" id="effectiveDate" class="date"/>
			<form:errors path="effectiveDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="effectiveTime" class="fieldLabel"><fmt:message key="effectiveTimeLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="effectiveTime" id="effectiveTime" class="time"/>
			<form:errors path="effectiveTime" class="error"/>
		</span>
		<form:hidden path="endDateAllowed"/>
		<c:if test="${locationChangeForm.endDateAllowed}">
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
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>