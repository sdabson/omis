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
  - Action menu for offense term.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<sec:authorize access="hasRole('OFFENSE_TERM_LIST') or hasRole('ADMIN')">
		<li>
			<a href="${pageContext.request.contextPath}/offenseTerm/list.html?person=${person.id}" class="listLink"><span class="visibleLinkLabel"><fmt:message key="listOffenseTermsLink" bundle="${offenseTermBundle}"/></span></a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/offenseTerm/listCurrentOffenses.html?person=${person.id}" class="listLink"><span class="visibleLinkLabel"><fmt:message key="listCurrentOffenseTermsLink" bundle="${offenseTermBundle}"/></span></a>
		</li>
	</sec:authorize>
	<sec:authorize access="hasRole('OFFENSE_TERM_DOCKET_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty courtCase}">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/editDocket.html?courtCase=${courtCase.id}" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="editOffenseTermDocketLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</c:if>
	</sec:authorize>
</ul>