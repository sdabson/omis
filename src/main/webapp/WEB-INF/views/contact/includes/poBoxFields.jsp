<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.contact.msgs.contact">
	<c:if test="${empty poBoxFieldsPropertyName}">
		<c:set var="poBoxFieldsPropertyName" value="poBoxFields" scope="request"/>
	</c:if>
	<div id="${poBoxFieldsPropertyName}Snippet">
		<span class="fieldGroup">
			<label class="fieldLabel" for="${poBoxFieldsPropertyName}.poBoxValue"><fmt:message key="poBoxFieldsPoBoxValueLabel"/></label>
			<input type="text" name="${poBoxFieldsPropertyName}.poBoxValue" id="${poBoxFieldsPropertyName}PoBoxValue" value="${poBoxFields.poBoxValue}"/>
			<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.poBoxValue"/>
		</span>
		<span class="fieldGroup">
			<label class="fieldLabel" for="${poBoxFieldsPropertyName}.country"><fmt:message key="poBoxFieldsCountryLabel"/></label>
			<select name="${poBoxFieldsPropertyName}.country" id="${poBoxFieldsPropertyName}Country">
				<jsp:include page="poBoxFieldsCountryOptions.jsp"/>
			</select>
			<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.country"/>
		</span>
		<span class="fieldGroup">
			<label class="fieldLabel" for="${poBoxFieldsPropertyName}.state"><fmt:message key="poBoxFieldsStateLabel"/></label>
			<select name="${poBoxFieldsPropertyName}.state" id="${poBoxFieldsPropertyName}State">
				<jsp:include page="poBoxFieldsStateOptions.jsp"/>
			</select>
			<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.state"/>
		</span>
		<c:choose>
			<c:when test="${poBoxFields.newCity}">
				<c:set value="" var="newCityDisplayClass"/>
				<c:set value="hidden" var="existingCityDisplayClass"/>
			</c:when>
			<c:otherwise>
				<c:set value="hidden" var="newCityDisplayClass"/>
				<c:set value="" var="existingCityDisplayClass"/>
			</c:otherwise>
		</c:choose>
		<span class="fieldGroup">
			<span id="${poBoxFieldsPropertyName}NewCitySelectionFieldGroup">
				<c:choose>
					<c:when test="${poBoxFields.newCity}">
						<label class="fieldLabel" for="${poBoxFieldsPropertyName}.city"><fmt:message key="poBoxFieldsCityLabel"/></label>
						<form:radiobutton id="${poBoxFieldsPropertyName}FalseNewCity" class="fieldValue" path="${poBoxFieldsPropertyName}.newCity" value="false" />
						<label for="${poBoxFieldsPropertyName}FalseNewCity" class="fieldValueLabel"><fmt:message key="falseNewCityLabel"/></label>
						<form:radiobutton id="${poBoxFieldsPropertyName}TrueNewCity" class="fieldValue" path="${poBoxFieldsPropertyName}.newCity" value="true" checked ="checked"/>
						<label for="${poBoxFieldsPropertyName}TrueNewCity" class="fieldValueLabel"><fmt:message key="trueNewCityLabel"/></label>
					</c:when>
					<c:otherwise>
						<label class="fieldLabel" for="${poBoxFieldsPropertyName}.city"><fmt:message key="poBoxFieldsCityLabel"/></label>
						<form:radiobutton id="${poBoxFieldsPropertyName}FalseNewCity" class="fieldValue" path="${poBoxFieldsPropertyName}.newCity" value="false" checked ="checked"/>
						<label for="${poBoxFieldsPropertyName}FalseNewCity" class="fieldValueLabel"><fmt:message key="falseNewCityLabel"/></label>
						<form:radiobutton id="${poBoxFieldsPropertyName}TrueNewCity" class="fieldValue" path="${poBoxFieldsPropertyName}.newCity"  value="true"/>
						<label for="${poBoxFieldsPropertyName}TrueNewCity" class="fieldValueLabel"><fmt:message key="trueNewCityLabel"/></label>
					</c:otherwise>
				</c:choose>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.newCity"/>
			</span>
			<span id="${poBoxFieldsPropertyName}ExistingCityFieldGroup" class="${existingCityDisplayClass}">
				<select name="${poBoxFieldsPropertyName}.city" id="${poBoxFieldsPropertyName}City">
					<jsp:include page="poBoxFieldsCityOptions.jsp"/>
				</select>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.city"/>
			</span>
			<span id="${poBoxFieldsPropertyName}NewCityFieldGroup" class="${newCityDisplayClass}">
				<input type="text" name="${poBoxFieldsPropertyName}.cityName" id="${poBoxFieldsPropertyName}CityName" value="${poBoxFields.cityName}"/>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.cityName"/>
			</span>
		</span>
		<c:choose>
			<c:when test="${poBoxFields.newCity or poBoxFields.newZipCode}">
				<c:set value="" var="newZipCodeDisplayClass"/>
				<c:set value="hidden" var="existingZipCodeDisplayClass"/>
			</c:when>
			<c:otherwise>
				<c:set value="hidden" var="newZipCodeDisplayClass"/>
				<c:set value="" var="existingZipCodeDisplayClass"/>
			</c:otherwise>
		</c:choose>
		<span class="fieldGroup">
			<span id="${poBoxFieldsPropertyName}NewZipCodeSelectionFieldGroup">
				<c:choose>
					<c:when test="${poBoxFields.newZipCode}">
						<label class="fieldLabel" for="${poBoxFieldsPropertyName}.zipCodeValue"><fmt:message key="poBoxFieldsZipCodeValueLabel"/></label>
						<c:if test="${poBoxFields.newCity}">
							<form:radiobutton id="${poBoxFieldsPropertyName}FalseNewZipCode" class="fieldValue" path="${poBoxFieldsPropertyName}.newZipCode" value ="false" disabled="true"/>
						</c:if>
						<c:if test="${not poBoxFields.newCity}">
							<form:radiobutton id="${poBoxFieldsPropertyName}FalseNewZipCode" class="fieldValue" path="${poBoxFieldsPropertyName}.newZipCode" value ="false" disabled="false"/>
						</c:if>
						<label for="${poBoxFieldsPropertyName}FalseNewZipCode" class="fieldValueLabel"><fmt:message key="falseNewZipCodeLabel"/></label>
						<form:radiobutton id="${poBoxFieldsPropertyName}TrueNewZipCode" class="fieldValue" path="${poBoxFieldsPropertyName}.newZipCode" value = "true" checked ="checked"/>
						<label for="${poBoxFieldsPropertyName}TrueNewZipCode" class="fieldValueLabel"><fmt:message key="trueNewZipCodeLabel"/></label>
					</c:when>
					<c:otherwise>
						<label class="fieldLabel" for="${poBoxFieldsPropertyName}.zipCode"><fmt:message key="poBoxFieldsZipCodeLabel"/></label>
						<c:if test="${poBoxFields.newCity}">
							<form:radiobutton id="${poBoxFieldsPropertyName}FalseNewZipCode" class="fieldValue" path="${poBoxFieldsPropertyName}.newZipCode" value ="false" disabled="true" checked ="checked"/>
						</c:if>
						<c:if test="${not poBoxFields.newCity}">
							<form:radiobutton id="${poBoxFieldsPropertyName}FalseNewZipCode" class="fieldValue" path="${poBoxFieldsPropertyName}.newZipCode" value ="false" disabled="false" checked ="checked"/>
						</c:if>
						<label for="${poBoxFieldsPropertyName}FalseNewZipCode" class="fieldValueLabel"><fmt:message key="falseNewZipCodeLabel"/></label>
						<form:radiobutton id="${poBoxFieldsPropertyName}TrueNewZipCode" class="fieldValue" path="${poBoxFieldsPropertyName}.newZipCode" value = "true" />
						<label for="${poBoxFieldsPropertyName}TrueNewZipCode" class="fieldValueLabel"><fmt:message key="trueNewZipCodeLabel"/></label>
					</c:otherwise>
				</c:choose>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.newZipCode"/>
			</span>
			<span id="${poBoxFieldsPropertyName}ExistingZipCodeFieldGroup" class="${existingZipCodeDisplayClass}">
				<select name="${poBoxFieldsPropertyName}.zipCode" id="${poBoxFieldsPropertyName}ZipCode">
					<jsp:include page="poBoxFieldsZipCodeOptions.jsp"/>
				</select>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.zipCode"/>
			</span>
			<span id="${poBoxFieldsPropertyName}NewZipCodeFieldGroup" class="${newZipCodeDisplayClass}">
				<input type="text" name="${poBoxFieldsPropertyName}.zipCodeValue" id="${poBoxFieldsPropertyName}ZipCodeValue" value="${poBoxFields.zipCodeValue}"/>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.zipCodeValue"/>
				<label class="fieldValueLabel" for="${poBoxFieldsPropertyName}.zipCodeExtension"><fmt:message key="poBoxFieldsZipCodeExtensionLabel"/></label>
				<input type="text" name="${poBoxFieldsPropertyName}.zipCodeExtension" id="${poBoxFieldsPropertyName}ZipCodeExtension" value="${poBoxFields.zipCodeExtension}"/>
				<form:errors cssClass="error" path="${poBoxFieldsPropertyName}.zipCodeExtension"/>
			</span>
		</span>
	</div>
</fmt:bundle>