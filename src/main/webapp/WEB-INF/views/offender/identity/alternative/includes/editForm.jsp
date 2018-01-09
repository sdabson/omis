<%--
 - Author: Stephen Abson
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Dec 9, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.person.msgs.identity">
<form:form commandName="alternativeOffenderIdentityForm" class="editForm">	
	<fieldset>	
		<legend><fmt:message key="identityLabel"/></legend>
		<span class="fieldGroup">
		<form:label path="alternativeNameAssociation" class="fieldLabel">
				<fmt:message key="alternativeNameAssociationLabel"/></form:label>
		<select id="alternativeNameAssociation" name="alternativeNameAssociation">
			<option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
				<c:forEach var="alternativeName" items="${alternativeNames}">
					<c:choose>
						<c:when test="${not empty alternativeName and alternativeOffenderIdentityForm.alternativeNameAssociation eq alternativeName}">
							<option value="${alternativeName.id}" selected="selected"><c:out value="${alternativeName.name.lastName}, ${alternativeName.name.firstName} ${alternativeName.name.middleName}"/></option>
						</c:when>
						<c:otherwise>
							<option value="${alternativeName.id}"><c:out value="${alternativeName.name.lastName}, ${alternativeName.name.firstName} ${alternativeName.name.middleName}"/></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		</select>	
			<form:errors path="alternativeNameAssociation" cssClass="error"/>
		</span>
		<c:set var="showDeathFields" value="${false}" scope="request"/>
		<c:set var="createNewBirthPlace" value="${alternativeOffenderIdentityForm.createNewBirthPlace}" scope="request"/>
		<jsp:include page="/WEB-INF/views/person/identity/includes/identityFields.jsp"/>
		<span class="fieldGroup">
			<form:errors path="offenderIdentityGroup" cssClass="groupError"/>
		</span>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="alternativeIdentityCategoryLabel"/></form:label>
			<form:select path="category">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${categories}"/>
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel">
				<fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors path="startDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel">
				<fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors path="endDate" cssClass="error"/>
		</span>
	</fieldset>
	<c:if test="${not empty alternativeIdentityAssociation}">
		<c:set var="updatable" value="${alternativeIdentityAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<button type="submit"><fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
	</p>
</form:form>
</fmt:bundle>