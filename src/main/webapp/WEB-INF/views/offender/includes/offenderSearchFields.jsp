<%--
  - Fields for offender search.
  -
  - This snippet requires that the command object have a composited
  - offenderSearchFields property. This property should contain offender
  - search fields. For this reason, it is recommended that OffenderSearchFields
  - is used.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.offender.msgs.offenderSearch" var="offenderSearchBundle"/>
<p>
	<form:radiobutton path="offenderSearchFields.type" id="searchTypeName" value="NAME"/>
	<form:label path="offenderSearchFields.lastName" id="lastNameLabel"><fmt:message key="searchByLastNameLabel" bundle="${offenderSearchBundle}"/></form:label>
	<form:input path="offenderSearchFields.lastName" id="offenderSearchFields.lastName"/>
	<form:label path="offenderSearchFields.firstName" id="firstNameLabel"><fmt:message key="searchByFirstNameLabel" bundle="${offenderSearchBundle}"/></form:label>
	<form:input path="offenderSearchFields.firstName" id="offenderSearchFields.firstName"/>
	<form:errors path="offenderSearchFields.lastName" cssClass="error"/>
</p>
<p>
	<form:radiobutton path="offenderSearchFields.type" id="searchTypeOffenderNumber" value="OFFENDER_NUMBER"/>
	<form:label path="offenderSearchFields.offenderNumber" id="offenderNumberLabel"><fmt:message key="searchByOffenderNumberLabel" bundle="${offenderSearchBundle}"/></form:label>
	<form:input path="offenderSearchFields.offenderNumber" id="offenderSearchFields.offenderNumber"/>
	<form:errors path="offenderSearchFields.offenderNumber" cssClass="error"/>
</p>
<p>
	<form:radiobutton path="offenderSearchFields.type" id="searchTypeSocialSecurityNumber" value="SOCIAL_SECURITY_NUMBER"/>
	<form:label path="offenderSearchFields.socialSecurityNumber" id="socialSecurityNumberLabel"><fmt:message key="searchBySocialSecurityNumberLabel" bundle="${offenderSearchBundle}"/></form:label>
	<form:input path="offenderSearchFields.socialSecurityNumber" id="offenderSearchFields.socialSecurityNumber"/>
	<form:errors path="offenderSearchFields.socialSecurityNumber" cssClass="error"/>
</p>
<p>
	<form:radiobutton path="offenderSearchFields.type" id="searchTypeBirthDate" value="BIRTH_DATE"/>
	<form:label path="offenderSearchFields.birthDate" id="birthDateLabel"><fmt:message key="searchTypeBirthDateLabel" bundle="${offenderSearchBundle}"/></form:label>
	<form:input path="offenderSearchFields.birthDate" id="offenderSearchFields.birthDate"/>
	<form:errors path="offenderSearchFields.birthDate" cssClass="error"/>
</p>