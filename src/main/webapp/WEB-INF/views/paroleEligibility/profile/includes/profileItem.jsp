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
 - Date: Jan 11, 2018
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.paroleeligibility.msgs.paroleEligibility">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/paroleEligibility/list.html?offender=${offenderSummary.id}">
			<span>
				<fmt:message key="paroleEligibilityAndCountLabel">
					<fmt:param value="${paroleEligibilityCount}"/>
				</fmt:message>
			</span>
		</a>
	</div>
</fmt:bundle>