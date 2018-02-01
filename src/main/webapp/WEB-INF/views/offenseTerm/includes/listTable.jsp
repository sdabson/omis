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
  - Table of offense items.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<fmt:setBundle basename="omis.courtcase.msgs.courtCase" var="courtCaseBundle"/>
<fmt:setBundle basename="omis.docket.msgs.docket" var="docketBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="docketLabel" bundle="${docketBundle}"/></th>
			<th><fmt:message key="judgeLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="overturnedLabel" bundle="${courtCaseBundle}"/></th>
			<th><fmt:message key="dismissedLabel" bundle="${courtCaseBundle}"/>
		</tr>
	</thead>
	<tbody id="courtCases">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>