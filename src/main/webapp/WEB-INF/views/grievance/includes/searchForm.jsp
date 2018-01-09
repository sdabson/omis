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
  - Form to search grievances.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<form:form commandName="grievanceSearchForm" class="searchForm">
	<p>
		<label><fmt:message key="grievanceSearchTypeLabel" bundle="${grievanceBundle}"/></label>
		<c:forEach var="grievanceSearchType" items="${grievanceSearchTypes}">
			<c:choose>
				<c:when test="${grievanceSearchType.name eq grievanceSearchForm.type.name}">
					<input type="radio" id="grievanceSearchType.${grievanceSearchType.name}" name="type" value="${grievanceSearchType.name}" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="radio" id="grievanceSearchType.${grievanceSearchType.name}" name="type" value="${grievanceSearchType.name}"/>
				</c:otherwise>
			</c:choose>
			<label for="grievanceSearchType.${grievanceSearchType.name}"><fmt:message key="grievanceSearchTypeLabel.${grievanceSearchType.name}" bundle="${grievanceBundle}"/></label>
		</c:forEach>
		<form:input path="query" id="grievanceSearchOffenderQuery" cssClass="${grievanceSearchForm.type.name eq 'OFFENDER' ? 'shown' : 'hidden'}"/>
		<form:hidden path="offender" id="searchOffender"/>
		<form:select path="location" id="searchLocation" cssClass="${grievanceSearchForm.type.name eq 'LOCATION' ? 'shown' : 'hidden'}">
			<form:option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></form:option>
			<form:options itemValue="id" itemLabel="name" items="${grievanceLocations}"/>
		</form:select>
		<input type="submit" value="<fmt:message key='searchLabel' bundle='${commonBundle}'/>"/>
		<form:errors path="query" cssClass="error"/>
		<form:errors path="type" cssClass="error"/>
	</p>
</form:form>