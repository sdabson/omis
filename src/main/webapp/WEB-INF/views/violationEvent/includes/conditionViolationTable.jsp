<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<table id="conditionViolationTable" class="editTable">
	<thead>
		<tr>
			<th class="operations"><a class="actionMenuItem" id="conditionViolationItemsActionMenuLink" href="${pageContext.request.contextPath}/violationEvent/conditionViolationItemsActionMenu.html?conditionViolationItemIndex=${conditionViolationItemIndex}"></a></th>
			<th id="violationNameHeader"><fmt:message key="violationNameLabel"/></th>
			<th id="violationDescriptionHeader"><fmt:message key="violationDescriptionLabel"/></th>
			<th id="detailsHeader"><fmt:message key="detailsLabel"/></th>
		</tr>
	</thead>
	<tbody id="conditionViolationTableBody">
		<jsp:include page="conditionViolationTableBodyContent.jsp"/>
	</tbody>
</table>
</fmt:bundle>