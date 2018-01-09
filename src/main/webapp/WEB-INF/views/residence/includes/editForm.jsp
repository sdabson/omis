<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.residence.msgs.residence">
<sec:authorize var="editResidence" access="hasRole('RESIDENCE_TERM_EDIT') or hasRole('NON_RESIDENCE_TERM_EDIT') or (hasRole('RESIDENCE_TERM_CREATE') and hasRole('NON_RESIDENCE_TERM_CREATE')) or hasRole('ADMIN')"/>
<form:form commandName="residenceForm" class="editForm">	
	<fieldset>
	<legend>
		<fmt:message key="residenceHeaderTitle"/>
	</legend>
	<form:input type="hidden" path="statusOption"/>
	<span class="fieldGroup">
	<form:label path="startDate" class="fieldLabel">
		<fmt:message key="startDateLabel"/></form:label>
	<form:input path="startDate" class="date"/>
	<form:errors cssClass="error" path="startDate"/>
</span>
<span class="fieldGroup">
	<form:label path="endDate" class="fieldLabel">
		<fmt:message key="endDateLabel"/></form:label>
	<form:input path="endDate" class="date"/>
	<form:errors path="endDate" cssClass="error"/>
</span>
<c:choose>
	<c:when test="${residenceForm.statusOption.name == 'PRIMARY_RESIDENCE' || residenceForm.statusOption.name == 'SECONDARY_RESIDENCE' || residenceForm.statusOption.name == 'FOSTER_CARE'}">
		<c:set var="displayClass" value="fieldGroup"/>		
	</c:when>
	<c:otherwise>
		<c:set var="displayClass" value="fieldGroup"/>
		<c:choose>
			<c:when	test="${residenceForm.createNewLocation}">
				<c:set var="newLocationClass" value=""/>
			</c:when>
			<c:otherwise>
				<c:set var="newLocationClass" value="hidden"/>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
<span class="${displayClass} ${newLocationClass}" id="streetNameGroup">
	<form:label path="value" class="fieldLabel">
		<fmt:message key="addressValueLabel"/>
	</form:label>
	<form:input path="value" id="value" class="addressValue"/>		
	<form:errors path="value" cssClass="error"/>
</span>
<span class="fieldGroup" id="stateGroup">
	<form:label path="state" class="fieldLabel">
		<fmt:message key="stateLabel"/></form:label>
	<form:select path="state" id="state">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${states}" itemLabel="name" itemValue="id"/>
	</form:select>	
	<form:errors path="state" cssClass="error"/>
</span>	
<span class="fieldGroup" id="cityGroup">
	<form:label path="city" class="fieldLabel">
		<fmt:message key="cityLabel"/></form:label>
	<form:select path="city" id="city">
				<jsp:include page="cityOptions.jsp"/>
	</form:select>			
	<form:errors path="city" cssClass="error"/>
</span>
<span class="${displayClass} ${newLocationClass}" id="zipCodeGroup">
	<form:label path="zipCode" class="fieldLabel">
		<fmt:message key="zipCodeLabel"/></form:label>	
	<form:select path="zipCode">
			<jsp:include page="zipCodeOptions.jsp"/>
	</form:select>
	<form:errors path="zipCode" cssClass="error"/>
</span>
<c:choose>
	<c:when test="${residenceForm.statusOption.name == 'GROUP_HOME' || residenceForm.statusOption.name == 'HOTEL'}">
		<c:set var="displayClass" value=""/>
		<c:choose>
			<c:when	test="${residenceForm.createNewLocation}">
				<c:set var="newLocationClass" value=""/>
				<c:set var="existingLocationClass" value="hidden"/>
			</c:when>
			<c:otherwise>
				<c:set var="newLocationClass" value="hidden"/>
				<c:set var="existingLocationClass" value=""/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="displayClass" value="hidden"/>
	</c:otherwise>
</c:choose>
<span  class="fieldGroup ${displayClass}" id="locationGroup">	
	<form:label path="location" class="fieldLabel">
		<fmt:message key="locationLabel"/></form:label>		
	<form:select path="location" id="location" class="${existingLocationClass}">
		<jsp:include page="allowedResidenceLocationOptions.jsp"/>
	</form:select>	
	<form:errors path="location" cssClass="error"/>
	<form:input id="locationName" path="locationName" class="${newLocationClass}"/>
	<form:errors path="locationName" cssClass="error"/>	
	<label for="createNewLocation" class="fieldValueLabel"><fmt:message key="createNewLocationLabel"/></label>
	<form:checkbox id="createNewLocation" path="createNewLocation"/>
</span>
<span class="fieldGroup">
	<form:label path="residenceComment" class="fieldLabel">
		<fmt:message key="commentLabel"/></form:label>
	<form:textarea path="residenceComment"/>
	<form:errors path="residenceComment" cssClass="error"/>
</span>
<span class="fieldGroup">
		<form:label path="confirmed" class="fieldLabel"><fmt:message key="residenceConfirmationLabel"/></form:label>
		<form:checkbox path="confirmed"/>
</span>
</fieldset>
<c:if test="${not empty existingResidenceSummary}">
	<fieldset>
		<legend>
			<fmt:message key="existingPrimaryResidenceHeaderTitle"/>
		</legend>
		<input type="hidden" id="existingPrimaryResidence" name="existingPrimaryResidence" value="${existingResidenceSummary.id}"/>
		<span class="contactSummarySection">
			<span class="contactSummarySubSection">
				<c:set value="${existingResidenceSummary}" var="addressSummarizable" scope="request"/>
				<label class="subSectionLabel"><fmt:message key="addressValueLabel"/></label>
				<jsp:include page="../../address/includes/addressSummary.jsp"/>
			</span>
		</span>
		<span class="fieldGroup">
			<label class="fieldLabel"><fmt:message key="setExistingPrimaryLabel"/></label>
			<c:choose>
				<c:when test="${residenceForm.existingResidenceOperation == 'SECONDARY'}">
					<input type="radio" class="fieldValue" id="existingPrimarySetSecondary" name="existingResidenceOperation" value="SECONDARY" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="radio" class="fieldValue" id="existingPrimarySetSecondary" name="existingResidenceOperation" value="SECONDARY"/>
				</c:otherwise>
			</c:choose>
			<label for="existingPrimarySetSecondary" title="<fmt:message key='existingPrimaryLabel.SECONDARY'/>" class="fieldValueLabel"><fmt:message key="existingPrimaryLabel.SECONDARY"/></label>
			<c:choose>
				<c:when test="${residenceForm.existingResidenceOperation == 'HISTORICAL'}">
					<input type="radio" class="fieldValue" id="existingPrimarySetHistorical" name="existingResidenceOperation" value="HISTORICAL" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="radio" class="fieldValue" id="existingPrimarySetHistorical" name="existingResidenceOperation" value="HISTORICAL"/>
				</c:otherwise>
			</c:choose>
			<label for="existingPrimarySetHistorical" title="<fmt:message key='existingPrimaryLabel.HISTORICAL'/>" class="fieldValueLabel"><fmt:message key="existingPrimaryLabel.HISTORICAL"/></label>
			<form:errors path="existingResidenceOperation" cssClass="error"/>
		</span>
		<c:choose>
			<c:when test="${residenceForm.existingResidenceOperation == 'HISTORICAL'}">
				<c:set var="existingPrimaryEndDateClass" value=""/>
			</c:when>
			<c:otherwise>
				<c:set var="existingPrimaryEndDateClass" value="hidden"/>
			</c:otherwise>
		</c:choose>
		<span id="existingPrimaryEndDate" class="fieldGroup ${existingPrimaryEndDateClass}">
			<label class="fieldLabel"><fmt:message key="existingPrimaryEndDateLabel"/></label>
			<input type="text" id="existingHistoricalEndDate" name="existingHistoricalEndDate" class="date" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${residenceForm.existingHistoricalEndDate}'/>"/>
			<form:errors path="existingHistoricalEndDate" cssClass="error"/>
		</span>
	</fieldset>
</c:if>
<c:choose>
	<c:when test="${residenceForm.statusOption == 'HOMELESS'}">
		<c:set var="displayClass" value="fieldGroup hidden"/>
	</c:when>
	<c:otherwise>
		<c:set var="displayClass" value="fieldGroup"/>
	</c:otherwise>
</c:choose>
<div id="verificationFieldSet" class="${displayClass}">
<fieldset>
	<legend>
		<fmt:message key="verificationHeaderTitle"/>
	</legend>
	<span class="fieldGroup">
		<form:label path="verifiedByUserAccount" class="fieldLabel">
			<fmt:message key="verifiedByLabel"/></form:label>		
		<input id="verifiedBy"/>
		<form:input path="verifiedByUserAccount" type="hidden"/>
		<a id="verifiedByUser" class="currentUserAccountLink"></a>
		<a id="clearVerifiedByUserLink" class="clearLink"></a>	
		<span id="verifiedCurrentLabel">
			<c:if test="${not empty residenceForm.verifiedByUserAccount}">
			<c:out value="${residenceForm.verifiedByUserAccount.user.name.lastName}, ${residenceForm.verifiedByUserAccount.user.name.firstName}"/>
			</c:if>
		</span>
		<form:errors path="verifiedByUserAccount" cssClass="error"/>	
	</span>	
	<span class="fieldGroup">
		<form:label path="verificationDate" class="fieldLabel">
			<fmt:message key="verificationDateLabel"/></form:label>
		<form:input path="verificationDate" class="date"/>
		<form:errors path="verificationDate" cssClass="error"/>
	</span>
	<span class="fieldGroup">
		<form:label path="verificationMethod" class="fieldLabel">
			<fmt:message key="verificationMethodLabel"/></form:label>
		<form:select path="verificationMethod">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${verificationMethods}" itemValue="id" itemLabel="name"/>
		</form:select>
		<form:errors path="verificationMethod" cssClass="error"/>
	</span>		
</fieldset>
</div>
<c:if test="${not empty residenceTerm || not empty nonResidenceTerm}">
	<c:choose>
		<c:when test="${not empty residenceTerm}"><c:set var="updatable" value="${residenceTerm}" scope="request"/></c:when>
		<c:when test="${not empty nonResidenceTerm}"><c:set var="updatable" value="${nonResidenceTerm}" scope="request"/></c:when>
	</c:choose>
	<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<p class="buttons">
	<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>
</p>
</form:form>
</fmt:bundle>