<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
<table id="targetSeparationNeeds" class="listTable">
	<caption><fmt:message key="separationNeedTargetHeadingLabel"/></caption>
	<thead>
		<tr>
			<th><fmt:message key="separationNeedSubjectiInfoLabel"/></th>
			<th><fmt:message key="currentLocationLabel"/></th>
			<th class="notes"><fmt:message key="separationNeedCommentLabel"/></th>
			<th class="date"><fmt:message key="beganOnLabel"/></th>
			<th class="date"><fmt:message key="removedOnLabel"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="../includes/targetListTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>