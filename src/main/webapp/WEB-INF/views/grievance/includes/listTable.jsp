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
  - Grievances list table.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.grievance.msgs.grievance" var="grievanceBundle"/>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="grievanceNumberLabel" bundle="${grievanceBundle}"/></th>
			<c:if test="${empty offender}">
				<th><fmt:message key="offenderLabel" bundle="${offenderBundle}"/></th>
			</c:if>
			<th><fmt:message key="grievanceOpenedDateLabel" bundle="${grievanceBundle}"/></th>
			<th><fmt:message key="grievanceSubjectLabel" bundle="${grievanceBundle}"/></th>
			<th><fmt:message key="grievanceStatusLabel" bundle="${grievanceBundle}"/></th>
			<th><fmt:message key="grievanceDescriptionLabel" bundle="${grievanceBundle}"/></th>
		</tr>
	</thead>
	<tbody id="grievances">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>