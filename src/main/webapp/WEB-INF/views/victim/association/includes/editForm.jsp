<%--
  - Victim association edit form.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.victim.msgs.victim" var="victimBundle"/>
<fmt:setBundle basename="omis.contact.msgs.contact" var="contactBundle"/>
<form:form commandName="victimAssociationForm" class="editForm">
	<form:hidden path="showPersonFields"/>
	<c:if test="${victimAssociationForm.showPersonFields}">
	<fieldset id="personFieldSet">
		<legend><fmt:message key="victimPersonalDetailsLabel" bundle="${victimBundle}"/></legend>
		<c:set var="personFields" value="${victimAssociationForm.personFields}" scope="request"/>
		<jsp:include page="/WEB-INF/views/person/includes/personFields.jsp"/>
	</fieldset>
	</c:if>
	<form:hidden path="showMailingAddressFields"/>
	<c:if test="${victimAssociationForm.showMailingAddressFields}">
	<c:set var="addressFieldsPropertyName" value="mailingAddressFields" scope="request"/>
	<fieldset id="mailingAddressFieldSet">
		<legend><fmt:message key="victimMailingAddressDetailsLabel" bundle="${victimBundle}"/></legend>
		<form:label path="enterMailingAddressFields"><fmt:message key="enterMailingAddressLabel" bundle="${contactBundle}"/></form:label>
		<form:checkbox id="enterMailingAddressFields" path="enterMailingAddressFields"/>
		<form:errors path="enterMailingAddressFields" cssClass="error"/>
		<c:choose>
			<c:when test="${not victimAssociationForm.enterMailingAddressFields}">
				<c:set var="mailingAddressEntryFieldsClass" value="hidden"/>
			</c:when>
			<c:otherwise>
				<c:set var="mailingAddressEntryFieldsClass" value=""/>
			</c:otherwise>
		</c:choose>
		<div id="mailingAddressEntryFields" class="${mailingAddressEntryFieldsClass}">
			<span class="fieldGroup">
				<form:label path="mailingAddressOperation" class="fieldLabel"><fmt:message key="useExistingMailingAddressLabel" bundle="${victimBundle}"/></form:label>
				<form:radiobutton id="useExistingMailingAddressButton" path="mailingAddressOperation" value="USE_EXISTING"/>
			</span>
			<span class="fieldGroup" id="existingMailingAddressGroup">
				<form:label path="existingMailingAddressQuery" class="fieldLabel"><fmt:message key="existingMailngAddressLabel" bundle="${victimBundle}"/></form:label>
				<form:input path="existingMailingAddressQuery" class="medium" disabled="${victimAssociationForm.mailingAddressOperation.name ne 'USE_EXISTING'}"/>
				<form:hidden path="existingMailingAddress"/>
				<form:errors path="existingMailingAddressQuery"/>
			</span>
			<span class="fieldGroup">
				<form:label path="mailingAddressOperation" class="fieldLabel"><fmt:message key="createNewMailingAddressLabel" bundle="${victimBundle}"/></form:label>
				<form:radiobutton id="createNewMailingAddressButton" path="mailingAddressOperation" value="CREATE_NEW"/>
			</span>
			<form:errors path="mailingAddressOperation"/>
			<c:choose>
				<c:when test="${victimAssociationForm.mailingAddressOperation.name ne 'CREATE_NEW'}">
					<c:set var="mailingAddressFieldsClass" value="hidden"/>
				</c:when>
				<c:otherwise>
					<c:set var="mailingAddressFieldsClass" value=""/>
				</c:otherwise>
			</c:choose>
			<div id="mailingAddressFields" class="${mailingAddressFieldsClass}">
				<c:set var="addressFields" value="${victimAssociationForm.mailingAddressFields}" scope="request"/>
				<jsp:include page="/WEB-INF/views/address/includes/addressFields.jsp"></jsp:include>
			</div>
		</div>
	</fieldset>
	</c:if>
	<form:hidden path="showPoBoxFields"/>
	<c:if test="${victimAssociationForm.showPoBoxFields}">
	<c:set var="poBoxFieldsPropertyName" value="poBoxFields" scope="request"/>
	<fieldset id="poBoxFieldSet">
		<legend><fmt:message key="victimPoBoxDetailsLabel" bundle="${victimBundle}"/></legend>
		<form:label path="enterPoBoxFields" class="fieldLabel"><fmt:message key="enterPoBoxLabel" bundle="${contactBundle}"/></form:label>
		<form:checkbox id="enterPoBoxFields" path="enterPoBoxFields"/>
		<form:errors path="enterPoBoxFields" cssClass="error"/>
		<c:choose>
			<c:when test="${not victimAssociationForm.enterPoBoxFields}">
				<c:set var="poBoxEntryFieldsClass" value="hidden"/>
			</c:when>
			<c:otherwise>
				<c:set var="poBoxEntryFieldsClass" value=""/>
			</c:otherwise>
		</c:choose>
		<div id="poBoxEntryFields" class="${poBoxEntryFieldsClass}">
			<c:set var="poBoxFields" value="${victimAssociationForm.poBoxFields}" scope="request"/>
			<jsp:include page="/WEB-INF/views/contact/includes/poBoxFields.jsp"></jsp:include>
		</div>
	</fieldset>
	</c:if>
	<form:hidden path="showTelephoneNumberItems"/>
	<c:if test="${victimAssociationForm.showTelephoneNumberItems}">
	<fieldset id="telephoneNumberItemsFieldSet">
		<legend><fmt:message key="victimTelephoneNumberItemsLabel" bundle="${victimBundle}"/></legend>
		<form:errors path="telephoneNumberItems" cssClass="error"/>
		<table class="editTable">
			<thead>
				<tr>
					<th class="operations"><a class="actionMenuItem" id="telephoneNumberActionMenuLink" href="${pageContext.request.contextPath}/victim/association/telephoneNumbersActionMenu.html?offender=${offender.id}"></a></th>
					<th><fmt:message key="contactTelephoneNumberValueLabel" bundle="${contactBundle}"/></th>
					<th><fmt:message key="contactTelephoneNumberExtensionLabel" bundle="${contactBundle}"/></th>					
					<th><fmt:message key="contactTelephoneNumberCategoryLabel" bundle="${contactBundle}"/></th>
					<th><fmt:message key="contactTelephoneNumberPrimaryLabel" bundle="${contactBundle}"/></th>
					<th><fmt:message key="contactTelephoneNumberActiveLabel" bundle="${contactBundle}"/></th>
				</tr>
			</thead>
			<tbody id="telephoneNumbersBody">
				<c:forEach var="telephoneNumberItem" items="${victimAssociationForm.telephoneNumberItems}" varStatus="status">
				<c:if test="${not empty telephoneNumberItem.operation}">
					<c:set var="telephoneNumberItemIndex" value="${status.index}" scope="request"/>
					<c:set var="telephoneNumberItem" value="${telephoneNumberItem}" scope="request"/>
					<c:set var="telephoneNumberFields" value="${telephoneNumberItem.fields}" scope="request"/>
					<jsp:include page="/WEB-INF/views/victim/association/includes/telephoneNumberEditTableRow.jsp"/>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	</c:if>
	<form:hidden path="showOnlineAccountItems"/>
	<c:if test="${victimAssociationForm.showOnlineAccountItems}">
	<fieldset id="onlineAccontItemsFieldSet">
		<legend><fmt:message key="victimOnlineAccountItemsLabel" bundle="${victimBundle}"/></legend>
		<form:errors path="onlineAccountItems" cssClass="error"/>
		<table class="editTable">
			<thead>
				<tr>
					<th class="operations"><a class="actionMenuItem" id="onlineAccountActionMenuLink" href="${pageContext.request.contextPath}/victim/association/onlineAccountsActionMenu.html?offender=${offender.id}"></a></th>		
					<th><fmt:message key="contactOnlineAccountNameLabel" bundle="${contactBundle}"/></th>
					<th><fmt:message key="contactOnlineAccountHostLabel" bundle="${contactBundle}"/></th>
					<th><fmt:message key="contactOnlineAccountPrimaryLabel" bundle="${contactBundle}"/></th>
					<th><fmt:message key="contactOnlineAccountActiveLabel" bundle="${contactBundle}"/></th>
				</tr>
			</thead>
			<tbody id="onlineAccountsBody">
				<c:forEach var="onlineAccountItem" items="${victimAssociationForm.onlineAccountItems}" varStatus="status">
				<c:if test="${not empty onlineAccountItem.operation}">
					<c:set var="onlineAccountItemIndex" value="${status.index}" scope="request"/>
					<c:set var="onlineAccountItem" value="${onlineAccountItem}" scope="request"/>
					<c:set var="onlineAccountFields" value="${onlineAccountItem.fields}" scope="request"/>
					<jsp:include page="/WEB-INF/views/victim/association/includes/onlineAccountEditTableRow.jsp"/>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	</c:if>
	<fieldset>
		<legend><fmt:message key="victimAssociationLabel" bundle="${victimBundle}"/></legend>
		<jsp:include page="/WEB-INF/views/victim/includes/victimAssociationFields.jsp"/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="victimNotesLabel" bundle="${victimBundle}"/></legend>
		<c:set var="victimNoteItems" value="${victimAssociationForm.noteItems}" scope="request"/>
		<jsp:include page="editTable.jsp"/>
	</fieldset>
	<c:if test="${not empty victimAssociation}">
		<c:set var="updatable" value="${victimAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>