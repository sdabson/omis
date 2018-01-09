<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.bedplacement.msgs.bedPlacement">
<table id="bedPlacements" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="bedPlacementReasonLabel"/></th>
			<th><fmt:message key="unitAssignedLabel"/></th>
			<th><fmt:message key="sectionAssignedLabel"/></th>
			<th><fmt:message key="levelAssignedLabel"/></th>
			<th><fmt:message key="roomAssignedLabel"/></th>
			<th><fmt:message key="bedAssignedLabel"/></th>
			<th class="date"><fmt:message key="bedPlacementStartDateLabel"/></th>
			<th class="date"><fmt:message key="bedPlacementEndDateLabel"/></th>
			<th><fmt:message key="bedPlacementDescriptionLabel"/></th>
			<th><fmt:message key="bedPlacementConfirmationLabel"/></th>
		</tr>
	</thead>
	<tbody id="placementTable">
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>