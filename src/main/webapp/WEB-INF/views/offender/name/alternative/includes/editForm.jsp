<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Dev 9, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.person.msgs.name">
<form:form commandName="alternativeOffenderNameForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="nameLabel"/></legend>
		<span class="fieldGroup">
			<form:label path="lastName" class="fieldLabel">
				<fmt:message key="lastNameLabel"/></form:label>
			<form:input path="lastName"/>
			<form:errors path="lastName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="firstName" class="fieldLabel">
				<fmt:message key="firstNameLabel"/></form:label>
			<form:input path="firstName"/>
			<form:errors path="firstName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="middleName" class="fieldLabel">
				<fmt:message key="middleNameLabel"/></form:label>
			<form:input path="middleName"/>
			<form:errors path="middleName" cssClass="fieldLabel"/>
		</span>
		<span class="fieldGroup">
			<form:label path="suffix" class="fieldLabel">
				<fmt:message key="suffixLabel"/></form:label>
			<form:select path="suffix">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="name" itemLabel="name" items="${suffixes}"/>
			</form:select>
			<form:errors path="suffix" cssClass="errors"/>
		</span>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="alternativeNameCategoryLabel"/></form:label>
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
	<c:if test="${not empty alternativeNameAssociation}">
		<c:set var="updatable" value="${alternativeNameAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<button type="submit"><fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
	</p>
</form:form>
</fmt:bundle>