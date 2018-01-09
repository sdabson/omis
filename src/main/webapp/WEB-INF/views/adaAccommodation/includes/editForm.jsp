<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
<form:form commandName="accommodationForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="disabilityTitle"/></legend>
		<span class="fieldGroup">
		<form:label path="disabilityCategory" class="fieldLabel">
			<fmt:message key="disabilityCategoryLabel"/></form:label>
		<form:select path="disabilityCategory">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${disabilityCategories}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors cssClass="error" path="disabilityCategory"/>
		</span>
		<span class="fieldGroup">
		<form:label path="disabilityDescription" class="fieldLabel">
			<fmt:message key="disabilityDescriptionLabel"/></form:label>
		<form:textarea path="disabilityDescription"/>
		<form:errors cssClass="error" path="disabilityDescription"/>
		</span>			
	</fieldset>
	<fieldset>
		<legend><fmt:message key="authorizationTitle"/></legend>
		<span class ="fieldGroup">
			<form:label path="authorizationUser" class="fieldLabel">
				<fmt:message key="authorizationSignatureLabel"/></form:label>
			<input id="authorizationSignature"/>
			<form:input path="authorizationUser" type="hidden"/>			
			<a id="authorizedByUser" class="currentUserAccountLink"></a>
			<a id="clearAuthorizedByUserLink" class="clearLink"></a>
			<span id="authorizedCurrentLabel">
			<c:if test="${not empty accommodationForm.authorizationUser}">
			<c:out value="${accommodationForm.authorizationUser.user.name.lastName}, ${accommodationForm.authorizationUser.user.name.firstName}"/>
			</c:if>
			</span>
		<form:errors path="authorizationUser" cssClass="error"/>	
		</span>
		<span class="fieldGroup">
			<form:label path="authorizationDate" class="fieldLabel">
				<fmt:message key="authorizationDateLabel"/></form:label>
			<form:input path="authorizationDate" class="date"/>
			<form:errors path="authorizationDate" cssClass="error"/>
		</span>
		<span>
		</span>
		<span class="fieldGroup">
		<form:label path="authorizationSourceCategory" class="fieldLabel">
			<fmt:message key="authorizationSourceCategoryLabel"/></form:label>
		<form:select path="authorizationSourceCategory">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${authorizationSourceCategories}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors cssClass="error" path="authorizationSourceCategory"/>
		</span>
		<span class="fieldGroup">
		<form:label path="temporaryAuthorization" class="fieldLabel">
			<fmt:message key="temporaryAuthorizationLabel"/></form:label>
		<form:select id="temporaryAuthorization" path="temporaryAuthorization">
			<form:option value="true"><fmt:message key="temporaryLabel"/></form:option>
			<form:option value="false"><fmt:message key="permanentLabel"/></form:option>
		</form:select>
		<form:errors cssClass="error" path="temporaryAuthorization"/>
		</span>
		<span class="fieldGroup">
		<form:label path="startDate" class="fieldLabel">
			<fmt:message key="authorizationTermStartDateLabel"/></form:label>
		<form:input path="startDate" class="date"/>
		<form:errors cssClass="error" path="startDate"/>
		</span>
		<c:choose>
			<c:when test="${accommodationForm.temporaryAuthorization}">
				<c:set var="endDateGroupClass" value="fieldGroup"/>
			</c:when>
			<c:otherwise>
				<c:set var="endDateGroupClass" value="fieldGroup hidden"/>
			</c:otherwise>
		</c:choose>
		<span id="endDateGroup" class="${endDateGroupClass}">
		<form:label path="endDate" class="fieldLabel">
			<fmt:message key="authorizationTermEndDateLabel"/></form:label>
		<form:input id="endDate" path="endDate" class="date"/>
		<form:errors cssClass="error" path="endDate"/>
		</span>
		<span class="fieldGroup">
		<form:label path="authorizationComments" class="fieldLabel">
			<fmt:message key="authorizationCommentsLabel"/></form:label>
		<form:textarea path="authorizationComments"/>
		<form:errors cssClass="error" path="authorizationComments"/>
		</span>		
	</fieldset>
	<fieldset>
		<legend><fmt:message key="accommodationTitle"/></legend>
		<span class="fieldGroup">
		<form:label path="accommodationDescription" class="fieldLabel">
			<fmt:message key="accommodationDescriptionLabel"/></form:label>
		<form:textarea path="accommodationDescription"/>
		<form:errors cssClass="error" path="accommodationDescription"/>
		</span>
		<span class="fieldGroup">
		<form:label path="accommodationCategory" class="fieldLabel">
			<fmt:message key="accommodationCategoryLabel"/></form:label>
			<form:select path="accommodationCategory">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options items="${accommodationCategories}" itemLabel="description" itemValue="id"/>
			</form:select>
		<form:errors cssClass="error" path="accommodationCategory"/>
		</span>
	</fieldset>
	<fieldset id="accommodationNotesHolder">
		<legend><fmt:message key="accommodationNotesTitle"/></legend>
			 <jsp:include page="accommodationNotesContent.jsp"/>
		<form:errors path="accommodationNotes" cssClass="error"/>
	</fieldset>
	<c:if test="${not empty authorization.accommodation}">
		<c:set var="updatable" value="${authorization.accommodation}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key="saveLabel" bundle="${commonBundle}"/>"/>
		</p>
</form:form>
</fmt:bundle>