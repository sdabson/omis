<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<table id="monthlySupervisionFeeListTable" class="listTable">
	<thead>
		<tr>
			<th class="operations"/>			
			<th class="date"><fmt:message key="startDateLabel"/></th>
			<th class="date"><fmt:message key="endDateLabel"/></th>			
			<th><fmt:message key="authorityCategoryLabel"/></th>
			<th><fmt:message key="monthlyFeeLabel"/></th>			
			<th><fmt:message key="commentLabel"/></th>
		</tr>
	</thead>	
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>		
	</tbody>
</table>
</fmt:bundle>