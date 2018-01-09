<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<form:form commandName="issuanceForm" class="editForm">
	<fieldset>	
		<sec:authorize access="hasRole('ADA_ACCOMMODATION_VIEW') or hasRole('ADMIN')">
		<span class="fieldGroup">
			<label for="summarize.disabilityClassificationCategory" class="fieldLabel">
			<fmt:message key="disabilityLabel"/></label>
			<fmt:message key="disabilityInformationLabel">
				<fmt:param value="${summarize.disabilityClassificationCategory}"/>
				<fmt:param value="${summarize.disabilityDescription}"/>
			</fmt:message>
		</span>
		<span class="fieldGroup">
			<label for="summarize.accommodationDescription" class="fieldLabel">
				<fmt:message key="accommodationDescriptionLabel"/></label>
			<c:out value="${summarize.accommodationDescription}"/>	
		</span>
		</sec:authorize>
		<span class="fieldGroup">
			<label for="summarize.accCategory" class="fieldLabel">
			<fmt:message key="categoryLabel"/></label>
			<c:out value="${summarize.accCategory}"/>
		</span>
		<span class="fieldGroup">
			<label for="summarize.authorizationSource" class="fieldLabel">
				<fmt:message key="authorizationSourceCategoryLabel"/></label>	
			<c:out value="${summarize.authorizationSource}"/>
		</span>
		<span class="fieldGroup">
			<label for="summarize.authorizationStartDate" class="fieldLabel">
				<fmt:message key="approvalDateLabel"/></label>				
			<fmt:formatDate value="${summarize.authorizationStartDate}" pattern="MM/dd/yyyy"/>
		</span>
		<span class="fieldGroup">
			<label for="summarize.authorizationEndDate" class="fieldLabel">
				<fmt:message key="expirationDateLabel"/></label>
			<fmt:formatDate value="${summarize.authorizationEndDate}" pattern="MM/dd/yyyy"/>
		</span>
		<span class="fieldGroup">
			<label for="summarize.createdByUsername" class="fieldLabel">
				<fmt:message key="createdByLabel"/></label>
			<fmt:formatDate var="createdDate" value="${summarize.createdDate}" pattern="MM/dd/yyyy"/>
			<fmt:message key="creationSignatureLabel">
				<fmt:param value="${summarize.createdByLastName}"/>
				<fmt:param value="${summarize.createdByFirstName}"/>
				<fmt:param value="${summarize.createdByUsername}"/>
				<fmt:param value="${createdDate}"/>
			</fmt:message>
		</span>		
	</fieldset>
	<fieldset>
	
	<input type="hidden" value="${summarize.accommodationId}"/>

	<span class="fieldGroup">
		<form:label path="day" class="fieldLabel">
			<fmt:message key="dayLabel"/></form:label>
		<form:input path="day" class="date"/>	
		<form:errors cssClass="error" path="day"/>
		<form:label path="time" class="fieldLabel">
			<fmt:message key="timeLabel"/></form:label>
		<form:input path="time" class="time"/>
		<form:errors cssClass="error" path="time"/>
	</span>
	<span class="fieldGroup">
		<form:label path="issuer" class="fieldLabel">
			<fmt:message key="issuerLabel"/></form:label>
		<input id="issuerName"/>
		<form:input path="issuer" type="hidden"/>			
		<a id="issuedByUser" class="currentUserAccountLink"></a>
		<a id="clearIssuedByUserLink" class="clearLink"></a>
		<span id="issuerCurrentLabel">
			<c:if test="${not empty issuanceForm.issuer}">
			<c:out value="${issuanceForm.issuer.name.lastName}, ${issuanceForm.issuer.name.firstName}"/>
			</c:if>				
		</span>
		<form:errors cssClass="error" path="issuer"/>	
	</span>
	<span class="fieldGroup">
		<form:label path="text" class="fieldLabel">
			<fmt:message key="textLabel"/></form:label>
		<form:textarea path="text"/>
		<form:errors cssClass="error" path="text"/>
	</span>
	</fieldset>
	<c:if test="${not empty issuance}">
		<c:set var="updatable" value="${issuance}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>
		</p>
</form:form>
</fmt:bundle>