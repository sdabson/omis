<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<form:form commandName="providerAssignmentForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="providerDetailsLabel"/></legend>
		<span class="fieldGroup">
					<form:label path="provider" class="fieldLabel"><fmt:message key="providerLabel"/></form:label>
					<form:hidden path="provider"/>
					<input type="text" id="providerInput"/>
					<a id="providerCurrent" class="currentUserAccountLink"></a>
					<a id="providerClear" class="clearLink"></a>
					<span class="providerDisplay">
						<c:if test="${not empty providerAssignmentForm.provider}">
							<fmt:message key="providerAssignmentNameLabel">
								<fmt:param value="${providerAssignmentForm.provider.name.lastName}"/>
								<fmt:param value="${providerAssignmentForm.provider.name.firstName}"/>
							</fmt:message>
						</c:if>
					</span>
					<form:errors cssClass="error" path="provider"/>
				</span>
		<span class="fieldGroup">
			<form:label path="title" class="fieldLabel"><fmt:message key="providerTitleLabel"/></form:label>
			<form:select path="title">
					<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
					<form:options items="${titles}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="title"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel"><fmt:message key="providerStartDateLabel"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors cssClass="error" path="startDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel"><fmt:message key="providerEndDateLabel"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors cssClass="error" path="endDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="providerType" class="fieldLabel"><fmt:message key="contractedLabel"/></form:label>
			<c:forEach var="type" items="${providerTypes}">
				<c:choose>
					<c:when test="${not empty providerAssignment}">
						<form:radiobutton path="providerType" value="${type}" disabled="true" />
						<c:if test="${providerAssignmentForm.providerType eq type}">
							<input type="hidden" value="${type}" name="providerType"/>
						</c:if>
					</c:when>
					<c:otherwise>
						<form:radiobutton path="providerType" value="${type}" />
					</c:otherwise>
				</c:choose>
				<label class="fieldValueLabel"><fmt:message key="providerTypeLabel.${type}"/></label>
			</c:forEach>
			<form:errors cssClass="error" path="providerType"/>
		</span>
		<c:set var="providerAssignmentForm" scope="request" value="${providerAssignmentForm}"/>
		<div id="medicalFacilityArea">
			<c:if test="${providerAssignmentForm.providerType eq 'EXTERNAL'}">
				<jsp:include page="medicalFacility.jsp"/>
			</c:if>
		</div>
	</fieldset>
 		<p class="buttons">
 			<input type="submit" value="<fmt:message key='providerSaveLabel'/>"/>
 		</p>
</form:form> 
</fmt:bundle>
