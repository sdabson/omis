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
 - Author: Trevor Isles
 - Date: Dec 18, 2017
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.paroleeligibility.msgs.paroleEligibility">
	<ul>
		<sec:authorize access="hasRole('PAROLE_ELIGIBILITY_VIEW') or hasRole('ADMIN')">
			<li>
				<a id="createParoleEligibilityNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/paroleEligibility/createParoleEligibilityNoteItem.html?paroleEligibilityNoteItemIndex=${paroleEligibilityNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="createParoleEligibilityNoteLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>