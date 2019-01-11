<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Sept. 7, 2018)
 - Since: OMIS 3.0
 -->
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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.staff.msgs.staff">
	<ul>
		<sec:authorize access="hasRole('STAFF_ASSIGNMENT_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" id="staffAssignmentCreateEditScreenActionMenuLink" href="${pageContext.request.contextPath}/staffSearch/staffSearch.html?">
					<span class="visibleLinkLabel">
						<fmt:message key="staffSearchLabel"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>