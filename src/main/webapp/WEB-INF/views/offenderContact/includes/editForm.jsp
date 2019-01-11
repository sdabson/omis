<%--
  - Offender contact edit form.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.offendercontact.msgs.offenderContact" var="offenderContactBundle"/>
<fmt:setBundle basename="omis.contact.msgs.contact" var="contactBundle"/>
<form:form commandName="offenderContactForm" class="editForm">
<form:hidden path="showMailingAddressFields"/>
	<c:if test="${offenderContactForm.showMailingAddressFields}">
	<c:set var="addressFieldsPropertyName" value="mailingAddressFields" scope="request"/>
	<fieldset id="mailingAddressFieldSet">	
		<legend><fmt:message key="offenderMailingAddressDetailsLabel" bundle="${offenderContactBundle}"/></legend>		
		<form:label path="enterMailingAddressFields"><fmt:message key="enterMailingAddressLabel" bundle="${contactBundle}"/></form:label>
		<form:checkbox id="enterMailingAddressFields" path="enterMailingAddressFields"/>
		<form:errors path="enterMailingAddressFields" cssClass="error"/>
		<c:choose>
			<c:when test="${not offenderContactForm.enterMailingAddressFields}">
				<c:set var="mailingAddressEntryFieldsClass" value="hidden"/>
			</c:when>
			<c:otherwise>
				<c:set var="mailingAddressEntryFieldsClass" value=""/>
			</c:otherwise>
		</c:choose>
		<div id="mailingAddressEntryFields" class="${mailingAddressEntryFieldsClass}">
			<span class="fieldGroup">
				<form:label path="mailingAddressOperation" class="fieldLabel"><fmt:message key="useExistingMailingAddressLabel" bundle="${offenderContactBundle}"/></form:label>
				<form:radiobutton id="useExistingMailingAddressButton" path="mailingAddressOperation" value="USE_EXISTING"/>
			</span>
			<form:errors path="mailingAddressOperation"/>
			<span class="fieldGroup" id="existingMailingAddressGroup">
				<form:label path="existingMailingAddressQuery" class="fieldLabel"><fmt:message key="existingMailngAddressLabel" bundle="${offenderContactBundle}"/></form:label>
				<form:input path="existingMailingAddressQuery" class="medium" disabled = "${offenderContactForm.mailingAddressOperation.name ne 'USE_EXISTING'}"/>				
				<form:hidden path="existingMailingAddress"/>
				<form:errors path="existingMailingAddressQuery" cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="mailingAddressOperation" class="fieldLabel"><fmt:message key="createNewMailingAddressLabel" bundle="${offenderContactBundle}"/></form:label>
				<form:radiobutton id="createNewMailingAddressButton" path="mailingAddressOperation" value="CREATE_NEW"/>
			</span>
			<form:errors path="mailingAddressOperation"/>
			<c:choose>
				<c:when test="${offenderContactForm.mailingAddressOperation.name ne 'CREATE_NEW'}">
					<c:set var="mailingAddressFieldsClass" value="hidden"/>
				</c:when>
				<c:otherwise>
					<c:set var="mailingAddressFieldsClass" value=""/>
				</c:otherwise>
			</c:choose>
			<div id="mailingAddressFields" class="${mailingAddressFieldsClass}">
				<c:set var="addressFields" value="${offenderContactForm.mailingAddressFields}" scope="request"/>
				<jsp:include page="/WEB-INF/views/address/includes/addressFields.jsp"></jsp:include>
			</div>
			<c:if test="${allowResidenceAtMailingAddress}">
				<span class="fieldGroup">
					<form:label path="residentAtMailingAddress" class="fieldLabel"><fmt:message key="residentAtMailingAddressLabel" bundle="${offenderContactBundle}"/></form:label>
					<form:checkbox id="residentAtMailingAddress" path="residentAtMailingAddress"/>
					<form:errors path="residentAtMailingAddress" cssClass="error"/>
				</span>
				<span class="fieldGroup">
					<form:label path="residentAtMailingAddressEffectiveDate" class="fieldLabel"><fmt:message key="residentAtMailingAddressEffectiveDateLabel" bundle="${offenderContactBundle}"/></form:label>
					<form:input id="residentAtMailingAddressEffectiveDate" path="residentAtMailingAddressEffectiveDate" class="date" disabled="${not offenderContactForm.residentAtMailingAddress}"/>
					<form:errors path="residentAtMailingAddressEffectiveDate" cssClass="error"/>
				</span>
			</c:if>
		</div>
	</fieldset>
	</c:if>
	<form:hidden path="showPoBoxFields"/>
	<c:if test="${offenderContactForm.showPoBoxFields}">
	<c:set var="poBoxFieldsPropertyName" value="poBoxFields" scope="request"/>
	<fieldset id="poBoxFieldSet">
		<legend><fmt:message key="offenderPoBoxDetailsLabel" bundle="${offenderContactBundle}"/></legend>
		<form:label path="enterPoBoxFields" class="fieldLabel"><fmt:message key="enterPoBoxLabel" bundle="${contactBundle}"/></form:label>
		<form:checkbox id="enterPoBoxFields" path="enterPoBoxFields"/>
		<form:errors path="enterPoBoxFields" cssClass="error"/>
		<c:choose>
			<c:when test="${not offenderContactForm.enterPoBoxFields}">
				<c:set var="poBoxEntryFieldsClass" value="hidden"/>
			</c:when>
			<c:otherwise>
				<c:set var="poBoxEntryFieldsClass" value=""/>
			</c:otherwise>
		</c:choose>
		<div id="poBoxEntryFields" class="${poBoxEntryFieldsClass}">
			<c:set var="poBoxFields" value="${offenderContactForm.poBoxFields}" scope="request"/>
			<jsp:include page="/WEB-INF/views/contact/includes/poBoxFields.jsp"></jsp:include>
		</div>
	</fieldset>
	</c:if>
	<form:hidden path="showTelephoneNumberItems"/>
	<c:if test="${offenderContactForm.showTelephoneNumberItems}">
	<fieldset id="telephoneNumberItemsFieldSet">
		<legend><fmt:message key="offenderTelephoneNumberItemsLabel" bundle="${offenderContactBundle}"/></legend>
		<form:errors path="telephoneNumberItems" cssClass="error"/>
		<c:set var="telephoneNumberItems" value="${offenderContactForm.telephoneNumberItems}" scope="request"/>
		<jsp:include page="../../offenderContact/includes/telephoneNumberEditTable.jsp"/>
	</fieldset>
	</c:if>
	<form:hidden path="showOnlineAccountItems"/>
	<c:if test="${offenderContactForm.showOnlineAccountItems}">
	<fieldset id="onlineAccontItemsFieldSet">
		<legend><fmt:message key="offenderOnlineAccountItemsLabel" bundle="${offenderContactBundle}"/></legend>
		<form:errors path="onlineAccountItems" cssClass="error"/>
		<c:set var="onlineAccountItems" value="${offenderContactForm.onlineAccountItems}" scope="request"/>
		<jsp:include page="../../offenderContact/includes/onlineAccountEditTable.jsp"/>
	</fieldset>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>