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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<ul>
		<sec:authorize access="hasRole('EARLY_RELEASE_REQUEST_CREATE') or hasRole('EARLY_RELEASE_REQUEST_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createEarlyReleaseRequestNoteAssociationItemLink" class="createLink" href="${pageContext.request.contextPath}/earlyReleaseTracking/createEarlyReleaseRequestNoteAssociationItem.html?earlyReleaseRequestNoteAssociationItemIndex=${earlyReleaseRequestNoteAssociationItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addEarlyReleaseRequestNoteAssociationLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>