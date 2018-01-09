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

<%-- Author: Ryan Johns
   - Version: 0.1.0 (Jul 28, 2015)
   - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.supervision.msgs.summary">
<c:if test="${not empty supervisionSummary.correctionalStatusName}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="correctionalStatusLabel"/></span>
		<span class="offenderHeaderFieldValue"><c:out value="${supervisionSummary.correctionalStatusName}"/></span>
	</div>
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="correctionalStatusTermStartDateLabel"/></span>
		<span class="offenderHeaderFieldValue"><fmt:formatDate value="${supervisionSummary.correctionalStatusTermStartDate}" pattern="MM/dd/yyyy"/></span>
	</div>
</div>
</c:if>
<c:if test="${not empty supervisionSummary.supervisoryOrganizationName}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="supervisoryOrganizationLabel"/></span>
		<span class="offenderHeaderFieldValue"><c:out value="${supervisionSummary.supervisoryOrganizationName} (${supervisionSummary.placementTermStartReasonName})"/></span>
	</div>
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="placementTermStartDateLabel"/></span>
		<span class="offenderHeaderFieldValue"><fmt:formatDate value="${supervisionSummary.supervisoryOrganizationTermStartDate}" pattern="MM/dd/yyyy"/></span>
	</div>
</div>
</c:if>
</fmt:bundle>