<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.family.msgs.family" var="familyBundle"/>
<c:if test="${empty familyAssociationFieldsPropertyName}">
		<c:set var="familyAssociationFieldsPropertyName" value="familyAssociationFields" scope="request"/>
	</c:if>
<span class="fieldGroup">
	<form:label path="${familyAssociationFieldsPropertyName}.startDate" class="fieldLabel"><fmt:message key="startDateLabel" bundle="${familyBundle}"/></form:label>
	<form:input id="familyAssociationFieldsStartDate" path="${familyAssociationFieldsPropertyName}.startDate" class="date"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.startDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${familyAssociationFieldsPropertyName}.endDate" class="fieldLabel"><fmt:message key="endDateLabel" bundle="${familyBundle}"/></form:label>
	<form:input id="familyAssociationFieldsEndDate" path="${familyAssociationFieldsPropertyName}.endDate" class="date"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.endDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${familyAssociationFieldsPropertyName}.category" class="fieldLabel" >
				<fmt:message key="relationshipLabel" bundle="${familyBundle}"/></form:label>
			<form:select path="${familyAssociationFieldsPropertyName}.category" id="relationship">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${familyAssociationCategories}" itemLabel="name" itemValue="id"/>
			</form:select>
	<form:errors path="${familyAssociationFieldsPropertyName}.category" cssClass="error"/>
</span>
<span class="fieldGroup" id="marriageDateContainer">
	<form:label path="${familyAssociationFieldsPropertyName}.marriageDate" class="fieldLabel"><fmt:message key="familyMarriageDateLabel" bundle="${familyBundle}"/></form:label>
	<form:input id="familyAssociationFieldsMarriageDate" path="${familyAssociationFieldsPropertyName}.marriageDate" class="date"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.marriageDate" cssClass="error"/>
</span>
<span class="fieldGroup" id="divorceDateContainer">
	<form:label path="${familyAssociationFieldsPropertyName}.divorceDate" class="fieldLabel"><fmt:message key="familyDivorceDateLabel" bundle="${familyBundle}"/></form:label>
	<form:input id="familyAssociationFieldsDivorceDate" path="${familyAssociationFieldsPropertyName}.divorceDate" class="date"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.divorceDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${familyAssociationFieldsPropertyName}.emergencyContact" class="fieldLabel"><fmt:message key="emergencyContactLabel" bundle="${familyBundle}"/></form:label>
	<form:checkbox path="${familyAssociationFieldsPropertyName}.emergencyContact" id="familyAssociationFieldsEmergencyContact"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.emergencyContact" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${familyAssociationFieldsPropertyName}.dependent" class="fieldLabel"><fmt:message key="familyDependantLabel" bundle="${familyBundle}"/></form:label>
	<form:checkbox path="${familyAssociationFieldsPropertyName}.dependent" id="familyAssociationFieldsDependent"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.dependent" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${familyAssociationFieldsPropertyName}.cohabitant" class="fieldLabel"><fmt:message key="familyCohabitateLabel" bundle="${familyBundle}"/></form:label>
	<form:checkbox path="${familyAssociationFieldsPropertyName}.cohabitant" id="familyAssociationFieldsCohabitant"/>
	<form:errors path="${familyAssociationFieldsPropertyName}.cohabitant" cssClass="error"/>
</span>