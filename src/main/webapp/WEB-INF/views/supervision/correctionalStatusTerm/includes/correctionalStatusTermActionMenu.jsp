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
  - Action menu for correctional status.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.supervision.msgs.profile" var="profileBundle"/>
<fmt:bundle basename="omis.supervision.msgs.correctionalStatusTerm">
<ul>
	<sec:authorize access="hasRole('CORRECTIONAL_STATUS_TERM_LIST') or hasRole('ADMIN')">
		<li><a class="listLink" href="${pageContext.request.contextPath}/supervision/correctionalStatusTerm/list.html?offender=${offender.id}"><fmt:message key="listCorrectionalStatusTermsLink"/></a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('SUPERVISION_PROFILE_VIEW') or hasRole('ADMIN')">
		<li><a class="supervisionProfileLink" href="${pageContext.request.contextPath}/supervision/profile.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="supervisionProfileHeader" bundle="${profileBundle}"/></span></a></li>
	</sec:authorize>
</ul>
</fmt:bundle>