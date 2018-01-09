<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
<table id="specialNeeds" class="listTable">
	<thead>
		<tr>		
			<th class="operations"></th>
			<th><fmt:message key="specialNeedClassificationLabel"/></th>
			<th><fmt:message key="specialNeedCategoryLabel"/></th>
			<th><fmt:message key="specialNeedSourceLabel"/></th>
			<th class="date"><fmt:message key="specialNeedStartDateLabel"/></th>
			<th class="date"><fmt:message key="specialNeedEndDateLabel"/></th>
			<th><fmt:message key="specialNeedCommentLabel"/></th>			
		</tr>
	</thead>
	<tbody>	
		<jsp:include page="../includes/listTableBodyContent.jsp"/>		
	</tbody>
</table>
</fmt:bundle>