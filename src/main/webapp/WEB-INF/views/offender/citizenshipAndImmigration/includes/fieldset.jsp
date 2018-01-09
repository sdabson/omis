<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="citizenshipBundle" basename="omis.citizenship.msgs.citizenship"/>
<fmt:setBundle var="immigrationBundle" basename="omis.immigration.msgs.immigration"/>
<fmt:setBundle var="offenderBundle" basename="omis.offender.msgs.offender"/>
	<fieldset>
		<legend><fmt:message key="citizenshipAndImmigrationLabel" bundle="${offenderBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="countryOfCitizenship" class="fieldLabel">
				<fmt:message key="countryOfCitizenshipLabel" bundle="${citizenshipBundle}"/></form:label>
			<form:select path="countryOfCitizenship">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${countries}"/>
			</form:select>
		<form:errors path="countryOfCitizenship" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="alienResidenceLegality" class="fieldLabel">
				<fmt:message key="alienResidenceLegalityLabel" bundle="${immigrationBundle}"/></form:label>
			<form:select path="alienResidenceLegality" disabled="${homeCountryCitizen}">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:option value="LEGAL"><fmt:message key="legalLabel" bundle="${immigrationBundle}"/></form:option>
				<form:option value="ILLEGAL"><fmt:message key="illegalLabel" bundle="${immigrationBundle}"/></form:option>
				<form:option value="UNKNOWN"><fmt:message key="unknownLabel" bundle="${immigrationBundle}"/></form:option>
			</form:select>
			<form:errors path="alienResidenceLegality" cssClass="error"/>
		</span>
	</fieldset>