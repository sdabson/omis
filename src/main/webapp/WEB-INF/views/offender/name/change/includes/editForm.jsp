<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<sec:authorize var="editOffender" access="hasRole('OFFENDER_EDIT') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.person.msgs.name" var="nameBundle" />
<fmt:bundle basename="omis.offender.msgs.changeOffenderName">
<form:form commandName="changeOffenderNameForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="lastName" class="fieldLabel">
				<fmt:message key="lastNameLabel" bundle="${nameBundle}"/></form:label>
			<form:input path="lastName"/>
			<form:errors path="lastName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="firstName" class="fieldLabel">
				<fmt:message key="firstNameLabel" bundle="${nameBundle}"/></form:label>
			<form:input path="firstName"/>
			<form:errors path="firstName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="middleName" class="fieldLabel">
				<fmt:message key="middleNameLabel" bundle="${nameBundle}"/></form:label>
			<form:input path="middleName"/>
		</span>
		<span class="fieldGroup">
			<form:label path="suffix" class="fieldLabel">
				<fmt:message key="suffixLabel" bundle="${nameBundle}"/></form:label>
			<form:select path="suffix">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="name" itemLabel="name" items="${suffixes}"/>
			</form:select>
		</span>
		<span class="fieldGroup">
			<form:label path="effectiveDate" class="fieldLabel">
				<fmt:message key="effectiveDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="effectiveDate" class="date"/>
			<form:errors path="effectiveDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="alternativeNameCategory" class="fieldLabel">
				<fmt:message key="previousNameCategoryLabel"/></form:label>
			<form:select path="alternativeNameCategory">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${alternativeNameCategories}"/>
			</form:select>
			<form:errors path="alternativeNameCategory" cssClass="error"/>
		</span>
		
	</fieldset>
	
	<c:if test="${editOffender}">
		<p class="buttons">
			<button type="submit"><fmt:message key="saveLabel" bundle="${commonBundle}"/></button>
		</p>
	</c:if>
</form:form>
</fmt:bundle>