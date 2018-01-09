<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Feb 9, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.audit.msgs.audit" var="auditBundle"/>
<fmt:bundle basename="omis.employment.msgs.employment">
<form:form commandName="employmentForm" action="${pageContext.request.contextPath}/employment/edit.html?offender=${offender.id}&employmentTerm=${employmentTerm.id}&employer=${employer.id}" class="editForm">
	<c:set var="addressFields" value="${employmentForm.addressFields}" scope="request"/>
	<c:set var="addressFieldsPropertyName" value="addressFields" scope="request"/>
	<fieldset>
		<c:if test="${employerStatus==false}">
			<legend><fmt:message key="createEmployerLabel"/></legend>
			<span class="fieldGroup">
				<form:label path="employerName" class="fieldLabel">
					<fmt:message key="employerLabel" />
				</form:label>
				<form:input path="employerName" id="employerName"/>
				<form:errors path="employerName"  cssClass="error"/>
			</span>
			<span class="fieldGroup">
				<form:label path="telephoneNumber" class="fieldLabel">
					<fmt:message key="telephoneNumberLabel"/></form:label>
				<form:input path="telephoneNumber" class="telephoneNumber"/>
				<form:errors path="telephoneNumber" cssClass="error"/>
			</span>
		</c:if>
		<c:if test="${employerStatus==true}">
			<legend><fmt:message key="employerSummaryLabel"/></legend>
		</c:if>
		<form:input type="hidden" path="existingEmployer" value="${existingEmployer.id}"/>
		<p>
			<c:if test="${employerStatus==false}">
				<c:if test="${employmentForm.newAddress==false}">
					<form:radiobutton path="newAddress" id="newAddressNo" value="false" checked ="checked"/><label class="fieldValueLabel" for="newAddressNo"><fmt:message key="newAddressNoLabel"/></label>
					<input type = "text" id="existingAddress"/>
					<form:hidden path="location"/>
					<a id="clearAddress" class="clearLink"></a>
					<span id="addressDisplay">
						<c:if test="${not empty employmentForm.location}"> 
						<c:out value="${location.organization.name}"/>, <c:out value="${location.address.value}"/>, <c:out value="${location.address.zipCode.city.name}"/> <c:out value="${location.address.zipCode.city.state.name}"/> <c:out value="${location.address.zipCode.value}"/>
					</c:if>
					</span>
					<form:errors path="location"  cssClass="error"/>
					<br>
					<form:radiobutton path="newAddress" id="newAddressYes" value="true" /><label class="fieldValueLabel" for="newAddressYes"><fmt:message key="newAddressYesLabel"/></label>
				</c:if>
				<c:if test="${employmentForm.newAddress==true}">
					<form:radiobutton path="newAddress" id="newAddressNo" value="false" /><label class="fieldValueLabel" for="newAddressNo"><fmt:message key="newAddressNoLabel"/></label>
					<input type = "text" id="existingAddress"/>
					<form:hidden path="location"/>
					<a id="clearAddress" class="clearLink"></a>
					<span id="addressDisplay">
						<c:if test="${not empty employmentForm.location}"> 
						<c:out value="${location.organization.name}"/>, <c:out value="${location.address.value}"/>, <c:out value="${location.address.zipCode.city.name}"/> <c:out value="${location.address.zipCode.city.state.name}"/> <c:out value="${location.address.zipCode.value}"/>
					</c:if>
					</span>
					<form:errors path="location"  cssClass="error"/>
					<br>
					<form:radiobutton path="newAddress" id="newAddressYes" value="true" checked ="checked"/><label class="fieldValueLabel" for="newAddressYes"><fmt:message key="newAddressYesLabel"/></label>
				</c:if>
				<jsp:include page="../../address/includes/addressFields.jsp"/>
			</c:if>
		</p>
		<p>
			<c:if test="${employerStatus==true}">
				<c:set var="existingEmployer" value="employmentForm.existingEmployer" scope="request"/>
				<c:set var="telephoneNumber">${employer.telephoneNumber}</c:set>
				<tr>
					<td><c:out value="${employer.location.organization.name}"/>, <c:out value="${employer.location.address.value}"/>, <c:out value="${employer.location.address.zipCode.city.name}"/>, <c:if test="${not empty employer.location.address.zipCode.city.state.name}"> <c:out value="${employer.location.address.zipCode.city.state.abbreviation}"/> </c:if> <c:out value="${employer.location.address.zipCode.value}"/> <c:if test="${not empty employer.location.address.zipCode.extension}"> - <c:out value="${employer.location.address.zipCode.extension}"/></c:if>, <c:out value="${employer.location.address.zipCode.city.country.name}"/></td>
				</tr>
				<br>
				<tr>
					<td> 
						<c:if test="${not empty employer.telephoneNumber}">
							<c:out value="Telephone Number:"/> 
						 	<c:choose>
								<c:when test="${fn:length(telephoneNumber) eq 10}">
									<c:out value="(${fn:substring(telephoneNumber, 0,3)})"/>
									<c:out value="${fn:substring(telephoneNumber, 3,6)}-"/>
									<c:out value="${fn:substring(telephoneNumber, 6,10)}"/>
								</c:when>
								<c:when test="${fn:length(telephoneNumber) eq 7}">
									<c:out value="${fn:substring(telephoneNumber, 0,3)}-"/>
									<c:out value="${fn:substring(telephoneNumber, 3,7)}"/>
								</c:when>
								<c:otherwise>
									<c:out value="${telephoneNumber}"/>
								</c:otherwise>
							</c:choose>	
						</c:if>  
					</td>
				</tr>
			</c:if>
		</p>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="employmentFormHeaderLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" />
			</form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate"  cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" />
			</form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate"  cssClass="error"/>
		</span>
		<span class="fieldGroup">	
			<form:label path="terminationReason" class="fieldLabel">
				<fmt:message key="terminationReasonLabel" />
			</form:label>
			<form:select path="terminationReason">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}" /></form:option>
			<form:options items="${terminationReasons}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="terminationReason"  cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="employmentFormPositionLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="extension" class="fieldLabel">
			 	<fmt:message key="extensionLabel" />
			 </form:label>
			<form:input path="extension" />
			<form:errors path="extension" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="jobTitle" class="fieldLabel">
				<fmt:message key="jobTitleLabel" />
			</form:label>
			<form:input path="jobTitle"/>
			<form:errors path="jobTitle"  cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="supervisorName" class="fieldLabel">
			 	<fmt:message key="supervisorNameLabel" />
			</form:label>
			<form:input path="supervisorName" type = "text"/>
			<form:errors path="supervisorName" cssClass="error"/>
		</span>
		<span class="fieldGroup">	
			<form:label path="workShift" class="fieldLabel">
				<fmt:message key="workShiftLabel" />
			</form:label>					
			<form:select path="workShift">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
                 <c:forEach items="${workShifts}" var="workShift">
                 	<form:option value="${workShift}">
                 		<fmt:message key="workShiftFrequencyLabel.${workShift}"/>
                 	</form:option>
                 </c:forEach>
			</form:select>
			<form:errors path="workShift" cssClass="error"/>
		</span>
		<span class="fieldGroup">		
			<form:label path="daysOff" title="Days off"  class="fieldLabel">
				<fmt:message key="daysOffLabel" />
			</form:label>
			<form:checkbox path="daysOff.sunday"/>
			<form:label path="daysOff.sunday" class="fieldValueLabel">
				<fmt:message key="daysOffSundayLabel" />
			</form:label>
			<form:errors path="daysOff.sunday" cssClass="error"/>
			<form:checkbox path="daysOff.monday"/>
			<form:label path="daysOff.monday" class="fieldValueLabel">
				<fmt:message key="daysOffMondayLabel" />
			</form:label>
			<form:errors path="daysOff.monday" cssClass="error"/>
			<form:checkbox path="daysOff.tuesday"/>
			<form:label path="daysOff.tuesday" class="fieldValueLabel">
				<fmt:message key="daysOffTuesdayLabel" />
			</form:label>
			<form:errors path="daysOff.tuesday" cssClass="error"/>
			<form:checkbox path="daysOff.wednesday"/>
			<form:label path="daysOff.wednesday" class="fieldValueLabel">
				<fmt:message key="daysOffWednesdayLabel" />
			</form:label>
			<form:errors path="daysOff.wednesday" cssClass="error"/>
			<form:checkbox path="daysOff.thursday"/>
			<form:label path="daysOff.thursday" class="fieldValueLabel">
				<fmt:message key="daysOffThursdayLabel" />
			</form:label>
			<form:errors path="daysOff.thursday" cssClass="error"/>
			<form:checkbox path="daysOff.friday"/>
			<form:label path="daysOff.friday" class="fieldValueLabel">
				<fmt:message key="daysOffFridayLabel" />
			</form:label>
			<form:errors path="daysOff.friday" cssClass="error"/>
			<form:checkbox path="daysOff.saturday"/>
			<form:label path="daysOff.saturday" class="fieldValueLabel">
				<fmt:message key="daysOffSaturdayLabel" />
			</form:label>
			<form:errors path="daysOff.saturday" cssClass="error"/>
			<form:checkbox path="varies"/>
			<form:label path="varies" class="fieldValueLabel">
				<fmt:message key="variesLabel" />
			</form:label>
			<form:errors path="varies" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startTime" Class="fieldLabel">
				<fmt:message key="startTimeLabel" />
			</form:label>					
			<form:input path="startTime" class="time"/>
			<form:errors path="startTime" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endTime" Class="fieldLabel">
				<fmt:message key="endTimeLabel" />
			</form:label>					
			<form:input path="endTime" class="time"/>
			<form:errors path="endTime" cssClass="error"/>
		</span>
		<span class="fieldGroup">	
			<form:label path="hoursPerWeek" cssClass="fieldLabel">
				<fmt:message key="hoursPerWeekLabel" />
			</form:label>					
			<form:input path="hoursPerWeek"/>
			<form:errors path="hoursPerWeek" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="employmentFormWagesLabel"/></legend>
		<span class="fieldGroup">	
			<form:label path="wageAmount" cssClass="fieldLabel">
				<fmt:message key="wageAmountLabel" />
			</form:label>					
			<form:input path="wageAmount"/>
			<form:errors path="wageAmount" cssClass="error"/>
		</span>
		<span class="fieldGroup">	
			<form:label path="paymentFrequency" class="fieldLabel">
				<fmt:message key="paymentFrequencyLabel" />
			</form:label>					
			<form:select path="paymentFrequency">
				<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
                 <c:forEach items="${paymentFrequencys}" var="paymentFrequency">
                 	<form:option value="${paymentFrequency}">
                 		<fmt:message key="paymentFrequencyLabel.${paymentFrequency}"/>
                 	</form:option>
                 </c:forEach>
			</form:select>
			<form:errors path="paymentFrequency" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="employmentFormVerificationLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="verificationUserAccount" class="fieldLabel">
				<fmt:message key="verificationUserAccountLabel"/></form:label>
			<input type="text" id="verificationUserInput"/>
			<a id="clearUserLink" class="fieldLink clearLink" title="<fmt:message key='clearLink' bundle='${commonBundle}'/>"><span class="linkLabel"><fmt:message key="clearLink" bundle="${commonBundle}"/></span></a>
			<a id="currentUserLink" class="fieldLink currentUserAccountLink" title="<fmt:message key='useCurrentUserAccountLink' bundle='${auditBundle}'/>"><span class="linkLabel"><fmt:message key="useCurrentUserAccountLink" bundle="${auditBundle}"/></span></a>
			<form:label id="verificationUserAccountLabel" path="verificationUserAccount" class="fieldValueLabel">
				<c:out value="${employmentForm.verificationUserAccountLabel}"/></form:label>
			<form:hidden path="verificationUserAccount"/>
			<form:errors path="verificationUserAccount" cssClass="error"/>
		</span>
		<span class="fieldGroup">	
			<form:label path="verificationDate" Class="fieldLabel">
				<fmt:message key="employmentVerificationDateLabel" />
			</form:label>					
			<form:input path="verificationDate" class="date"/>
			<form:errors path="verificationDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">	
			<form:label path="verified" Class="fieldLabel">
				<fmt:message key="employmentVerificationResultLabel" />
			</form:label>					
			<form:checkbox path="verified"/>
			<form:errors path="verified" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationMethod" class="fieldLabel">
				<fmt:message key="verificationMethodLabel" /></form:label>
			<form:select path="verificationMethod">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${verificationMethods}" itemValue="id" itemLabel="name"/>
			</form:select>
			<form:errors path="verificationMethod" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<span class="fieldGroup">	
			<form:checkbox path="convictedOfEmployerTheft"/>
			<form:label path="convictedOfEmployerTheft" Class="nowrap">
				<fmt:message key="convictedOfEmployerTheftLabel" />
			</form:label>
			<form:errors path="convictedOfEmployerTheft" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty employmentTerm}">
		<c:set var="updatable" value="${employmentTerm}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveEmploymentLabel' />"/>
	</p>
</form:form>
</fmt:bundle>