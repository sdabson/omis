<%--
 - Edit screen for locations.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.address.msgs.address" var="addressBundle"/>
<fmt:bundle basename="omis.location.msgs.location">
<form:form commandName="locationForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="locationLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="organizationName" class="fieldLabel">
				<fmt:message key="organizationNameLabel"/></form:label>
			<form:input path="organizationName" class="medium"/>
			<form:errors path="organizationName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="addressLabel" bundle="${addressBundle}"/></legend>
		<c:if test="${not empty location}">
			<span class="fieldGroup">
				<form:radiobutton class="fieldOption" path="addressOperation" id="useCurrentAddress" value="USE_CURRENT"/>
				<label for="useCurrentAddress"><fmt:message key="useCurrentAddressLabel"/>
					<c:out value="${location.address.value}"/>
					<c:out value="${location.address.zipCode.city.name}"/>,
					<c:out value="${location.address.zipCode.city.state.abbreviation}"/>
					<c:out value="${location.address.zipCode.value}"/>
					<c:if test="${not empty location.address.zipCode.extension}">-<c:out value="${location.address.zipCode.extension}"/></c:if>
				</label>
			</span>
		</c:if>
		<span class="fieldGroup">
			<form:radiobutton class="fieldOption" path="addressOperation" id="useExistingAddress" value="USE_EXISTING"/>
			<label for="useExistingAddress"><fmt:message key="useExistingAddressLabel"/></label>
		</span>
		<span class="fieldGroup">
			<form:label path="addressQuery" class="fieldLabel"><fmt:message key="addressSearchLabel"/></form:label>
			<c:choose>
				<c:when test="${'USE_EXISTING' eq locationForm.addressOperation.name}"><form:input path="addressQuery" class="medium"/></c:when>
				<c:otherwise><form:input path="addressQuery" class="medium" disabled="true"/></c:otherwise>
			</c:choose>
			<form:errors path="addressQuery" cssClass="error"/>
			<form:hidden path="address"/>
		</span>
		<span class="fieldGroup">
			<form:radiobutton class="fieldOption" path="addressOperation" id="createNewAddress" value="CREATE_NEW"/>
			<label for="createNewAddress"><fmt:message key="createNewAddressLabel"/></label>
		</span>
		<c:choose>
			<c:when test="${'CREATE_NEW' eq locationForm.addressOperation.name}"><c:set var="addressFieldsClass" value=""/></c:when>
			<c:otherwise><c:set var="addressFieldsClass" value="hidden"/></c:otherwise>
		</c:choose>
		<div id="addressFields" class="${addressFieldsClass}">
			<jsp:include page="/WEB-INF/views/address/includes/addressFields.jsp"/>
		</div>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel'/>"/>
	</p>
</form:form>
</fmt:bundle>