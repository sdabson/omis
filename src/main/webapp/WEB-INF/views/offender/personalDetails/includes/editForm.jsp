<%--
 - Form to change offender personal details.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.person.msgs.name" var="nameBundle"/>
<fmt:setBundle basename="omis.person.msgs.identity" var="identityBundle"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form commandName="offenderPersonalDetailsForm" class="editForm" >
	<fieldset>
		<legend><fmt:message key="nameLabel" bundle="${nameBundle}"/></legend>
		<jsp:include page="/WEB-INF/views/person/name/includes/nameFields.jsp"/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="identityLabel" bundle="${identityBundle}"/></legend>
		<sec:authorize access="hasRole('OFFENDER_SSN_EDIT') or hasRole('ADMIN')" var="canEditSsn"/>
		<c:set var="readOnlySsn" value="${not canEditSsn}" scope="request"/>
		<form:hidden path="validateSocialSecurityNumber"/>
		<c:set var="deceased" value="${offenderPersonalDetailsForm.deceased}" scope="request"/>
		<c:set var="showDeathFields" value="${true}" scope="request"/>
		<c:set var="createNewBirthPlace" value="${offenderPersonalDetailsForm.createNewBirthPlace}" scope="request"/>
		<jsp:include page="/WEB-INF/views/person/identity/includes/identityFields.jsp"/>
	</fieldset>
	<c:set var="updatable" value="${offender}" scope="request"/>
	<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	<p class="buttons">
		<button type="submit"><fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
	</p>
</form:form>