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
 - Form to change correctional status.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.supervision.msgs.placementTerm">
<form:form commandName="changeCorrectionalStatusForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
			<form:label path="effectiveDate" class="fieldLabel">
				<fmt:message key="effectiveDateLabel" bundle="${commonBundle}"/></form:label>
			<form:input path="effectiveDate" class="date"/>
			<form:errors path="effectiveDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="correctionalStatus" class="fieldLabel">
				<fmt:message key="correctionalStatusLabel"/></form:label>
			<form:select path="correctionalStatus">
				<jsp:include page="correctionalStatusOptions.jsp"/>
			</form:select>
			<form:errors path="correctionalStatus" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startChangeReason" class="fieldLabel">
				<fmt:message key="startChangeReasonLabel"/></form:label>
			<form:select path="startChangeReason" disabled="true">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			</form:select>
			<form:errors path="startChangeReason" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endChangeReason" class="fieldLabel">
				<fmt:message key="endChangeReasonLabel"/></form:label>
			<form:select path="endChangeReason" disabled="true">
				<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			</form:select>
			<form:errors path="endChangeReason" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>
</fmt:bundle>