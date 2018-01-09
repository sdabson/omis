<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
<table id="separationNeeds" class="listTable">
	<thead>
		<tr class="">
			<th class="operations"></th>
			<th><fmt:message key="separationNeedTargetOffenderInfoLabel"/></th>
			<th><fmt:message key="separationNeedStatusLabel"/></th>
			<th><fmt:message key="currentLocationLabel"/></th>
			<th class="notes"><fmt:message key="separationNeedCommentLabel"/></th>
			<th class="date"><fmt:message key="beganOnLabel"/></th>
			<th class="date"><fmt:message key="removedOnLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/listTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>