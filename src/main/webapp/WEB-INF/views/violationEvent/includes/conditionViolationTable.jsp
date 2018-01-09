<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<table id="conditionViolationTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="conditionViolationItemsActionMenuLink" href="${pageContext.request.contextPath}/violationEvent/conditionViolationItemsActionMenu.html?conditionViolationItemIndex=${conditionViolationItemIndex}"></a></th>
			<th><fmt:message key="violationNameLabel"/></th>
			<th><fmt:message key="violationDescriptionLabel"/></th>
		</tr>
	</thead>
	<tbody id="conditionViolationTableBody">
		<jsp:include page="conditionViolationTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>