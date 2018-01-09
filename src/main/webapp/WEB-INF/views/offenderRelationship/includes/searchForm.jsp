<%--
  - Offender relation search form.
  -
  - Used to query people that can be related to an offender.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle var="offenderRelationshipBundle" basename="omis.offenderrelationship.msgs.offenderRelationship"/>
<c:forEach var="option" items="${options}" varStatus="status">
	<c:set var="optionParams" value="${status.first ? '' : optionParams}&option=${option}"/>
</c:forEach>
<form id="offenderRelationshipSearchForm" class="searchForm" method="POST" action="${pageContext.request.contextPath}/offenderRelationship/search.html?offender=${offenderSummary.id}&amp;effectiveDate=<fmt:formatDate value='${effectiveDate}' pattern='MM/dd/yyyy'/><c:if test='${not empty optionParams}'><c:out value='${optionParams}'/></c:if>">
	<p>
		<label><fmt:message key="searchByLabel" bundle="${offenderRelationshipBundle}"/></label>
	</p>
	<p>
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'NAME'}">
				<input type="radio" id="searchTypeNameRadio" name="type" value="NAME" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeNameRadio" name="type" value="NAME"/>
			</c:otherwise>
		</c:choose>
		<label for="searchTypeName" id="searchTypeNameLabel"><fmt:message key="searchByNameLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input id="searchTypeName" name="lastName" value="${offenderRelationshipSearchForm.lastName}"/>
		<label for="searchTypeFirstName"><fmt:message key="searchByFirstNameLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input id="searchTypeFirstName" name="firstName" value="${offenderRelationshipSearchForm.firstName}"/>
		<form:errors path="offenderRelationshipSearchForm.lastName" cssClass="error"/>
	</p>
	<p>
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'OFFENDER_NUMBER'}">
				<input type="radio" id="searchTypeOffenderNumberRadio" name="type" value="OFFENDER_NUMBER" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeOffenderNumberRadio" name="type" value="OFFENDER_NUMBER"/>
			</c:otherwise>
		</c:choose>
		<label for="searchTypeOffenderNumber" id="searchTypeOffenderNumberLabel"><fmt:message key="searchByOffenderNumberLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input id="searchOffenderNumber" name="offenderNumber" value="${offenderRelationshipSearchForm.offenderNumber}"/>
		<form:errors path="offenderRelationshipSearchForm.offenderNumber" cssClass="error"/>
	</p>
	<p>
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'SOCIAL_SECURITY_NUMBER'}">
				<input type="radio" id="searchTypeSocialSecurityNumberRadio" name="type" value="SOCIAL_SECURITY_NUMBER" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeSocialSecurityNumberRadio" name="type" value="SOCIAL_SECURITY_NUMBER"/>
			</c:otherwise>
		</c:choose>
		<label for="searchTypeSocialSecurityNumber" id="searchTypeSocialSecurityNumberLabel"><fmt:message key="searchBySocialSecurityNumber" bundle="${offenderRelationshipBundle}"/></label>
		<input id="searchSocialSecurityNumber" name="socialSecurityNumber" value="${offenderRelationshipSearchForm.socialSecurityNumber}"/>
		<form:errors path="offenderRelationshipSearchForm.socialSecurityNumber" cssClass="error"/>
	</p>
	<p>
		<c:choose>
			<c:when test="${offenderRelationshipSearchForm.type.name eq 'BIRTH_DATE'}">
				<input type="radio" id="searchTypeBirthDateRadio" name="type" value="BIRTH_DATE" checked="checked"/>
			</c:when>
			<c:otherwise>
				<input type="radio" id="searchTypeBirthDateRadio" name="type" value="BIRTH_DATE"/>
			</c:otherwise>
		</c:choose>
		<label for="searchTypeBirthDate" id="searchTypeBirthDateLabel"><fmt:message key="searchByBirthDateLabel" bundle="${offenderRelationshipBundle}"/></label>
		<input id="searchBirthDate" name="birthDate" class="date" value="<fmt:formatDate value='${offenderRelationshipSearchForm.birthDate}' pattern='MM/dd/yyyy'/>"/>
		<form:errors path="offenderRelationshipSearchForm.birthDate" cssClass="error"/>
	</p>
	<p class="buttons">
		<c:if test="${showAllResultsOptions}">
			<fmt:message key="warningMessageLabel" bundle="${offenderRelationshipBundle}">
				<fmt:param value="${maximumResults}"/>
			</fmt:message>
			<button name="searchOperation" type="submit" value="SEARCH_ALL_RESULTS"><fmt:message key="showAllResultsLabel" bundle="${offenderRelationshipBundle}"/></button>
		</c:if>
		<button name="searchOperation" type="submit" value="SEARCH"><fmt:message key="searchLabel" bundle="${offenderRelationshipBundle}"/></button>
	</p>
</form>