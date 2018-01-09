<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.person.msgs.name" var="nameBundle"/>
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
		<form:errors path="middleName" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="suffix" class="fieldLabel">
			<fmt:message key="suffixLabel" bundle="${nameBundle}"/></form:label>
		<form:select path="suffix">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options items="${suffixes}" itemLabel="name" itemValue="name"/>
		</form:select>
		<form:errors path="suffix" cssClass="error"/>
		</span>