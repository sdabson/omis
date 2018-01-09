<%--
 - Author: Jason Nelson
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.tierdesignation.msgs.tierDesignation">
<form:form commandName="tierDesignationForm" class="editForm">
	<fieldset>
	<legend><fmt:message key="tierDesignationLabel"/></legend>
	<span class="fieldGroup">
	<form:label path="level" class="fieldLabel">
		<fmt:message key="levelLabel"/></form:label>
	<form:select path="level">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options items="${levels}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="level" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="source" class="fieldLabel">
		<fmt:message key="sourceLabel"/></form:label>
	<form:select path="source">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options items="${sources}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="source" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="changeReason" class="fieldLabel">
		<fmt:message key="changeReasonLabel"/></form:label>
	<form:select path="changeReason">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options items="${changeReasons}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="changeReason" cssClass="error"/>
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
	<span class="fieldGroup">			  
	<form:label path="comment" class="fieldLabel">
		<fmt:message key="commentLabel"/></form:label>
	<form:textarea path="comment"/>
	<form:errors path="comment" cssClass="error"/>
	</span>
	</fieldset>
	<c:if test="${not empty tierDesignation}">
	<c:set var="updatable" value="${tierDesignation}" scope="request"/>
	<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${commonBundle}"/>"/>
	</p>
</form:form>
</fmt:bundle>