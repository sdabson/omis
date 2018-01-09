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
  - Action menu for screen to list grievances.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle var="grievanceBundle" basename="omis.grievance.msgs.grievance"/>
<ul>
	<sec:authorize access="hasRole('ADMIN') or hasRole('GRIEVANCE_LIST')">
		<c:if test="${not empty offender}">
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/grievance/listByOffender.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listGrievancesByOffenderLink" bundle="${grievanceBundle}"/></span></a>
		</li>
		</c:if>
		<c:if test="${not empty location}">
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/grievance/listByLocation.html?location=${location.id}"><span class="visibleLinkLabel"><fmt:message key="listGrievancesByLocationLink" bundle="${grievanceBundle}"><fmt:param>${location}</fmt:param></fmt:message></span></a>
		</li>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('GRIEVANCE_VIEW')">
		<c:if test="${not empty grievance}">
		<li>
			<a class="viewEditLink" href="${pageContext.request.contextPath}/grievance/edit.html?grievance=${grievance.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditGrievanceLink" bundle="${grievanceBundle}"/></span></a>
		</li>
		</c:if>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('GRIEVANCE_REMOVE')">
		<c:if test="${not empty grievance}">
		<li>
			<a class="removeLink" href="${pageContext.request.contextPath}/grievance/remove.html?grievance=${grievance.id}"><span class="visibleLinkLabel"><fmt:message key="removeGrievanceLink" bundle="${grievanceBundle}"/></span></a>
		</li>
		</c:if>
	</sec:authorize>
</ul>