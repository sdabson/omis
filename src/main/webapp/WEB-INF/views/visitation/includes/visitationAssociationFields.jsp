<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.visitation.msgs.visitation" var="visitationBundle"/>
<c:if test="${empty visitationAssociationFieldsPropertyName}">
		<c:set var="familyAssociationFieldsPropertyName" value="familyAssociationFields" scope="request"/>
	</c:if>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.money" class="fieldLabel"><fmt:message key="visitationAssociationMoneyLabel" bundle="${visitationBundle}"/></form:label>
	<form:checkbox path="${visitationAssociationFieldsPropertyName}.money" id="visitationAssociationFieldsMoney"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.money" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.nonContact" class="fieldLabel"><fmt:message key="visitationAssociationNonContactLabel" bundle="${visitationBundle}"/></form:label>
	<form:checkbox path="${visitationAssociationFieldsPropertyName}.nonContact" id="visitationAssociationFieldsNonContact"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.nonContact" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.courtOrder" class="fieldLabel"><fmt:message key="visitationAssociationCourtOrderLabel" bundle="${visitationBundle}"/></form:label>
	<form:checkbox path="${visitationAssociationFieldsPropertyName}.courtOrder" id="visitationAssociationFieldsCourtOrder"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.courtOrder" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.specialVisit" class="fieldLabel"><fmt:message key="visitationAssociationSpecialVisitLabel" bundle="${visitationBundle}"/></form:label>
	<form:checkbox path="${visitationAssociationFieldsPropertyName}.specialVisit" id="visitationAssociationFieldsSpecialVisit"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.specialVisit" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.approved" class="fieldLabel"><fmt:message key="visitationAssociationApprovedLabel" bundle="${visitationBundle}"/></form:label>
	<form:checkbox path="${visitationAssociationFieldsPropertyName}.approved" id="visitationAssociationFieldsApproved"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.approved" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.decisionDate" class="fieldLabel"><fmt:message key="visitationAssociationDecisionDateLabel" bundle="${visitationBundle}"/></form:label>
	<form:input id="visitationAssociationFieldsDecisionDate" path="${visitationAssociationFieldsPropertyName}.decisionDate" class="date"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.decisionDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.startDate" class="fieldLabel"><fmt:message key="visitationAssociationStartDateLabel" bundle="${visitationBundle}"/></form:label>
	<form:input id="visitationAssociationFieldsStartDate" path="${visitationAssociationFieldsPropertyName}.startDate" class="date"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.startDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.endDate" class="fieldLabel"><fmt:message key="visitationAssociationEndDateLabel" bundle="${visitationBundle}"/></form:label>
	<form:input id="visitationAssociationFieldsEndDate" path="${visitationAssociationFieldsPropertyName}.endDate" class="date"/>
	<form:errors path="${visitationAssociationFieldsPropertyName}.endDate" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.category" class="fieldLabel">
				<fmt:message key="visitationAssociationCategoryLabel" bundle="${visitationBundle}"/></form:label>
			<form:select path="${visitationAssociationFieldsPropertyName}.category">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>			
					<form:options items="${visitationAssociationCategories}" itemLabel="name" itemValue="id"/>					
			</form:select>
	<form:errors path="${visitationAssociationFieldsPropertyName}.category" cssClass="error"/>
</span>
<span class="fieldGroup">
		<form:label path="${visitationAssociationFieldsPropertyName}.guardianship" class="fieldLabel"><fmt:message key="guardianshipLabel" bundle="${visitationBundle}"/></form:label>
		<form:textarea path="${visitationAssociationFieldsPropertyName}.guardianship" id="${visitationAssociationFieldsPropertyName}Guardianship" maxlength="1024" rows="8"/>
		<span class="characterCounter" id="guardianshipCharacterCounter">
		</span>
	</span>
<span class="fieldGroup">
	<form:label path="${visitationAssociationFieldsPropertyName}.notes" class="fieldLabel"><fmt:message key="visitationAssociationNotesLabel" bundle="${visitationBundle}"/></form:label>
	<form:textarea path="${visitationAssociationFieldsPropertyName}.notes" id="${visitationAssociationFieldsPropertyName}Notes" maxlength="2048" rows="8"/>
	<span class="characterCounter" id="notesCharacterCounter">
	</span>
	<form:errors path="${visitationAssociationFieldsPropertyName}.notes" cssClass="error"/>
</span>