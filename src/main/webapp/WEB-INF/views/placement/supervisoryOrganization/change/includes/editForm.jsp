<%--
 - Form to change supervisory organization.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<form:form class="editForm" commandName="supervisoryOrganizationChangeForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="supervisoryOrganization" class="fieldLabel"><fmt:message key="supervisoryOrganizationLabel" bundle="${placementBundle}"/></form:label>
			<form:select path="supervisoryOrganization">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${supervisoryOrganizations}"/>
			</form:select>
			<form:errors path="supervisoryOrganization" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="changeReason" class="fieldLabel"><fmt:message key="changeReasonLabel" bundle="${placementBundle}"/></form:label>
			<form:select path="changeReason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${changeReasons}"/>
			</form:select>
			<form:errors path="changeReason" cssClass="error"/>
		</span>
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
		<p class="information">
			<fmt:message key="locationsNeedToBeEndedMessage" bundle="${placementBundle}"/>
		</p>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>