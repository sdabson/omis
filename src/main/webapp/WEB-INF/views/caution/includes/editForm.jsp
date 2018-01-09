<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
 - Form to edit cautions.
 -
 - Author: Stephen Abson
 - Author: Sheronda Vaughn
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.caution.msgs.caution">
<sec:authorize var="editCaution" access="hasRole('CAUTION_EDIT') or hasRole('CAUTION_CREATE') or hasRole('ADMIN')"/>
<form:form commandName="cautionForm" class="editForm">
<fieldset>
	<legend><fmt:message key="cautionDetailsTitle"/></legend>
	<span class="fieldGroup">
	<form:label path="startDate" class="fieldLabel">
		<fmt:message key="startDateLabel" bundle="${commonBundle}"/></form:label>
	<form:input path="startDate" readonly="${not editCaution}" class="date"/>
	<form:errors path="startDate" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="endDate" class="fieldLabel">
		<fmt:message key="endDateLabel" bundle="${commonBundle}"/></form:label>
	<form:input path="endDate" readonly="${not editCaution}" class="date"/>
	<form:errors path="endDate" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="description" class="fieldLabel">
		<fmt:message key="cautionDescriptionLabel"/></form:label>
	<form:select path="description" readonly="${not editCaution}">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options items="${descriptions}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="description" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="source" class="fieldLabel">
		<fmt:message key="cautionSourceLabel"/></form:label>
	<form:select path="source" readonly="${not editCaution}">
		<form:option value="">...</form:option>
		<form:options items="${sources}" itemLabel="name" itemValue="id"/>
	</form:select>
	<form:errors path="source" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="sourceComment" class="fieldLabel">
		<fmt:message key="cautionSourceCommentLabel"/></form:label>
	<form:textarea path="sourceComment" readonly="${not editCaution}"/>
	<form:errors path="sourceComment" cssClass="error"/>
	</span>
	<span class="fieldGroup">
	<form:label path="comment" class="fieldLabel">
		<fmt:message key="cautionCommentLabel"/></form:label>
	<form:textarea path="comment" readonly="${not editCaution}"/>
	<form:errors path="comment" cssClass="error"/>
	</span>
</fieldset>

<c:if test="${not empty caution}">
<c:set var="updatable" value="${caution}" scope="request"/>
<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
</c:if>
<c:if test="${editCaution}">
<p class="buttons">
	<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
</p>
</c:if>
</form:form>
</fmt:bundle>