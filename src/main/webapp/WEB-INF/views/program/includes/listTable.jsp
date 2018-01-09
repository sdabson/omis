<%--
  - List table for program placements.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="omis.program.msgs.program" var="programBundle"/>
<fmt:setBundle basename="omis.supervision.msgs.placementTerm" var="placementTermBundle"/>
<fmt:setBundle basename="omis.location.msgs.location" var="locationBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table class="listTable" id="programPlacements">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="supervisoryOrganizationLabel" bundle="${placementTermBundle}"/></th>
			<th><fmt:message key="correctionalStatusLabel" bundle="${placementTermBundle}"/></th>
			<th><fmt:message key="locationLabel" bundle="${locationBundle}"/></th>
			<th><fmt:message key="programLabel" bundle="${programBundle}"/></th>
			<th><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
			<th><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
			<th><fmt:message key="dayCountLabel" bundle="${programBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>