<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form commandName="cancelExternalReferralForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="cancelExternalReferralLabel" bundle="${healthBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="cancellationStatusReason" class="fieldLabel">
				<fmt:message key="externalReferralCancelStatusReasonLabel" bundle="${healthBundle}"/></form:label>
			<form:select path="cancellationStatusReason">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemLabel="name" itemValue="id" items="${cancellationStatusReasons}"/>
			</form:select>
			<form:errors path="cancellationStatusReason" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>