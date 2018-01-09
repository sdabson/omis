<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<table id="feeRequirementsListTable" class="listTable">
	<thead>
		<tr>
			<th class="adjustments"/>			
			<th class="date"><fmt:message key="startDateLabel"/></th>
			<th class="date"><fmt:message key="endDateLabel"/></th>	
			<th><fmt:message key="feeRequirementLabel"/></th>							
			<th class="reason"><fmt:message key="reasonLabel"/></th>
			<th class="authority"><fmt:message key="authorityCategoryLabel"/></th>
		</tr>
	</thead>	
	<tbody>	
		<jsp:include page="feeRequirementsListTableBodyContent.jsp"/>		
	</tbody>	
</table>
</fmt:bundle>