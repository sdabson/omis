<%--
  - Fields for program placements.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<span class="fieldGroup">
	<form:label class="fieldLabel" path="program">
		<fmt:message key="programLabel" bundle="${programBundle}"/></form:label>
	<form:select path="program">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options itemLabel="name" itemValue="id" items="${programs}"/>
	</form:select>
	<form:errors cssClass="error" path="program"/>
</span>
<span class="fieldGroup">
	<form:label class="fieldLabel" path="startDate">
		<fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
	<form:input id="startDate" path="startDate" class="date"/>
	<form:errors cssClass="error" path="startDate"/>
</span>
<span class="fieldGroup">
	<form:label class="fieldLabel" path="endDate">
		<fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
	<form:input id="endDate" path="endDate" class="date"/>
	<form:errors cssClass="error" path="endDate"/>
</span>