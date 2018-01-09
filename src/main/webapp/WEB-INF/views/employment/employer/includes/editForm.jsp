<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<fmt:bundle basename="omis.employment.msgs.employment">
<form:form id="employerForm" commandName="employer">
	<p>
		<form:label path="name"><fmt:message key="employerNameLabel"/>:</form:label>
		<form:input path="name"/>
		<form:errors path="name" cssClass="error"/>
	</p>
	<p>
		<span class="nowrap">
			<form:label path="telephoneNumber.areaCode">
				<fmt:message key="employerTelephoneLabel"/>:
			</form:label>
			(<form:input path="telephoneNumber.areaCode" class="areaCode"/>)
			<form:errors path="telephoneNumber.areaCode" cssClass="error"/>
			<form:input path="telephoneNumber.number" class="phone"/>
		</span>
		
	</p>
	<p>
		<span class="nowrap">
			<form:label path="address.value">
				<fmt:message key="employerAddressValueLabel"/>:
			</form:label>
			<form:input path="address.value"/>
			<form:errors path="address.value" cssClass="error"/>
		</span>
		<span id="nonMontana" class="nowrap">
			<form:label path="address.city">
				<fmt:message key="employerAddressCityLabel"/>:
			</form:label>
			<form:input path="address.city"/>
			<form:errors path="address.city" cssClass="error"/>
		</span>
		<span id="montana" class="nowrap">
			<form:label path="address.city">
				<fmt:message key="employerAddressCityLabel"/>:
			</form:label>
			<form:select path="address.city">
				<form:options items="${cityList}" itemLabel="name" itemValue="code"/>
			</form:select>
		</span>
		<span class="nowrap">
			<form:label path="address.state.name">
				<fmt:message key="employerAddressStateLabel"/>:
			</form:label>
			<form:select path="address.state" onchange="changeState()">
				<form:options items="${stateList}" itemLabel="name" itemValue="code"/>
			</form:select>
			<form:errors path="address.state" cssClass="error"/>
		</span>
		<span class="nowrap">
			<form:label path="address.zipCode">
				<fmt:message key="employerAddressZipLabel"/>:
			</form:label>
			<form:input path="address.zipCode" class="zipCode"/>
			<form:errors path="address.zipCode" cssClass="error"/>
		</span>
	</p>
	<button id="employerSubmit" type="button"><fmt:message key="saveEmployerLabel"/></button>
</form:form>
</fmt:bundle>
