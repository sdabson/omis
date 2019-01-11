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
  - Table to display presentence investigation docket associations.
  -
  - Author: Josh Divine
  - Version: 0.1.0 (Oct 31, 2018)
  - Since: OMIS 3.0
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationRequest">
<table id="presentenceInvestigationDocketAssociationTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="presentenceInvestigationDocketAssociationItemsActionMenuLink" href="${pageContext.request.contextPath}/presentenceInvestigation/request/presentenceInvestigationDocketAssociationItemsActionMenu.html?presentenceInvestigationDocketAssociationItemIndex=${presentenceInvestigationDocketAssociationItemIndex}&offender=${offender.id}"></a></th>
			<th><fmt:message key="docketValueLabel"/></th>
			<th><fmt:message key="courtLabel"/></th>
		</tr>
	</thead>
	<tbody id="presentenceInvestigationDocketAssociationTableBody">
		<jsp:include page="presentenceInvestigationDocketAssociationTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>