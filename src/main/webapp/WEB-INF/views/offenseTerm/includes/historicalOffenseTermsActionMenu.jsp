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
  - Action menu for historical offense terms.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/editHistoricalOffenseTerm.html?sentence=${sentence.id}" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="viewEditHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_REMOVE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/removeHistoricalOffenseTerm.html?sentence=${sentence.id}" class="removeLink"><span class="visibleLinkLabel"><fmt:message key="removeHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty conviction}">
		<sec:authorize access="hasRole('OFFENSE_TERM_CREATE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/createHistoricalOffenseTerm.html?conviction=${conviction.id}" class="createLink"><span class="visibleLinkLabel"><fmt:message key="createHistoricalOffenseTermLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
</ul>