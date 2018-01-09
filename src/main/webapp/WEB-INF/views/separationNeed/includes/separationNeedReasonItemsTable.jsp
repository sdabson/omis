<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
<table id="separationNeedReasonItemsTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="separationNeedReasonItemsActionMenuLink" href="${pageContext.request.contextPath}/separationNeed/separationNeedReasonItemsActionMenu.html"></a></th>
			<th><fmt:message key="reasonLabel"/></th>
			<th><fmt:message key="activeLabel"/></th>
		</tr>
	</thead>
	<tbody id="separationNeedReasonItemTableBody">
		<jsp:include page="separationNeedReasonItemsTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>