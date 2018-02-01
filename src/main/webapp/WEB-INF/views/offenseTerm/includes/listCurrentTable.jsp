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
  - Table of sentence items.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<fmt:setBundle var="courtCaseBundle" basename="omis.courtcase.msgs.courtCase"/>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="courtCaseLabel" bundle="${courtCaseBundle}"/></th>
			<th><fmt:message key="offenseLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="termLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="countsLabel" bundle="${convictionBundle}"/></th>
			<th><fmt:message key="legalDispositionCategoryLabel" bundle="${offenseTermBundle}"/></th>
			<th><fmt:message key="pronouncementDateLabel" bundle="${courtCaseBundle}"/></th>
		</tr>
	</thead>
	<tbody id="sentences">
		<jsp:include page="listCurrentTableBodyContent.jsp"/>
	</tbody>
</table>