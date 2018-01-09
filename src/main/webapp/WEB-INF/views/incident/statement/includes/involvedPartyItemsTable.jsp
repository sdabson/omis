<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<table class="editTable" id="involvedPartyItemsTable">
		<thead>
			<tr>
				<th class="operations"><a class="actionMenuItem" id="involvedPartyItemsActionMenuLink" href="${pageContext.request.contextPath}/incident/statement/involvedPartyItemsActionMenu.html"></a></th>
				<th><label><fmt:message key="personCategoryLabel"/></label></th>
				<th><label><fmt:message key="personLabel"/></label></th>
				<th><label><fmt:message key="narrativeLabel"/></label></th>
			</tr>
		</thead>
		<tbody id="involvedPartyItemsTableBody">
			<jsp:include page="involvedPartyItemsTableBody.jsp"/>
		</tbody>
	</table>
</fmt:bundle>