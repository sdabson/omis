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
  - Fields for docket.
  -
  - Attribute: fieldsPropertyName name of fields property; defaults to "fields"
  - Attribute: docketFieldsEnabled whether docket fields are enabled;
  -            defaults to true
  - Attribute: docketFields docket fields; required
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<fmt:setBundle var="courtBundle" basename="omis.court.msgs.court"/>
<fmt:setBundle var="docketBundle" basename="omis.docket.msgs.docket"/>
<c:if test="${empty fieldsPropertyName}">
	<c:set var="fieldsPropertyName" value="fields"/>
</c:if>
<c:if test="${empty docketFieldsEnabled}">
	<c:set var="docketFieldsEnabled" value="${true}"/>
</c:if>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.court" class="fieldLabel">
		<fmt:message key="courtLabel" bundle="${courtBundle}"/></form:label>
	<form:select path="${fieldsPropertyName}.court" disabled="${not docketFieldsEnabled}">
		<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
		<form:options itemValue="id" itemLabel="name" items="${courts}"/>
	</form:select>
	<form:errors path="${fieldsPropertyName}.court" cssClass="error"/>
</span>
<span class="fieldGroup">
	<form:label path="${fieldsPropertyName}.value" class="fieldLabel">
		<fmt:message key="docketValueLabel" bundle="${docketBundle}"/></form:label>
	<form:input path="${fieldsPropertyName}.value" disabled="${not docketFieldsEnabled}"/>
	<form:errors path="${fieldsPropertyName}.value" cssClass="error"/>
</span>