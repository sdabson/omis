<%--
  - Summary of online account.
  -
  - Author: Joel Norris
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="contact" uri="/WEB-INF/tld/contact.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.person.msgs.person"	var="person"/>
<span class="subSectionLine">
	<span class="lineCell">
		<label class="subSectionLabel"><fmt:message key="personNameLabel" bundle="${person}"/></label>
		<fmt:message key="personNameLastFirst" bundle="${person}">
			<fmt:param value="${personSummarizable.personLastName}"/>
			<fmt:param value="${personSummarizable.personFirstName}"/>
		</fmt:message>
		<c:if test="${not empty personSummarizable.personMiddleName}">
			<c:out value="${personSummarizable.personMiddleName}"/>
		</c:if>
	</span>
	<c:if test="${not empty personSummarizable.personSex}">
		<span class="lineCell">
			<label class="subSectionLabel"><fmt:message key="sexLabel" bundle="${person}"/></label>
			<fmt:message key="person.${personSummarizable.personSex}.sex" bundle="${person}"/>
		</span>
	</c:if>
	<c:if test="${not empty personSummarizable.personBirthDate}">
		<span class="lineCell">
			<label class="subSectionLabel"><fmt:message key="birthDateLabel" bundle="${person}"/></label>
			<fmt:formatDate value="${personSummarizable.personBirthDate}" pattern="MM/dd/yyyy" var="birthDate"/>
			<c:out value="${birthDate}"/>
		</span>
	</c:if>
	<span class="lineCell">
		<c:if test="${not empty personSummarizable.personBirthPlaceName or not empty personSummarizable.personBirthStateName or not empty personSummarizable.personBirthCountryName}">
			<label class="subSectionLabel"><fmt:message key="birthPlaceLabel" bundle="${person}"/></label>
		</c:if>
		<c:if test="${not empty personSummarizable.personBirthPlaceName}">
			<c:out value="${personSummarizable.personBirthPlaceName}, "/>
		</c:if>
		<c:if test="${not empty personSummarizable.personBirthStateName}">
			<c:out value="${personSummarizable.personBirthStateName} "/>
		</c:if>
		<c:if test="${not empty personSummarizable.personBirthCountryName}">
			<c:out value="${personSummarizable.personBirthCountryName}"/>
		</c:if>
	</span>
	<c:if test="${not empty personSummarizable.personDeathDate}">
		<span class="lineCell">
			<label class="subSectionLabel"><fmt:message key="deathDateLabel" bundle="${person}"/></label>
			<fmt:formatDate value="${personSummarizable.personDeathDate}" pattern="MM/dd/yyyy" var="deathDate"/>
			<c:out value="${deathDate}"/>
		</span>
	</c:if> 
</span>