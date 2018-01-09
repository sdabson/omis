<%--
  - Form to edit program placements.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="programPlacementForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="programPlacementLabel" bundle="${programBundle}"/></legend>
		<span class="fieldGroup">
			<form:label path="program" class="fieldLabel">
				<fmt:message key="programLabel" bundle="${programBundle}"/></form:label>
			<form:select path="program">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
				<form:options itemValue="id" itemLabel="name" items="${programs}"/>
			</form:select>
			<form:errors path="program" cssClass="error"/>
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
	<c:if test="${not empty programPlacement}">
	<c:set var="updatable" value="${programPlacement}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>