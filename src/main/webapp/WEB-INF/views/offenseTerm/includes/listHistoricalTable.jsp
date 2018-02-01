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
  - Table of historical offense terms.
  -
  - Historical offense terms are inactive sentences.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="sentenceBundle" basename="omis.sentence.msgs.sentence"/>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<table class="listTable" id="historicalOffenseTerms">
	<thead>
		<tr>
			<th></th>
			<th><fmt:message key="offenseLabel" bundle="${convictionBundle}"/></th>
			<th><fmt:message key="termLabel" bundle="${offenseTermBundle}"/>
			<th><fmt:message key="pronouncementDateLabel" bundle="${sentenceBundle}"/>
			<th><fmt:message key="dateLabel" bundle="${convictionBundle}"/>
			<th><fmt:message key="changeOrderLabel" bundle="${sentenceBundle}"/>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listHistoricalTableBodyContent.jsp"/>
	</tbody>
</table>