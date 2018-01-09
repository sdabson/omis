<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.person.msgs.identity" var="identityBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="alternativeNameAssociationLabel" bundle="${identityBundle}"/></th>
			<th><fmt:message key="socialSecurityNumberLabel" bundle="${identityBundle}"/></th>
			<th><fmt:message key="stateIdNumberLabel" bundle="${identityBundle}"/></th>
			<th class="date"><fmt:message key="birthDateLabel" bundle="${identityBundle}"/></th>
			<th><fmt:message key="birthCountryLabel" bundle="${identityBundle}"/></th>
			<th><fmt:message key="birthPlaceLabel" bundle="${identityBundle}"/></th>
			<th><fmt:message key="sexLabel" bundle="${identityBundle}"/></th>
			<th><fmt:message key="alternativeIdentityCategoryLabel" bundle="${identityBundle}"/></th>
			<th class="date"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
			<th class="date"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>