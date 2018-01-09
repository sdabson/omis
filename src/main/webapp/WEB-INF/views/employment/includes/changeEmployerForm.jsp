<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:bundle basename="omis.employment.msgs.employment">
<form:form commandName="employerChangeForm" class="editForm">
	<c:set var="addressFields" value="${employerChangeForm.addressFields}" scope="request"/>
	<c:set var="employmentTerm" value="${employmentTerm}" scope="request"/>
	<fieldset>
		<legend><fmt:message key="employerLabel"/></legend>
		<c:choose>
			<c:when test="${createEmployer}">
				<c:choose>
					<c:when test="${createAddress}">  
						<c:set var="changeEmployerContainorClass" value=""/>
						<c:set var="changeEmployerAddressFieldsRadioButtonsContainorClass" value=""/>
						<c:set var="changeEmployerAddressFieldsContainerClass" value=""/>
						<c:set var="existingAddressClass" value="fieldGroup hidden"/>
						<c:set var="existingEmployerClass" value="fieldGroup hidden"/>
					</c:when>
					<c:otherwise>
						<c:set var="changeEmployerContainorClass" value=""/>
						<c:set var="changeEmployerAddressFieldsRadioButtonsContainorClass" value=""/>
						<c:set var="changeEmployerAddressFieldsContainerClass" value="fieldGroup hidden"/>
						<c:set var="existingAddressClass" value=""/>
						<c:set var="existingEmployerClass" value="fieldGroup hidden"/>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:set var="changeEmployerContainorClass" value="fieldGroup hidden"/>
				<c:set var="changeEmployerAddressFieldsRadioButtonsContainorClass" value="fieldGroup hidden"/>
				<c:set var="changeEmployerAddressFieldsContainerClass" value="fieldGroup hidden"/>
				<c:set var="existingAddressClass" value="fieldGroup hidden"/>
				<c:set var="existingEmployerClass" value=""/>
			</c:otherwise>
		</c:choose>
		<span class="fieldGroup">
			<c:choose>
				<c:when test="${employerChangeForm.newEmployer}">
					<p>
						<form:radiobutton path="newEmployer" id="newEmployerNo" value="false" /><label class="fieldValueLabel" for="newEmployerNo"><fmt:message key="newEmployerNoLabel"/></label>
						<input type = "text" id="employerInput" />
						<form:hidden path="existingEmployer"/>
						<a id="clearEmployer" class="clearLink"></a>
						<span id="employerDisplay">
							<c:if test="${not empty employerChangeForm.existingEmployer}"> 
							<c:out value="${employerChangeForm.existingEmployer.location.organization.name}"/>, <c:out value="${employerChangeForm.existingEmployer.location.address.value}"/>, <c:out value="${employerChangeForm.existingEmployer.location.address.zipCode.city.name}"/> <c:out value="${employerChangeForm.existingEmployer.location.address.zipCode.city.state.name}"/>
						</c:if>
						</span>
						<form:errors path="existingEmployer"  cssClass="error"/>
					</p>
					<p>
						<form:radiobutton path="newEmployer" id="newEmployerYes" value="true" checked="checked"/><label class="fieldValueLabel" for="newEmployerYes"><fmt:message key="newEmployerYesLabel"/></label>
						<form:input path="newEmployerName" type="text" id="newEmployerInput"/>
						<form:errors path="newEmployerName"  cssClass="error"/>
					</p>
				</c:when>
				<c:otherwise>
					<p>
						<form:radiobutton path="newEmployer" id="newEmployerNo" value="false"  checked="checked" /><label class="fieldValueLabel" for="newAddressNo"><fmt:message key="newEmployerNoLabel"/></label>
						<input type = "text" id="employerInput" />
						<form:hidden path="existingEmployer"/>
						<a id="clearEmployer" class="clearLink"></a>
						<span id="employerDisplay">
							<c:if test="${not empty employerChangeForm.existingEmployer}"> 
							<c:out value="${employerChangeForm.existingEmployer.location.organization.name}"/>, <c:out value="${employerChangeForm.existingEmployer.location.address.value}"/>, <c:out value="${employerChangeForm.existingEmployer.location.address.zipCode.city.name}"/> <c:out value="${employerChangeForm.existingEmployer.location.address.zipCode.city.state.name}"/>
						</c:if>
						</span>
						<form:errors path="existingEmployer"  cssClass="error"/>
					</p>
					<p>
						<form:radiobutton path="newEmployer" id="newEmployerYes" value="true" /><label class="fieldValueLabel" for="newEmployerYes"><fmt:message key="newEmployerYesLabel"/></label>
						<form:input path="newEmployerName" type="text" id="newEmployerInput"/>
						<form:errors path="newEmployerName"  cssClass="error"/>
					</p>
				</c:otherwise>
			</c:choose>
		</span>
		<div id = "changeEmployerContainor" class="${changeEmployerContainorClass}">
			<span class="fieldGroup">
				<br>
				<form:label path="telephoneNumber" class="fieldLabel">
					<fmt:message key="telephoneNumberLabel"/></form:label>
				<form:input path="telephoneNumber" class="telephoneNumber"/>
				<form:errors path="telephoneNumber" cssClass="error"/>
			</span>
		</div>
		<div id = "changeEmployerAddressFieldsRadioButtonsContainor" class="${changeEmployerAddressFieldsRadioButtonsContainorClass}">
			<span class="fieldGroup">
				<c:if test="${employerChangeForm.newAddress==false}">
					<p>
						<form:radiobutton path="newAddress" id="newAddressNo" value="false" checked ="checked"/><label class="fieldValueLabel" for="newAddressNo"><fmt:message key="newAddressNoLabel"/></label>
						<form:input path="addressQuery"  id="existingChangeEmployorAddress" class="${existingAddressClass}, medium"/>
						<form:errors path="addressQuery" cssClass="error"/>
						<form:hidden path="addressSearchResult" id="changeEmployerExistingAddress"/>
						<form:errors path="addressSearchResult" cssClass="error"/>
					</p>
					<br>
					<p>
						<form:radiobutton path="newAddress" id="newAddressYes" value="true" /><label class="fieldValueLabel" for="newAddressYes"><fmt:message key="newAddressYesLabel"/></label>
					</p>
				</c:if>
				<c:if test="${employerChangeForm.newAddress==true}">
					<p>
						<form:radiobutton path="newAddress" id="newAddressNo" value="false" /><label class="fieldValueLabel" for="newAddressNo"><fmt:message key="newAddressNoLabel"/></label>
						<form:input path="addressQuery"  id="existingChangeEmployorAddress" class="${existingAddressClass}, medium"/>
						<form:errors path="addressQuery" cssClass="error"/>
						<form:hidden path="addressSearchResult" id="changeEmployerExistingAddress"/>
						<form:errors path="addressSearchResult"  cssClass="error"/>
					</p>
					<br>
					<p>
						<form:radiobutton path="newAddress" id="newAddressYes" value="true" checked ="checked"/><label class="fieldValueLabel" for="newAddressYes"><fmt:message key="newAddressYesLabel"/></label>
					</p>
				</c:if>
			</span>
		</div>
		<div id="changeEmployerAddressFieldsContainer" class="${changeEmployerAddressFieldsContainerClass}">
			<jsp:include page="../../address/includes/addressFields.jsp"/>
		</div>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveEmploymentLabel' />"/>
	</p>
</form:form>
</fmt:bundle>