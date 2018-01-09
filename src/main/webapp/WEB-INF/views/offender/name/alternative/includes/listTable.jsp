<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.person.msgs.name" var="nameBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"/>
			<th><fmt:message key="lastNameLabel" bundle="${nameBundle}"/></th>
			<th><fmt:message key="firstNameLabel" bundle="${nameBundle}"/></th>
			<th><fmt:message key="middleNameLabel" bundle="${nameBundle}"/></th>
			<th><fmt:message key="suffixLabel" bundle="${nameBundle}"/></th>
			<th><fmt:message key="alternativeNameCategoryLabel" bundle="${nameBundle}"/></th>
			<th class="date"><fmt:message key="startDateLabel" bundle="${commonBundle}"/></th>
			<th class="date"><fmt:message key="endDateLabel" bundle="${commonBundle}"/></th>
		</tr>
	</thead>
	<tbody>
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>